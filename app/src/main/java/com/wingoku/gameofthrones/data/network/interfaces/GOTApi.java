package com.wingoku.gameofthrones.data.network.interfaces;

import androidx.lifecycle.LiveData;

import com.wingoku.gameofthrones.data.network.models.BookDTO;
import com.wingoku.gameofthrones.data.network.models.CategoryDTO;
import com.wingoku.gameofthrones.data.network.models.CharactorDTO;
import com.wingoku.gameofthrones.data.network.models.HouseDTO;
import com.wingoku.gameofthrones.data.network.models.responses.ApiResponse;

import java.util.List;

import retrofit2.http.GET;

public interface GOTApi {

    @GET("categories")
    LiveData<ApiResponse<List<CategoryDTO>>> getCategories();

    @GET("list/1")
    LiveData<ApiResponse<List<BookDTO>>> getBooks();

    @GET("list/2")
    LiveData<ApiResponse<List<HouseDTO>>> getHouses();

    @GET("list/3")
    LiveData<ApiResponse<List<CharactorDTO>>> getCharacters();
}
