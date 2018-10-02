package com.example.alien.course05task03.di;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;

import com.example.alien.course05task03.ui.locationDetail.LocationDetailViewModel;
import com.example.alien.course05task03.ui.locationDetail.LocationDetailViewModelCustomFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

class LocationDetailViewModelProvider implements Provider<LocationDetailViewModel> {
    @Inject
    protected Fragment mFragment;
    @Inject
    protected LocationDetailViewModelCustomFactory mFactory;
    @Inject
    @Named("LocationId")
    Long mLocationId;


    @Override
    public LocationDetailViewModel get() {
        return ViewModelProviders.of(mFragment, mFactory).get(String.valueOf(mLocationId),LocationDetailViewModel.class);
    }
}