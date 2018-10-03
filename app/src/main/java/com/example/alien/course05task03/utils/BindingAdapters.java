package com.example.alien.course05task03.utils;



import com.example.alien.course05task03.ui.search.SearchByNameViewModel;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.BindingAdapter;


public class BindingAdapters {

    @BindingAdapter("android:searchListener")
    public static void setSearchListener(SearchView view, SearchByNameViewModel vm) {
        view.setQuery(vm.getSearchByNameQuery().getValue(), false);
        view.clearFocus();
        view.setOnQueryTextListener(vm);
    }
}
