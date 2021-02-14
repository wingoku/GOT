package com.wingoku.gameofthrones.domain.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wingoku.gameofthrones.data.network.utils.Resource;
import com.wingoku.gameofthrones.domain.interfaces.GotRepo;
import com.wingoku.gameofthrones.domain.models.Charactor;
import com.wingoku.gameofthrones.presentation.application.GameOfThronesApplication;

import java.util.List;

import javax.inject.Inject;

public class CharactersViewModel extends AndroidViewModel {
    @Inject
    GotRepo repo;

    private LiveData<Resource<List<Charactor>>> charactersLiveData;

    public CharactersViewModel(@NonNull Application application) {
        super(application);

        ((GameOfThronesApplication) getApplication()).getAppComponent().inject(this);
        charactersLiveData = repo.getCharacters();
    }

    public LiveData<Resource<List<Charactor>>> getCharacters() {
        return charactersLiveData;
    }
}
