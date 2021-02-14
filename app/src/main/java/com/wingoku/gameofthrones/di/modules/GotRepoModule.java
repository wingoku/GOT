package com.wingoku.gameofthrones.di.modules;

import com.wingoku.gameofthrones.data.network.interfaces.GOTApi;
import com.wingoku.gameofthrones.data.persistance.databases.GOTDatabase;
import com.wingoku.gameofthrones.data.repositories.GotRepoImpl;
import com.wingoku.gameofthrones.domain.interfaces.GotRepo;
import com.wingoku.gameofthrones.domain.mappers.BookDomainMapper;
import com.wingoku.gameofthrones.domain.mappers.CategoryDomainMapper;
import com.wingoku.gameofthrones.domain.mappers.CharactorDomainMapper;
import com.wingoku.gameofthrones.domain.mappers.HouseDomainMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {RetrofitModule.class, GotDBModule.class, MapperModule.class})
public class GotRepoModule {

    @Provides
    @Singleton
    public GotRepo providesGotRepoImpl(GOTApi gotApi, GOTDatabase db, CategoryDomainMapper categoryDomainMapper,
                                       HouseDomainMapper houseDomainMapper, BookDomainMapper bookDomainMapper, CharactorDomainMapper charactorDomainMapper) {

        return new GotRepoImpl(db, gotApi, categoryDomainMapper, houseDomainMapper, bookDomainMapper, charactorDomainMapper);
    }
}
