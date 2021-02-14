package com.wingoku.gameofthrones.domain.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.wingoku.gameofthrones.data.network.utils.Resource;
import com.wingoku.gameofthrones.domain.interfaces.GotRepo;
import com.wingoku.gameofthrones.domain.models.Category;
import com.wingoku.gameofthrones.presentation.application.GameOfThronesApplication;

import java.util.List;

import javax.inject.Inject;

public class SharedViewModel extends AndroidViewModel {
    @Inject
    GotRepo repo;

    private MediatorLiveData<Resource<List<Category>>> catMediatorLiveData = new MediatorLiveData<>();

    public SharedViewModel(@NonNull Application application) {
        super(application);
        ((GameOfThronesApplication) getApplication()).getAppComponent().inject(this);
    }

    public void setCategoryData(Resource<List<Category>> catData) {

        catMediatorLiveData.setValue(catData);
    }

    public LiveData<Resource<List<Category>>> getCategoryData() {
        return catMediatorLiveData;
    }

    public LiveData<Resource<List<Category>>> returnLiveData() {
        return repo.getCategories(1);
    }
}
