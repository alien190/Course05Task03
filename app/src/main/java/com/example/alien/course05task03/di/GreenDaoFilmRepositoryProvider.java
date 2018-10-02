package com.example.alien.course05task03.di;

import com.example.alien.course05task03.data.GreenDaoFilmRepository;
import com.example.alien.course05task03.data.model.FilmDao;

import javax.inject.Inject;
import javax.inject.Provider;

class GreenDaoFilmRepositoryProvider implements Provider<GreenDaoFilmRepository> {
    private FilmDao mFilmDao;

    @Inject
    public GreenDaoFilmRepositoryProvider(FilmDao mFilmDao) {
        this.mFilmDao = mFilmDao;
    }

    @Override
    public GreenDaoFilmRepository get() {
        return new GreenDaoFilmRepository(mFilmDao);
    }
}
