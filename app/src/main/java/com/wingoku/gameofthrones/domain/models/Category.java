package com.wingoku.gameofthrones.domain.models;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "category", indices = {@Index(value = {"categoryName", "type"},
        unique = true)})
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int _pid;
    public String categoryName;
    public int type;

    public Category() {}

    public Category(String categoryName, int type) {
        this.categoryName = categoryName;
        this.type = type;
    }

    public int get_pid() {
        return _pid;
    }

    public void set_pid(int _pid) {
        this._pid = _pid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}