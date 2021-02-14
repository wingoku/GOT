package com.wingoku.gameofthrones.domain.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wingoku.gameofthrones.data.network.utils.Resource;
import com.wingoku.gameofthrones.domain.interfaces.GotRepo;
import com.wingoku.gameofthrones.domain.models.House;
import com.wingoku.gameofthrones.presentation.application.GameOfThronesApplication;
import com.wingoku.gameofthrones.utils.Utils;

import java.util.List;

import javax.inject.Inject;

public class HousesViewModel extends AndroidViewModel {
    @Inject
    GotRepo repo;

    private LiveData<Resource<List<House>>> housesLiveData;

    public HousesViewModel(@NonNull Application application) {
        super(application);

        ((GameOfThronesApplication) getApplication()).getAppComponent().inject(this);

        fetchData();
    }

    public void fetchData() {
        housesLiveData = repo.getHouses();
    }

    public LiveData<Resource<List<House>>> getHouses() {

        if((housesLiveData == null || housesLiveData.getValue().data == null|| housesLiveData.getValue().data.size() == 0) && Utils.isNetworkAvailable(getApplication().getApplicationContext()))
            fetchData();

        return housesLiveData;
    }
}
