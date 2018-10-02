package com.example.alien.course04task02.di;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.alien.course04task02.data.GreenDaoFilmRepository;
import com.example.alien.course04task02.data.model.DaoMaster;
import com.example.alien.course04task02.data.model.DaoSession;
import com.example.alien.course04task02.data.model.FilmDao;

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
