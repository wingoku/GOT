package com.wingoku.gameofthrones.data.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.wingoku.gameofthrones.data.network.interfaces.GOTApi;
import com.wingoku.gameofthrones.data.network.models.BookDTO;
import com.wingoku.gameofthrones.data.network.models.CategoryDTO;
import com.wingoku.gameofthrones.data.network.models.CharactorDTO;
import com.wingoku.gameofthrones.data.network.models.HouseDTO;
import com.wingoku.gameofthrones.data.network.models.responses.ApiResponse;
import com.wingoku.gameofthrones.data.network.utils.NetworkBoundResource;
import com.wingoku.gameofthrones.data.network.utils.Resource;
import com.wingoku.gameofthrones.data.persistance.daos.GOTDao;
import com.wingoku.gameofthrones.data.persistance.databases.GOTDatabase;
import com.wingoku.gameofthrones.domain.interfaces.GotRepo;
import com.wingoku.gameofthrones.domain.mappers.BookDomainMapper;
import com.wingoku.gameofthrones.domain.mappers.CategoryDomainMapper;
import com.wingoku.gameofthrones.domain.mappers.CharactorDomainMapper;
import com.wingoku.gameofthrones.domain.mappers.HouseDomainMapper;
import com.wingoku.gameofthrones.domain.models.Book;
import com.wingoku.gameofthrones.domain.models.Category;
import com.wingoku.gameofthrones.domain.models.Charactor;
import com.wingoku.gameofthrones.domain.models.House;

import java.util.List;

public class GotRepoImpl implements GotRepo {

    private static final String TAG = "GOTClientRepo";
    private GOTApi gotApi;
    private GOTDao gotDao;
    private GOTDatabase gotDatabase;
    private CategoryDomainMapper categoryDomainMapper;
    private HouseDomainMapper houseDomainMapper;
    private BookDomainMapper bookDomainMapper;
    private CharactorDomainMapper charactorDomainMapper;

    public GotRepoImpl(GOTDatabase db, GOTApi gotApi, CategoryDomainMapper categoryDomainMapper,
                       HouseDomainMapper houseDomainMapper, BookDomainMapper bookDomainMapper, CharactorDomainMapper charactorDomainMapper) {
        Log.e(TAG, "GOTClientRepo: inside construvtor");
        this.gotApi = gotApi;
        this.gotDatabase = db;
        this.categoryDomainMapper = categoryDomainMapper;
        this.houseDomainMapper = houseDomainMapper;
        this.bookDomainMapper = bookDomainMapper;
        this.charactorDomainMapper = charactorDomainMapper;

        this.gotDao = this.gotDatabase.getDao();
    }

    public LiveData<Resource<List<Category>>> getCategories(int pageNum) {
        Log.d(TAG, "getCategories()");

        return new NetworkBoundResource<List<Category>, List<CategoryDTO>>() {
            @Override
            protected List<Category> mapToDomainObject(List<CategoryDTO> dto) {
                return categoryDomainMapper.fromDTO(dto);
            }

            @Override
            protected void saveCallResult(@NonNull List<Category> item) {
                Log.d(TAG, "saveCallResult()");
                if(item == null || item.size() != 0) {
                    Log.d(TAG, "saveCallResult: saving in db");
                    gotDao.insertCategories(item.toArray(new Category[item.size()]));
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Category> data) {
                Log.d(TAG, "shouldFetch() Categories : "+ (data == null || data.size() == 0));
                return data == null || data.size() == 0;
            }

            @NonNull
            @Override
            protected LiveData<List<Category>> loadFromDb() {
                return gotDao.getCategories();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<CategoryDTO>>> createCall() {
                LiveData<ApiResponse<List<CategoryDTO>>>  result = gotApi.getCategories();
                Log.d(TAG, "createCall() Categories");
                return result;
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<List<Book>>> getBooks(int pageNum) {
        Log.d(TAG, "getBooks()");
        return new NetworkBoundResource<List<Book>, List<BookDTO>>() {
            @Override
            protected List<Book> mapToDomainObject(List<BookDTO> dto) {
                return bookDomainMapper.fromDTO(dto);
            }

            @Override
            protected void saveCallResult(@NonNull List<Book> item) {
                Log.d(TAG, "saveCallResult()");
                if(item == null || item.size() != 0) {
                    Log.d(TAG, "saveCallResult: saving in db");
                    gotDao.insertBooks(item.toArray(new Book[item.size()]));
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Book> data) {
                Log.d(TAG, "shouldFetch() : books");
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Book>> loadFromDb() {
                return gotDao.getBooks(pageNum);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<BookDTO>>> createCall() {
                LiveData<ApiResponse<List<BookDTO>>>  result = gotApi.getBooks();
                Log.d(TAG, "createCall() books");
                return result;
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<List<House>>> getHouses() {

        Log.d(TAG, "getHouses()");
        return new NetworkBoundResource<List<House>, List<HouseDTO>>() {
            @Override
            protected List<House> mapToDomainObject(List<HouseDTO> dto) {
                return houseDomainMapper.fromDTO(dto);
            }

            @Override
            protected void saveCallResult(@NonNull List<House> item) {
                Log.d(TAG, "saveCallResult()");
                if(item == null || item.size() != 0) {
                    Log.d(TAG, "saveCallResult: saving in db");
                    gotDao.insertHouses(item.toArray(new House[item.size()]));
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<House> data) {
                Log.d(TAG, "shouldFetch() : Houses");
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<House>> loadFromDb() {
                return gotDao.getHouses(1);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<HouseDTO>>> createCall() {
                LiveData<ApiResponse<List<HouseDTO>>>  result = gotApi.getHouses();
                Log.d(TAG, "createCall() House");
                return result;
            }
        }.getAsLiveData();
    }
    
    public LiveData<Resource<List<Charactor>>> getCharacters() {
        Log.d(TAG, "getCharacters()");
        return new NetworkBoundResource<List<Charactor>, List<CharactorDTO>>() {
            @Override
            protected List<Charactor> mapToDomainObject(List<CharactorDTO> dto) {
                return charactorDomainMapper.fromDTO(dto);
            }

            @Override
            protected void saveCallResult(@NonNull List<Charactor> item) {
                Log.d(TAG, "saveCallResult()");
                if(item == null || item.size() != 0) {
                    Log.d(TAG, "saveCallResult: saving in db");
                    gotDao.insertCharacter(item.toArray(new Charactor[item.size()]));
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Charactor> data) {
                Log.d(TAG, "shouldFetch() : Charactor");
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Charactor>> loadFromDb() {
                LiveData<List<Charactor>> result = gotDao.getCharacters(1);
                return result;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<CharactorDTO>>> createCall() {
                LiveData<ApiResponse<List<CharactorDTO>>>  result = gotApi.getCharacters();
                Log.d(TAG, "createCall() Charactors");
                return result;
            }
        }.getAsLiveData();
    }
}
