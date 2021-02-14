package com.wingoku.gameofthrones.data.persistance.databases;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.wingoku.gameofthrones.data.persistance.converters.ListConverter;
import com.wingoku.gameofthrones.data.persistance.daos.GOTDao;
import com.wingoku.gameofthrones.domain.models.Book;
import com.wingoku.gameofthrones.domain.models.Category;
import com.wingoku.gameofthrones.domain.models.Charactor;
import com.wingoku.gameofthrones.domain.models.House;

@Database(entities = {Category.class, Book.class, Charactor.class, House.class}, version = 1, exportSchema = false)
@TypeConverters({ListConverter.class})
public abstract class GOTDatabase extends RoomDatabase {
    public abstract GOTDao getDao();
}
