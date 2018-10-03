package com.example.alien.course05task03.ui.search;



import com.example.alien.course05task03.data.ILocationRepository;
import com.example.alien.course05task03.ui.common.BaseViewModel;
import com.google.gson.Gson;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.MutableLiveData;


public class SearchByNameViewModel extends BaseViewModel implements SearchView.OnQueryTextListener {

    private MutableLiveData<String> mSearchByNameQuery = new MutableLiveData<>();

    public SearchByNameViewModel(ILocationRepository repository, Gson gson) {
        super(repository, gson);
        updateFromRepository();
    }

    public MutableLiveData<String> getSearchByNameQuery() {
        return mSearchByNameQuery;
    }

    private void setSearchByNameQuery(String query) {
        this.mSearchByNameQuery.setValue(query);
        updateFromRepository();
    }

    @Override
    protected void updateFromRepository() {
        mLocationList.postValue(mRepository.search(mSearchByNameQuery.getValue()));
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        setSearchByNameQuery(s);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        setSearchByNameQuery(s);
        return true;
    }
}
