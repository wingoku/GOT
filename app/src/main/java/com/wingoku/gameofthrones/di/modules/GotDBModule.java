package com.wingoku.gameofthrones.di.modules;

import android.content.Context;

import androidx.room.Room;

import com.wingoku.gameofthrones.data.persistance.databases.GOTDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(includes = {ContextModule.class})
public class GotDBModule {
    private static final String DATABASE_NAME = "temp";
    @Provides
    @Singleton
    public GOTDatabase providesGotClientRepo(Context context) {

        return Room.databaseBuilder(
                context.getApplicationContext(),
                GOTDatabase.class,
                DATABASE_NAME
        ).build();
    }
}
