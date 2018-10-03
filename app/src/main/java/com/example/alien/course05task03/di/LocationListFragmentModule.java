package com.example.alien.course05task03.di;


import android.support.v4.app.Fragment;

import com.example.alien.course05task03.ui.locationList.LocationListAdapter;
import com.example.alien.course05task03.ui.locationList.IOnItemClickListener;

import toothpick.config.Module;

public class LocationListFragmentModule extends Module {
    private Fragment mFragment;

    public LocationListFragmentModule(Fragment fragment) {
        mFragment = fragment;

        if (mFragment instanceof IOnItemClickListener) {
            bind(LocationListAdapter.class).toInstance(new LocationListAdapter((IOnItemClickListener) mFragment));
        } else {
            bind(LocationListAdapter.class).toInstance(new LocationListAdapter(null));
        }
    }


}
