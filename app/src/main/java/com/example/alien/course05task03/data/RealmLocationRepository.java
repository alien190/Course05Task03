package com.example.alien.course05task03.data;

import com.example.alien.course05task03.data.model.Location;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.Sort;

public class RealmLocationRepository implements ILocationRepository {
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
        EventBus.getDefault().post(new OnLocationDataBaseUpdate());
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
        return mRealm.copyFromRealm(getLocationById(id));
    }

    @Override
    public boolean deleteItem(long id) {
        boolean isSuccessful = false;
        Location location = getLocationById(id);
        mRealm.beginTransaction();
        if (location != null) {
            location.deleteFromRealm();
            isSuccessful = true;
        }
        mRealm.commitTransaction();
        EventBus.getDefault().post(new OnLocationDataBaseUpdate());
        return isSuccessful;
    }

    @Override
    public List<Location> getAll() {
        return mRealm.copyFromRealm(mRealm.where(Location.class)
                .findAll()
                .sort("id", Sort.ASCENDING));
    }


    private Location getLocationById(long id) {
        return mRealm.where(Location.class).equalTo("id", id).findFirst();
    }

    @Override
    public List<Location> search(String query) {
        if (query != null && !query.isEmpty()) {
            query = "*" + query + "*";
            return mRealm.copyFromRealm(mRealm.where(Location.class)
                    .like("city", query, Case.INSENSITIVE)
                    .or()
                    .like("country", query, Case.INSENSITIVE)
                    .findAll());
        } else {
            return getAll();
        }
    }

    @Override
    public long getItemsCount() {
        return mRealm.where(Location.class).count();
    }

    class OnLocationDataBaseUpdate implements IOnLocationDataBaseUpdate {
    }
}
