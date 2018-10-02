package com.example.alien.course05task03.di;


import android.support.v4.app.Fragment;

import com.example.alien.course05task03.ui.locationList.FilmListAdapter;
import com.example.alien.course05task03.ui.locationList.IOnItemClickListener;

import toothpick.config.Module;

public class FilmListFragmentModule extends Module {
    private Fragment mFragment;

    public FilmListFragmentModule(Fragment fragment) {
        mFragment = fragment;

        if (mFragment instanceof IOnItemClickListener) {
            bind(FilmListAdapter.class).toInstance(new FilmListAdapter((IOnItemClickListener) mFragment));
        } else {
            bind(FilmListAdapter.class).toInstance(new FilmListAdapter(null));
        }
    }


}
