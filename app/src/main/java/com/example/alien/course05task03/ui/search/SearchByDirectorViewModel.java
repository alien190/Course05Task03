package com.example.alien.course05task03.ui.search;

import android.arch.lifecycle.MutableLiveData;

import com.example.alien.course05task03.data.ILocationRepository;
import com.example.alien.course05task03.ui.common.BaseViewModel;
import com.google.gson.Gson;

public class SearchByDirectorViewModel extends BaseViewModel {

    private MutableLiveData<String> mSearchByDirectorQuery = new MutableLiveData<>();

    public SearchByDirectorViewModel(ILocationRepository repository, Gson gson) {
        super(repository, gson);
        updateFromRepository();
    }


    public MutableLiveData<String> getSearchByDirectorQuery() {
        return mSearchByDirectorQuery;
    }

    public void setSearchByDirectorQuery(CharSequence query) {
        this.mSearchByDirectorQuery.setValue(query.toString());
        updateFromRepository();
    }

    @Override
    protected void updateFromRepository() {
        mFilmList.postValue(mRepository.searchByDirector(mSearchByDirectorQuery.getValue()));
    }
}
