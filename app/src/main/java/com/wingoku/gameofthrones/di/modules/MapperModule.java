package com.wingoku.gameofthrones.di.modules;

import com.wingoku.gameofthrones.domain.mappers.BookDomainMapper;
import com.wingoku.gameofthrones.domain.mappers.CategoryDomainMapper;
import com.wingoku.gameofthrones.domain.mappers.CharactorDomainMapper;
import com.wingoku.gameofthrones.domain.mappers.HouseDomainMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MapperModule {

    @Provides
    @Singleton
    public BookDomainMapper providesBookDomainMapper() {
        return new BookDomainMapper();
    }

    @Provides
    @Singleton
    public CharactorDomainMapper providesCharactorDomainMapper() {
        return new CharactorDomainMapper();
    }

    @Provides
    @Singleton
    public HouseDomainMapper providesHouseDomainMapper() {
        return new HouseDomainMapper();
    }

    @Provides
    @Singleton
    public CategoryDomainMapper providesCategoryDomainMapper() {
        return new CategoryDomainMapper();
    }
}
