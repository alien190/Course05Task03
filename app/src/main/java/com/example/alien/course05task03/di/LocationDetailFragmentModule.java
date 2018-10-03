package com.example.alien.course05task03.di;



import android.support.v4.app.Fragment;

import com.example.alien.course05task03.ui.locationDetail.LocationDetailViewModel;
import com.example.alien.course05task03.ui.locationDetail.LocationDetailViewModelCustomFactory;

import toothpick.config.Module;

public class LocationDetailFragmentModule extends Module {

    private Fragment mFragment;
    private Long mLocationId;

    public LocationDetailFragmentModule(Fragment fragment, long locationId) {
        this.mFragment = fragment;
        this.mLocationId = locationId;

        bind(Fragment.class).toInstance(mFragment);
        bind(LocationDetailViewModel.class).toProvider(LocationDetailViewModelProvider.class);
        bind(Long.class).withName("LocationId").toInstance(mLocationId);
        bind(LocationDetailViewModelCustomFactory.class).toProvider(LocationDetailViewModelCustomFactoryProvider.class);
    }
}
