package com.example.alien.course05task03.di;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;

import com.example.alien.course05task03.ui.search.SearchByYearViewModel;
import com.example.alien.course05task03.ui.common.ViewModelCustomFactory;

import javax.inject.Inject;
import javax.inject.Provider;

class SearchByYearViewModelProvider implements Provider<SearchByYearViewModel> {
    @Inject
    protected AppCompatActivity mActivity;
    @Inject
    protected ViewModelCustomFactory mFactory;


    @Override
    public SearchByYearViewModel get() {
        return ViewModelProviders.of(mActivity, mFactory).get(SearchByYearViewModel.class);
    }
}