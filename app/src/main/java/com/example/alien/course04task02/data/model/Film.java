package com.example.alien.course04task02.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Film extends RealmObject {
    @PrimaryKey
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private int year;
    private String director;
    private double rating;

    public Film() {
    }

    @Generated(hash = 1265539438)
    public Film(Long id, String name, int year, String director, double rating) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
