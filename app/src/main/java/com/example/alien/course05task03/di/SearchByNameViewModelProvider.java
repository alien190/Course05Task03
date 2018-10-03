package com.example.alien.course05task03.di;



import com.example.alien.course05task03.ui.search.SearchByNameViewModel;
import com.example.alien.course05task03.ui.common.ViewModelCustomFactory;

import javax.inject.Inject;
import javax.inject.Provider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

class SearchByNameViewModelProvider implements Provider<SearchByNameViewModel> {
    @Inject
    protected AppCompatActivity mActivity;
    @Inject
    protected ViewModelCustomFactory mFactory;


    @Override
    public SearchByNameViewModel get() {
        return ViewModelProviders.of(mActivity, mFactory).get(SearchByNameViewModel.class);
    }
}