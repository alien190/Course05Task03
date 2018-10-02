package com.example.alien.course05task03.di;

import com.example.alien.course05task03.data.IFilmRepository;
import com.example.alien.course05task03.ui.common.ViewModelCustomFactory;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelCustomFactoryProvider implements Provider<ViewModelCustomFactory> {

    protected IFilmRepository mRepository;
    private Gson mGson;

    @Inject
    public ViewModelCustomFactoryProvider(IFilmRepository mRepository, Gson gson) {
        this.mRepository = mRepository;
        this.mGson = gson;
    }

    @Override
    public ViewModelCustomFactory get() {
        return new ViewModelCustomFactory(mRepository, mGson);
    }
}
