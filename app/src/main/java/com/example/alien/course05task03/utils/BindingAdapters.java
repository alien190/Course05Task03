package com.example.alien.course05task03.utils;


import android.databinding.BindingAdapter;
import android.support.v7.widget.SearchView;

import com.example.alien.course05task03.ui.search.SearchByNameViewModel;


public class BindingAdapters {

    @BindingAdapter("android:searchListener")
    public static void setSearchListener(SearchView view, SearchByNameViewModel vm) {
        view.setQuery(vm.getSearchByNameQuery().getValue(), false);
        view.clearFocus();
        view.setOnQueryTextListener(vm);
    }
}
