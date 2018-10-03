package com.example.alien.course05task03.ui.locationDetail;


import com.example.alien.course05task03.data.ILocationRepository;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

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
