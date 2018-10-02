package com.example.alien.course05task03.ui.locationDetail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.alien.course05task03.data.ILocationRepository;
import com.google.gson.Gson;

public class LocationDetailViewModelCustomFactory implements ViewModelProvider.Factory {
    private ILocationRepository mRepository;
    private Gson mGson;
    private Long mFilmId;

    public LocationDetailViewModelCustomFactory(ILocationRepository repository, Gson gson, Long filmId) {

        this.mRepository = repository;
        this.mGson = gson;
        this.mFilmId = filmId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LocationDetailViewModel(mRepository, mGson, mFilmId);

    }
}
