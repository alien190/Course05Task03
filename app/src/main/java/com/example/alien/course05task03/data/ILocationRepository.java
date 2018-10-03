package com.example.alien.course05task03.data;

import com.example.alien.course05task03.data.model.Location;

import java.util.List;

public interface ILocationRepository {

    long insertItem(Location location);

    void insertItems(List<Location> locations);

    Location getItem(long id);

    boolean deleteItem(long id);

    List<Location> getAll();

    List<Location> search(String query);

    long getItemsCount();

    interface IOnLocationDataBaseUpdate {
    }

}
