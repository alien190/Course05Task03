package com.example.alien.course05task03.di;


import com.example.alien.course05task03.ui.locationList.ListAllViewModel;
import com.example.alien.course05task03.ui.common.ViewModelCustomFactory;

import javax.inject.Inject;
import javax.inject.Provider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

class ListAllViewModelProvider implements Provider<ListAllViewModel> {
    @Inject
    protected AppCompatActivity mActivity;
    @Inject
    protected ViewModelCustomFactory mFactory;


    @Override
    public ListAllViewModel get() {
        return ViewModelProviders.of(mActivity, mFactory).get(ListAllViewModel.class);
    }
}