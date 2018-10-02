package com.example.alien.course05task03.di;

import com.example.alien.course05task03.data.ILocationRepository;
import com.example.alien.course05task03.ui.locationDetail.LocationDetailViewModelCustomFactory;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class FilmDetailViewModelCustomFactoryProvider implements Provider<LocationDetailViewModelCustomFactory> {

    protected ILocationRepository mRepository;
    private Gson mGson;
    private Long mFilmId;

    @Inject
    public FilmDetailViewModelCustomFactoryProvider(ILocationRepository mRepository, Gson gson, @Named("FilmId") Long filmId) {
        this.mRepository = mRepository;
        this.mGson = gson;
        this.mFilmId = filmId;
    }

    @Override
    public LocationDetailViewModelCustomFactory get() {
        return new LocationDetailViewModelCustomFactory(mRepository, mGson, mFilmId);
    }
}
