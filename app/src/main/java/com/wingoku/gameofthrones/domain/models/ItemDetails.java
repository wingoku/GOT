package com.wingoku.gameofthrones.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.LinkedHashMap;

public class ItemDetails implements Parcelable
{

    private String imageUrl;
    private LinkedHashMap<String, String> detailsMap;
    public final static Creator<ItemDetails> CREATOR = new Creator<ItemDetails>() {
        @SuppressWarnings({
                "unchecked"
        })
        public ItemDetails createFromParcel(Parcel in) {
            return new ItemDetails(in);
        }

        public ItemDetails[] newArray(int size) {
            return (new ItemDetails[size]);
        }
    };

    protected ItemDetails(Parcel in) {
        this.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.detailsMap = ((LinkedHashMap) in.readValue((LinkedHashMap.class.getClassLoader())));
    }

    public ItemDetails() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LinkedHashMap<String, String> getDetailsMap() {
        return detailsMap;
    }

    public void setDetailsMap(LinkedHashMap<String, String> detailsMap) {
        this.detailsMap = detailsMap;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(imageUrl);
        dest.writeValue(detailsMap);
    }

    public int describeContents() {
        return 0;
    }
}