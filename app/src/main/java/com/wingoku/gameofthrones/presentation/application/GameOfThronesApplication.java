package com.wingoku.gameofthrones.presentation.application;

import android.app.Application;

import com.wingoku.gameofthrones.di.components.AppComponent;
import com.wingoku.gameofthrones.di.components.DaggerAppComponent;
import com.wingoku.gameofthrones.di.modules.ContextModule;

public class GameOfThronesApplication extends Application {
    AppComponent component;
    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder().contextModule(new ContextModule(getApplicationContext())).build();
        component.inject(this);
    }

    public AppComponent getAppComponent() {
        return component;
    }
}
