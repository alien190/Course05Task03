package com.example.alien.course05task03.data;

import com.example.alien.course05task03.data.model.Location;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.Sort;

public class RealmLocationRepository implements ILocationRepository {
    private static final int MIN_LENGTH_FOR_NAME_SEARCH = 3;
    private static final int MIN_LENGTH_FOR_DIRECTOR_SEARCH = 4;
    private AtomicLong currentId = new AtomicLong();
    private Realm mRealm;


    public RealmLocationRepository() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        mRealm = Realm.getInstance(realmConfiguration);

        Number number = mRealm.where(Location.class).max("id");
        if (number != null) {
            currentId.set(number.longValue());
        } else {
            currentId.set(0);
        }

    }

    @Override
    public long insertItem(Location location) {
        location.setId(currentId.incrementAndGet());
        mRealm.beginTransaction();
        mRealm.copyToRealm(location);
        mRealm.commitTransaction();
        EventBus.getDefault().post(new OnFilmDataBaseUpdate());
        return location.getId();
    }

    @Override
    public void insertItems(List<Location> locations) {
        for (Location location : locations) {
            insertItem(location);
        }
    }

    @Override
    public Location getItem(long id) {
        return mRealm.copyFromRealm(getFilmById(id));
    }

    @Override
    public boolean deleteItem(long id) {
        boolean isSuccessful = false;
        Location location = getFilmById(id);
        mRealm.beginTransaction();
        if (location != null) {
            location.deleteFromRealm();
            isSuccessful = true;
        }
        mRealm.commitTransaction();
        EventBus.getDefault().post(new OnFilmDataBaseUpdate());
        return isSuccessful;
    }

    @Override
    public List<Location> getAll() {
        return mRealm.copyFromRealm(mRealm.where(Location.class)
                .findAll()
                .sort("id", Sort.ASCENDING));
    }

    @Override
    public void updateItem(Location Location) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(Location);
        mRealm.commitTransaction();
        EventBus.getDefault().post(new OnFilmDataBaseUpdate());
    }

    private Location getFilmById(long id) {
        return mRealm.where(Location.class).equalTo("id", id).findFirst();
    }

    public long createFilmAndSave(String name, String director, int year, double rating) {
        //Location location = new Location(0L, name, year, director, rating);
        //return insertItem(location);
        return 0;
    }

    @Override
    public List<Location> search(String query) {
        if (query != null && !query.isEmpty()) {
            query = "*" + query + "*";
            return mRealm.copyFromRealm(mRealm.where(Location.class)
                    .like("name", query, Case.INSENSITIVE)
                    .findAll());
        } else {
            return getAll();
        }
    }

    @Override
    public List<Location> searchInBounds(int startYear, int endYear) {
        if (endYear == 0) {
            return mRealm.copyFromRealm(mRealm.where(Location.class)
                    .equalTo("year", startYear)
                    .sort("year", Sort.ASCENDING)
                    .findAll());
        } else {
            return mRealm.copyFromRealm(mRealm.where(Location.class)
                    .between("year", startYear, endYear)
                    .sort("year", Sort.ASCENDING)
                    .findAll());
        }
    }

    @Override
    public List<Location> searchByDirector(String name) {
        if (name == null || name.length() < MIN_LENGTH_FOR_DIRECTOR_SEARCH) {
            return new ArrayList<>();
        }
        return mRealm.copyFromRealm(mRealm.where(Location.class)
                .beginsWith("director", name, Case.INSENSITIVE)
                .findAll());
    }

    @Override
    public List<Location> getTopFilms(int count) {

        List<Location> retList = new ArrayList<>();

        List<Location> results = mRealm.copyFromRealm(mRealm.where(Location.class).sort("rating", Sort.DESCENDING).findAll());
        for (Location location : results) {
            if (retList.size() >= count) {
                break;
            }
            retList.add(location);
        }
        return retList;
    }


    @Override
    public void createFilmAndUpdate(long id, String name, String director, int year, double rating) {
//        Location location = new Location(id, name, year, director, rating);
//        updateItem(location);
    }

    class OnFilmDataBaseUpdate implements IOnFilmDataBaseUpdate {
    }
}
