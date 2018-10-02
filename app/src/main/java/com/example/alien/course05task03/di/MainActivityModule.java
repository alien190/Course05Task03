package com.example.alien.course05task03.di;


import android.support.v7.app.AppCompatActivity;

import com.example.alien.course05task03.R;
import com.example.alien.course05task03.ui.common.BaseViewModel;


public class MainActivityModule extends CommonActivityModule {

    public MainActivityModule(AppCompatActivity activity, String scopeName, int type) {
        super(activity, scopeName, type);
        //todo сделать интерфейсы
        bind(BaseViewModel.class).toProvider(ListAllViewModelProvider.class).providesSingletonInScope();
        bind(Integer.class).withName("TitleId").toInstance(R.string.main_activity_title);
    }

}
