package com.example.alien.course04task02.data;

import com.example.alien.course04task02.data.model.Film;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Case;
import io.realm.Realm;
import io.realm.Sort;

public class RealmFilmRepository implements IFilmRepository {
    private static final int MIN_LENGTH_FOR_NAME_SEARCH = 3;
    private static final int MIN_LENGTH_FOR_DIRECTOR_SEARCH = 4;
    private AtomicLong currentId = new AtomicLong();
    private Realm mRealm;


    public RealmFilmRepository() {
        mRealm = Realm.getDefaultInstance();
        Number number = mRealm.where(Film.class).max("id");
        if (number != null) {
            currentId.set(number.longValue());
        } else {
            currentId.set(0);
        }

    }

    @Override
    public long insertItem(Film film) {
        film.setId(currentId.incrementAndGet());
        mRealm.beginTransaction();
        mRealm.copyToRealm(film);
        mRealm.commitTransaction();
        EventBus.getDefault().post(new OnFilmDataBaseUpdate());
        return film.getId();
    }

    @Override
    public void insertItems(List<Film> films) {
        for (Film film : films) {
            insertItem(film);
        }
    }

    @Override
    public Film getItem(long id) {
        return mRealm.copyFromRealm(getFilmById(id));
    }

    @Override
    public boolean deleteItem(long id) {
        boolean isSuccessful = false;
        Film film = getFilmById(id);
        mRealm.beginTransaction();
        if (film != null) {
            film.deleteFromRealm();
            isSuccessful = true;
        }
        mRealm.commitTransaction();
        EventBus.getDefault().post(new OnFilmDataBaseUpdate());
        return isSuccessful;
    }

    @Override
    public List<Film> getAll() {
        return mRealm.copyFromRealm(mRealm.where(Film.class)
                .findAll()
                .sort("id", Sort.ASCENDING));
    }

    @Override
    public void updateItem(Film Film) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(Film);
        mRealm.commitTransaction();
        EventBus.getDefault().post(new OnFilmDataBaseUpdate());
    }

    private Film getFilmById(long id) {
        return mRealm.where(Film.class).equalTo("id", id).findFirst();
    }

    public long createFilmAndSave(String name, String director, int year, double rating) {
        Film film = new Film(0L, name, year, director, rating);
        return insertItem(film);
    }

    @Override
    public List<Film> search(String query) {
        if (query != null && query.length() >= MIN_LENGTH_FOR_NAME_SEARCH) {
            query = "*" + query + "*";
            return mRealm.copyFromRealm(mRealm.where(Film.class)
                    .like("name", query, Case.INSENSITIVE)
                    .findAll());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Film> searchInBounds(int startYear, int endYear) {
        if (endYear == 0) {
            return mRealm.copyFromRealm(mRealm.where(Film.class)
                    .equalTo("year", startYear)
                    .sort("year", Sort.ASCENDING)
                    .findAll());
        } else {
            return mRealm.copyFromRealm(mRealm.where(Film.class)
                    .between("year", startYear, endYear)
                    .sort("year", Sort.ASCENDING)
                    .findAll());
        }
    }

    @Override
    public List<Film> searchByDirector(String name) {
        if (name == null || name.length() < MIN_LENGTH_FOR_DIRECTOR_SEARCH) {
            return new ArrayList<>();
        }
        return mRealm.copyFromRealm(mRealm.where(Film.class)
                .beginsWith("director", name, Case.INSENSITIVE)
                .findAll());
    }

    @Override
    public List<Film> getTopFilms(int count) {

        List<Film> retList = new ArrayList<>();

        List<Film> results = mRealm.copyFromRealm(mRealm.where(Film.class).sort("rating", Sort.DESCENDING).findAll());
        for (Film film : results) {
            if (retList.size() >= count) {
                break;
            }
            retList.add(film);
        }
        return retList;
    }


    @Override
    public void createFilmAndUpdate(long id, String name, String director, int year, double rating) {
        Film film = new Film(id, name, year, director, rating);
        updateItem(film);
    }

    class OnFilmDataBaseUpdate implements IOnFilmDataBaseUpdate {
    }
}
