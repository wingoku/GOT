package com.wingoku.gameofthrones.data.persistance.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wingoku.gameofthrones.domain.models.Book;
import com.wingoku.gameofthrones.domain.models.Category;
import com.wingoku.gameofthrones.domain.models.Charactor;
import com.wingoku.gameofthrones.domain.models.House;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface GOTDao {
    @Insert(onConflict = IGNORE)
    long[] insertCategories(Category... category);

    @Insert(onConflict = IGNORE)
    void insertCategory(Category category);

    @Insert(onConflict = IGNORE)
    long[] insertBooks(Book... book);

    @Insert(onConflict = IGNORE)
    long[] insertHouses(House... house);

    @Insert(onConflict = IGNORE)
    long[] insertCharacter(Charactor... character);

    @Query("SELECT * FROM Book WHERE name = :name")
    LiveData<Charactor> getBook(String name);

    @Query("SELECT * FROM charactor WHERE id = :id")
    LiveData<Charactor> getCharacter(String id);

    @Query("SELECT * FROM Category WHERE categoryName = :name")
    LiveData<Charactor> getCategory(String name);

    @Query("SELECT * FROM House WHERE id = :id")
    LiveData<Charactor> getHouse(String id);

    @Query("SELECT * FROM Book ORDER BY name DESC LIMIT (:pageNum * 10)")
    LiveData<List<Book>> getBooks(int pageNum);

    @Query("SELECT * FROM Category")
    LiveData<List<Category>> getCategories();

    @Query("SELECT * FROM House ORDER BY name DESC LIMIT (:pageNum * 10)")
    LiveData<List<House>> getHouses(int pageNum);

    @Query("SELECT * FROM charactor ORDER BY name DESC LIMIT (:pageNum * 10)")
    LiveData<List<Charactor>> getCharacters(int pageNum);
}
