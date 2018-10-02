package com.example.alien.course05task03.ui.filmList;

import com.example.alien.course05task03.data.IFilmRepository;
import com.example.alien.course05task03.data.model.Location;
import com.example.alien.course05task03.ui.common.BaseViewModel;
import com.google.gson.Gson;

import java.util.List;

public class ListAllViewModel extends BaseViewModel {

    public ListAllViewModel(IFilmRepository repository, Gson gson) {
        super(repository, gson);
        updateFromRepository();
    }

    @Override
    protected void updateFromRepository() {
        List<Location> locations = mRepository.getAll();
        mFilmList.postValue(locations);
    }
}
