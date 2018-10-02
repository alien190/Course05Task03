package com.example.alien.course04task02.data;

import com.example.alien.course04task02.data.model.Film;
import com.example.alien.course04task02.data.model.FilmDao;
import com.example.alien.course04task02.data.model.FilmDao.Properties;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


public class GreenDaoFilmRepository implements IFilmRepository {

    private FilmDao mFilmDao;

    private static final int MIN_LENGTH_FOR_NAME_SEARCH = 3;
    private static final int MIN_LENGTH_FOR_DIRECTOR_SEARCH = 4;

    public GreenDaoFilmRepository(FilmDao mFilmDao) {
        this.mFilmDao = mFilmDao;

    }

    @Override
    public long insertItem(Film film) {
        film.setId(null);
        Long id = mFilmDao.insert(film);
        EventBus.getDefault().post(new OnFilmDataBaseUpdate());
        return id;
    }

    @Override
    public void insertItems(List<Film> films) {
        for (Film film : films) {
            insertItem(film);
        }
    }

    @Override
    public Film getItem(long id) {
        List<Film> films = mFilmDao.queryBuilder()
                .where(Properties.Id.eq(id))
                .list();
        if (films.isEmpty()) {
            return null;
        } else {
            return films.get(0);
        }
    }

    @Override
    public boolean deleteItem(long id) {
        mFilmDao.queryBuilder()
                .where(Properties.Id.eq(id))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();
        EventBus.getDefault().post(new OnFilmDataBaseUpdate());
        //todo как получить резултат удаления?
        return true;
    }

    @Override
    public List<Film> getAll() {
        return mFilmDao.queryBuilder().list();
    }

    @Override
    public void updateItem(Film film) {
        mFilmDao.update(film);
        EventBus.getDefault().post(new OnFilmDataBaseUpdate());
    }

    @Override
    public List<Film> search(String query) {
        if (query != null && query.length() >= MIN_LENGTH_FOR_NAME_SEARCH) {
            query = "%" + query + "%";
        } else {
            query = "";
        }

        return mFilmDao.queryBuilder()
                .where(FilmDao.Properties.Name.like(query))
                .list();
    }

    @Override
    public List<Film> searchInBounds(int startYear, int endYear) {
        if (endYear == 0) {
            return mFilmDao.queryBuilder()
                    .where(Properties.Year.eq(startYear))
                    .orderAsc(Properties.Year)
                    .list();
        } else {
            return mFilmDao.queryBuilder()
                    .where(Properties.Year.between(startYear, endYear))
                    .orderAsc(Properties.Year)
                    .list();
        }
    }

    @Override
    public List<Film> searchByDirector(String name) {
        if (name != null && name.length() >= MIN_LENGTH_FOR_DIRECTOR_SEARCH) {
            return mFilmDao.queryBuilder()
                    .where(Properties.Director.like(name + "%"))
                    .list();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Film> getTopFilms(int count) {
        if (count < 0) {
            count = 0;
        }
        return mFilmDao.queryBuilder()
                .orderDesc(Properties.Rating)
                .limit(count)
                .list();
    }

    @Override
    public long createFilmAndSave(String name, String director, int year, double rating) {
        Film film = new Film(null, name, year, director, rating);
        return insertItem(film);
    }

    @Override
    public void createFilmAndUpdate(long id, String name, String director, int year, double rating) {
        Film film = new Film(id, name, year, director, rating);
        updateItem(film);
    }

    class OnFilmDataBaseUpdate implements IOnFilmDataBaseUpdate {}
}
