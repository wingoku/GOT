package com.wingoku.gameofthrones.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context mContext;

    public ContextModule(Context context) {
        this.mContext = context;
    }

    @Provides
    public Context providesContext() {
        return mContext;
    }
}
