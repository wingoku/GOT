package com.wingoku.gameofthrones.domain.models;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "house", indices = {@Index(value = {"name"},
        unique = true)})
public class House {
    @PrimaryKey(autoGenerate = true)
    private int _pid;
    private String id;
    private String name;
    private String region;
    private String title;

    public House() {}

    public House(String id, String name, String region, String title) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.title = title;
    }

    public int get_pid() {
        return _pid;
    }

    public void set_pid(int _pid) {
        this._pid = _pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}