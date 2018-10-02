package com.example.alien.course05task03.di;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.DialogFragment;

import com.example.alien.course05task03.ui.locationDetail.LocationDetailViewModel;
import com.example.alien.course05task03.ui.locationDetail.LocationDetailViewModelCustomFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

class FilmDetailViewModelProvider implements Provider<LocationDetailViewModel> {
    @Inject
    protected DialogFragment mFragment;
    @Inject
    protected LocationDetailViewModelCustomFactory mFactory;
    @Inject
    @Named("FilmId")
    Long mFilmId;


    @Override
    public LocationDetailViewModel get() {
        return ViewModelProviders.of(mFragment, mFactory).get(String.valueOf(mFilmId),LocationDetailViewModel.class);
    }
}