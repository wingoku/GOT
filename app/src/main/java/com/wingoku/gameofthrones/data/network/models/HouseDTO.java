package com.wingoku.gameofthrones.data.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HouseDTO {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("title")
    @Expose
    private String title;

    public HouseDTO() {}

    public HouseDTO(String id, String name, String region, String title) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.title = title;
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