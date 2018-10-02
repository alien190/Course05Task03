package com.example.alien.course04task02.di;


import android.support.v7.app.AppCompatActivity;

import com.example.alien.course04task02.R;
import com.example.alien.course04task02.ui.common.BaseViewModel;


public class SearchByTopActivityModule extends CommonActivityModule {


    public SearchByTopActivityModule(AppCompatActivity activity, String scopeName, int type) {
        super(activity, scopeName, type);
        //todo сделать интерфейсы
        bind(BaseViewModel.class).toProvider(SearchByTopViewModelProvider.class).providesSingletonInScope();
        //bind(SearchByTopViewModel.class).toProvider(SearchByTopViewModelProvider.class).providesSingletonInScope();
        bind(Integer.class).withName("TitleId").toInstance(R.string.top_search_title);
    }

}
