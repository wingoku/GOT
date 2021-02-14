package com.wingoku.gameofthrones.data.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryDTO {
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("type")
    @Expose
    private int type;

    public CategoryDTO() {}

    public CategoryDTO(String categoryName, int type) {
        this.categoryName = categoryName;
        this.type = type;
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