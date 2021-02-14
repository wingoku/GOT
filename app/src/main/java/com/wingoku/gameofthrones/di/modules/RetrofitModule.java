package com.wingoku.gameofthrones.di.modules;

import android.util.Log;

import com.wingoku.gameofthrones.BuildConfig;
import com.wingoku.gameofthrones.data.network.adapters.liveData.LiveDataAdapterFactory;
import com.wingoku.gameofthrones.data.network.interfaces.GOTApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {OKHttpModule.class})
public class RetrofitModule {

    @Provides
    @Singleton
    public GOTApi providesGotAPI(Retrofit retrofit) {
        Log.e("", "retrofit object created");
        return retrofit.create(GOTApi.class);
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory, LiveDataAdapterFactory rxJava2AdapterFactory) {

        return new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(rxJava2AdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .baseUrl(BuildConfig.API_URL)
                .build();
    }

    @Provides
    public GsonConverterFactory providesGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    public LiveDataAdapterFactory providesCallToLiveDataAdapterFactory() {
        return new LiveDataAdapterFactory();
    }
}
