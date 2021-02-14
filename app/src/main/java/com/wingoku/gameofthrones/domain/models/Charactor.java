package com.wingoku.gameofthrones.domain.models;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "charactor", indices = {@Index(value = {"name"},
        unique = true)})
public class Charactor {
    @PrimaryKey(autoGenerate = true)
    private int _pid;
    private String id;
    private String name;
    private String gender;
    private String culture;
    private String born;
    private String died;
    private List<String> titles = null;
    private List<String> aliases = null;
    private String father;
    private String mother;
    private String spouse;
    private List<String> allegiances = null;
    private List<String> playedBy = null;

    public Charactor(String id, String name, String gender, String culture, String born, String died, List<String> titles, List<String> aliases, String father, String mother, String spouse, List<String> allegiances, List<String> playedBy) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.culture = culture;
        this.born = born;
        this.died = died;
        this.titles = titles;
        this.aliases = aliases;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
        this.allegiances = allegiances;
        this.playedBy = playedBy;
    }

    public Charactor() {}

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getDied() {
        return died;
    }

    public void setDied(String died) {
        this.died = died;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public List<String> getAllegiances() {
        return allegiances;
    }

    public void setAllegiances(List<String> allegiances) {
        this.allegiances = allegiances;
    }

    public List<String> getPlayedBy() {
        return playedBy;
    }

    public void setPlayedBy(List<String> playedBy) {
        this.playedBy = playedBy;
    }

}