package com.example.alien.course05task03.data.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Location extends RealmObject {
    @PrimaryKey
    private Long id;
    private String city;
    private String country;
    private Integer duration;
    private String durationUnits;
    private Integer price;
    private String priceUnits;


    public Location() {
    }

    public Location(Long id, String city, String country, Integer duration, String durationUnits, Integer price, String priceUnits) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.duration = duration;
        this.durationUnits = durationUnits;
        this.price = price;
        this.priceUnits = priceUnits;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDurationUnits() {
        return durationUnits;
    }

    public void setDurationUnits(String durationUnits) {
        this.durationUnits = durationUnits;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPriceUnits() {
        return priceUnits;
    }

    public void setPriceUnits(String priceUnits) {
        this.priceUnits = priceUnits;
    }
}
