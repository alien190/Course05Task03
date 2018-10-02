package com.example.alien.course04task02.di;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.alien.course04task02.data.model.DaoMaster;
import com.example.alien.course04task02.data.model.DaoSession;
import com.example.alien.course04task02.data.model.FilmDao;

import javax.inject.Inject;
import javax.inject.Provider;

class FilmDaoProvider implements Provider<FilmDao> {
    private Application mApplication;

    @Inject
    public FilmDaoProvider(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Override
    public FilmDao get() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mApplication, "films-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        return daoSession.getFilmDao();
    }
}
