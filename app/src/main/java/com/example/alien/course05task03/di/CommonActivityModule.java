package com.example.alien.course05task03.di;



import com.example.alien.course05task03.ui.locationList.ListAllFragment;
import com.example.alien.course05task03.ui.main.MainFragment;
import com.example.alien.course05task03.ui.main.SettingsFragment;

import androidx.appcompat.app.AppCompatActivity;
import toothpick.config.Module;

public class CommonActivityModule extends Module {

    private AppCompatActivity mActivity;
    private String mScopeName;
    private int mType;

    public CommonActivityModule(AppCompatActivity activity, String scopeName, int type) {
        mActivity = activity;
        mScopeName = scopeName;
        mType = type;

        bind(MainFragment.class).toInstance(MainFragment.newInstance(mScopeName, mType));
        bind(ListAllFragment.class).toInstance(ListAllFragment.newInstance(mScopeName));
        bind(SettingsFragment.class).toInstance(SettingsFragment.newInstance());
        bind(AppCompatActivity.class).toInstance(mActivity);
    }
}