package com.wingoku.gameofthrones.domain.interfaces;

import androidx.lifecycle.LiveData;

import com.wingoku.gameofthrones.data.network.utils.Resource;
import com.wingoku.gameofthrones.domain.models.Book;
import com.wingoku.gameofthrones.domain.models.Category;
import com.wingoku.gameofthrones.domain.models.Charactor;
import com.wingoku.gameofthrones.domain.models.House;

import java.util.List;

public interface GotRepo {
    LiveData<Resource<List<Category>>> getCategories(int pageNum);
    LiveData<Resource<List<Book>>> getBooks(int pageNum);
    LiveData<Resource<List<House>>> getHouses();
    LiveData<Resource<List<Charactor>>> getCharacters();
}
