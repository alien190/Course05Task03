package com.example.alien.course05task03.data.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Location extends RealmObject {
    @PrimaryKey
    private Long id;
    private String city;
    private String country;
    private Integer duration;
    private String durationUnit;
    private Integer price;
    private String priceUnit;
    private String imageBase64;
    private String hotelImageBase64;
    private String hotelName;
    private String hotelAddress;
    private Integer hotelPrice;
    private String hotelPriceUnit;


    public Location() {
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

    public String getDurationUnit() {
        return durationUnit;
    }

    public void setDurationUnit(String durationUnit) {
        this.durationUnit = durationUnit;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getHotelImageBase64() {
        return hotelImageBase64;
    }

    public void setHotelImageBase64(String hotelImageBase64) {
        this.hotelImageBase64 = hotelImageBase64;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(Integer hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public String getHotelPriceUnit() {
        return hotelPriceUnit;
    }

    public void setHotelPriceUnit(String hotelPriceUnit) {
        this.hotelPriceUnit = hotelPriceUnit;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }
}
