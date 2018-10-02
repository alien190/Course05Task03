package com.example.alien.course05task03.di;

import com.example.alien.course05task03.data.IFilmRepository;
import com.example.alien.course05task03.ui.filmDetail.FilmDetailViewModelCustomFactory;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class FilmDetailViewModelCustomFactoryProvider implements Provider<FilmDetailViewModelCustomFactory> {

    protected IFilmRepository mRepository;
    private Gson mGson;
    private Long mFilmId;

    @Inject
    public FilmDetailViewModelCustomFactoryProvider(IFilmRepository mRepository, Gson gson, @Named("FilmId") Long filmId) {
        this.mRepository = mRepository;
        this.mGson = gson;
        this.mFilmId = filmId;
    }

    @Override
    public FilmDetailViewModelCustomFactory get() {
        return new FilmDetailViewModelCustomFactory(mRepository, mGson, mFilmId);
    }
}
