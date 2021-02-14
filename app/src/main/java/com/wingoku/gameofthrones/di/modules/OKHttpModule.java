package com.wingoku.gameofthrones.di.modules;

import android.content.Context;

import com.wingoku.gameofthrones.BuildConfig;
import com.wingoku.gameofthrones.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.wingoku.gameofthrones.utils.Constants.CONNECTION_TIME_OUT;
import static com.wingoku.gameofthrones.utils.Constants.REQUEST_RETRIES;


@Module (includes = ContextModule.class)
public class OKHttpModule {
    // NOTE:
    // USE ADD_INTERCEPTOR INSTEAD OF ADD_NETWORK_INTERCEPTOR IF YOU WANT TO CALL CHAIN.PROCEED(REQUEST) MULTIPLE TIMES. ADd_NETWORK+INTERCEPTOR ALLOWS TO CALL CHAIN.PROCEED(REQUESt) ONLY ONCE!
    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient(final Context context, OkHttpClient.Builder builder, HttpLoggingInterceptor okhttpLoggingInterceptor, Cache cache, @Named("RewriteResponseInterceptor") Interceptor rewriteResponseInterceptor, @Named("RewriteResponseOfflineInterceptor") Interceptor rewriteResponseOfflineInterceptor, @Named("RetryInterceptor") Interceptor retriesInterceptor) {

        builder.cache(cache);
        builder.addInterceptor(retriesInterceptor);
        builder.addInterceptor(rewriteResponseInterceptor);
        builder.addInterceptor(rewriteResponseOfflineInterceptor);

        if(BuildConfig.DEBUG) {
            builder.addInterceptor(okhttpLoggingInterceptor);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient().newBuilder();
        okhttpClientBuilder.connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS);
        return okhttpClientBuilder;
    }

    @Provides
    @Singleton
    public Cache providesCache(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), "marvel_responses");
        int cacheSize = 100 * 1024 * 1024; // 100 MB
        return new Cache(httpCacheDirectory, cacheSize);
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor providesOkhttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Named("RewriteResponseOfflineInterceptor")
    public Interceptor providesRewriteResponseInterceptorOfflineInterceptor(final Context con) {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!Utils.isNetworkAvailable(con)) {
                    request = request.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("must-revalidate")
                            .removeHeader("keep-alive")
                            .removeHeader("Connection")
                            .removeHeader("Transfer-Encoding")
                            .removeHeader("Date")
                            .header("Cache-Control", "public, "+ CacheControl.FORCE_CACHE)
                            .build();
                }
                else {
                    int maxAge = 6000; // read from cache for 1 minute
                    request = request.newBuilder()
                            .removeHeader("must-revalidate")
                            .removeHeader("Pragma")
                            .removeHeader("Connection")
                            .removeHeader("Transfer-Encoding")
                            .removeHeader("Date")
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                }
                return chain.proceed(request);
            }
        };
    }

    @Provides
    @Named("RewriteResponseInterceptor")
    public Interceptor providesRewriteResponseInterceptor() {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Response originalResponse = chain.proceed(chain.request());
                String cacheControl = originalResponse.header("Cache-Control");
                if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                        cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0") || cacheControl.contains("only-if-cached")) {
                    return originalResponse.newBuilder()
                            .removeHeader("must-revalidate")
                            .removeHeader("Pragma")
                            .removeHeader("Connection")
                            .removeHeader("Transfer-Encoding")
                            .removeHeader("keep-alive")
                            .removeHeader("Date")
                            .header("Cache-Control", "public, max-age=" + 500000)
                            .build();
                } else {
                    return originalResponse;
                }
            }
        };
    }

    @Provides
    @Named("RetryInterceptor")
//    @Singleton
    public Interceptor providesRetryInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());

                int retries = 0;
                while(!response.isSuccessful() && retries < REQUEST_RETRIES) {

                    response = chain.proceed(chain.request());
                    retries++;
                }
                return response;
            }
        };
    }
}

