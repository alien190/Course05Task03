package com.example.alien.course04task02.di;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;

import com.example.alien.course04task02.ui.filmList.ListAllViewModel;
import com.example.alien.course04task02.ui.common.ViewModelCustomFactory;

import javax.inject.Inject;
import javax.inject.Provider;

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