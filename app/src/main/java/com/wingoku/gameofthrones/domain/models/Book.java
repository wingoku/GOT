package com.wingoku.gameofthrones.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "book", indices = {@Index(value = {"name"},
        unique = true)})
public class Book implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int _pid;
    private String name;
    private String isbn;
    private List<String> authors = null;
    private int numberOfPages;
    private String publisher;
    private String country;
    private String mediaType;
    private String released;

    public Book() {
    }

    public Book(String name, String isbn, List<String> authors, int numberOfPages, String publisher, String country, String mediaType, String released) {
        this.name = name;
        this.isbn = isbn;
        this.authors = authors;
        this.numberOfPages = numberOfPages;
        this.publisher = publisher;
        this.country = country;
        this.mediaType = mediaType;
        this.released = released;
    }

    public final static Creator<Book> CREATOR = new Creator<Book>() {
        @SuppressWarnings({
                "unchecked"
        })
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        public Book[] newArray(int size) {
            return (new Book[size]);
        }
    };

    protected Book(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.isbn = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.authors, (String.class.getClassLoader()));
        this.numberOfPages = ((int) in.readValue((int.class.getClassLoader())));
        this.publisher = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.mediaType = ((String) in.readValue((String.class.getClassLoader())));
        this.released = ((String) in.readValue((String.class.getClassLoader())));
    }

    public int get_pid() {
        return _pid;
    }

    public void set_pid(int _pid) {
        this._pid = _pid;
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