package com.wingoku.gameofthrones.di.components;

import com.wingoku.gameofthrones.di.modules.GotRepoModule;
import com.wingoku.gameofthrones.domain.viewModels.BooksViewModel;
import com.wingoku.gameofthrones.domain.viewModels.CharactersViewModel;
import com.wingoku.gameofthrones.domain.viewModels.HousesViewModel;
import com.wingoku.gameofthrones.domain.viewModels.SharedViewModel;
import com.wingoku.gameofthrones.presentation.activities.MainActivity;
import com.wingoku.gameofthrones.presentation.application.GameOfThronesApplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {GotRepoModule.class})
public interface AppComponent {
    void inject(GameOfThronesApplication app);
    void inject(MainActivity app);
    void inject(BooksViewModel app);
    void inject(HousesViewModel app);
    void inject(CharactersViewModel app);
    void inject(SharedViewModel app);
}
