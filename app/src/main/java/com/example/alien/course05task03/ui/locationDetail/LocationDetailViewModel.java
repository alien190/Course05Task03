package com.example.alien.course05task03.ui.locationDetail;



import com.example.alien.course05task03.data.ILocationRepository;
import com.example.alien.course05task03.data.model.Location;
import com.example.alien.course05task03.ui.common.BaseViewModel;
import com.google.gson.Gson;

import androidx.lifecycle.MutableLiveData;


public class LocationDetailViewModel extends BaseViewModel {
    private Long mLocationId;
    private MutableLiveData<String> mCity = new MutableLiveData<>();
    private MutableLiveData<String> mDuration = new MutableLiveData<>();
    private MutableLiveData<String> mImageBase64 = new MutableLiveData<>();
    private MutableLiveData<String> mHotelImageBase64 = new MutableLiveData<>();
    private MutableLiveData<String> mHotelName = new MutableLiveData<>();
    private MutableLiveData<String> mHotelAddress = new MutableLiveData<>();
    private MutableLiveData<String> mHotelPrice = new MutableLiveData<>();


    public LocationDetailViewModel(ILocationRepository repository, Gson gson, Long locationId) {
        super(repository, gson);
        mLocationId = locationId;
    }

    private void loadFilm() {
        Location location = mRepository.getItem(mLocationId);
        mCity.postValue(location.getCity());
        mDuration.postValue(String.valueOf(location.getDuration()) + " " + location.getDurationUnit());
        mImageBase64.postValue(location.getImageBase64());
        mHotelImageBase64.postValue(location.getHotelImageBase64());
        mHotelName.postValue(location.getHotelName());
        mHotelAddress.postValue(location.getHotelAddress());
        mHotelPrice.postValue(location.getHotelPriceUnit() + String.valueOf(location.getHotelPrice()));
    }

    @Override
    protected void updateFromRepository() {
        loadFilm();
    }

    public Long getLocationId() {
        return mLocationId;
    }

    public void setLocationId(Long locationId) {
        mLocationId = locationId;
    }

    public MutableLiveData<String> getCity() {
        return mCity;
    }

    public void setCity(MutableLiveData<String> city) {
        mCity = city;
    }

    public MutableLiveData<String> getDuration() {
        return mDuration;
    }

    public void setDuration(MutableLiveData<String> duration) {
        mDuration = duration;
    }

    public MutableLiveData<String> getImageBase64() {
        return mImageBase64;
    }

    public void setImageBase64(MutableLiveData<String> imageBase64) {
        mImageBase64 = imageBase64;
    }

    public MutableLiveData<String> getHotelImageBase64() {
        return mHotelImageBase64;
    }

    public void setHotelImageBase64(MutableLiveData<String> hotelImageBase64) {
        mHotelImageBase64 = hotelImageBase64;
    }

    public MutableLiveData<String> getHotelName() {
        return mHotelName;
    }

    public void setHotelName(MutableLiveData<String> hotelName) {
        mHotelName = hotelName;
    }

    public MutableLiveData<String> getHotelAddress() {
        return mHotelAddress;
    }

    public void setHotelAddress(MutableLiveData<String> hotelAddress) {
        mHotelAddress = hotelAddress;
    }

    public MutableLiveData<String> getHotelPrice() {
        return mHotelPrice;
    }

    public void setHotelPrice(MutableLiveData<String> hotelPrice) {
        mHotelPrice = hotelPrice;
    }
}
