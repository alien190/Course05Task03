package com.example.alien.course05task03.data;

import com.example.alien.course05task03.data.model.Location;

import java.util.List;

public interface IFilmRepository {

    long insertItem(Location location);

    void insertItems(List<Location> locations);

    Location getItem(long id);

    boolean deleteItem(long id);

    List<Location> getAll();

    void updateItem(Location location);

    List<Location> search(String query);

    List<Location> searchInBounds(int startYear, int endYear);

    List<Location> searchByDirector(String name);

    List<Location> getTopFilms(int count);

    long createFilmAndSave(String name, String director, int year, double rating);

    void createFilmAndUpdate(long id, String name, String director, int year, double rating);

    interface IOnFilmDataBaseUpdate {}

}
