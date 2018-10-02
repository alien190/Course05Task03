package com.example.alien.course05task03.di;


import android.support.v4.app.Fragment;

import com.example.alien.course05task03.ui.locationDetail.LocationDetailViewModel;
import com.example.alien.course05task03.ui.locationDetail.LocationDetailViewModelCustomFactory;

import toothpick.config.Module;

public class LocationDetailFragmentModule extends Module {

    private Fragment mFragment;
    private Long mFilmId;

    public LocationDetailFragmentModule(Fragment fragment, long filmId) {
        this.mFragment = fragment;
        this.mFilmId = filmId;

        bind(Fragment.class).toInstance(mFragment);
        bind(LocationDetailViewModel.class).toProvider(FilmDetailViewModelProvider.class);
        bind(Long.class).withName("FilmId").toInstance(mFilmId);
        bind(LocationDetailViewModelCustomFactory.class).toProvider(FilmDetailViewModelCustomFactoryProvider.class);
    }
}
