package com.wingoku.gameofthrones.domain.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wingoku.gameofthrones.data.network.utils.Resource;
import com.wingoku.gameofthrones.domain.interfaces.GotRepo;
import com.wingoku.gameofthrones.domain.models.Book;
import com.wingoku.gameofthrones.presentation.application.GameOfThronesApplication;

import java.util.List;

import javax.inject.Inject;

public class BooksViewModel extends AndroidViewModel {
    @Inject
    GotRepo repo;

    private LiveData<Resource<List<Book>>> booksLiveData;

    public BooksViewModel(@NonNull Application application) {
        super(application);

        ((GameOfThronesApplication) getApplication()).getAppComponent().inject(this);
        booksLiveData = repo.getBooks(1);
    }

    public LiveData<Resource<List<Book>>> getBooks() {
        return booksLiveData;
    }
}
