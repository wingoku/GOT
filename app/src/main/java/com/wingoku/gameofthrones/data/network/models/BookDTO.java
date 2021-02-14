package com.wingoku.gameofthrones.data.network.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookDTO implements Parcelable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("authors")
    @Expose
    private List<String> authors = null;
    @SerializedName("numberOfPages")
    @Expose
    private int numberOfPages;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("mediaType")
    @Expose
    private String mediaType;
    @SerializedName("released")
    @Expose
    private String released;

    public BookDTO() {
    }

    public BookDTO(String name, String isbn, List<String> authors, int numberOfPages, String publisher, String country, String mediaType, String released) {
        this.name = name;
        this.isbn = isbn;
        this.authors = authors;
        this.numberOfPages = numberOfPages;
        this.publisher = publisher;
        this.country = country;
        this.mediaType = mediaType;
        this.released = released;
    }

    public final static Parcelable.Creator<BookDTO> CREATOR = new Creator<BookDTO>() {
        @SuppressWarnings({
                "unchecked"
        })
        public BookDTO createFromParcel(Parcel in) {
            return new BookDTO(in);
        }

        public BookDTO[] newArray(int size) {
            return (new BookDTO[size]);
        }
    };

    protected BookDTO(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.isbn = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.authors, (java.lang.String.class.getClassLoader()));
        this.numberOfPages = ((int) in.readValue((int.class.getClassLoader())));
        this.publisher = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.mediaType = ((String) in.readValue((String.class.getClassLoader())));
        this.released = ((String) in.readValue((String.class.getClassLoader())));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(isbn);
        dest.writeList(authors);
        dest.writeValue(numberOfPages);
        dest.writeValue(publisher);
        dest.writeValue(country);
        dest.writeValue(mediaType);
        dest.writeValue(released);
    }

    public int describeContents() {
        return 0;
    }
}