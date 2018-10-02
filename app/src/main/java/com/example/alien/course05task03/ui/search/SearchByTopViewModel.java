package com.example.alien.course05task03.ui.search;

import android.arch.lifecycle.MutableLiveData;

import com.example.alien.course05task03.data.IFilmRepository;
import com.example.alien.course05task03.ui.common.BaseViewModel;
import com.google.gson.Gson;

import timber.log.Timber;

public class SearchByTopViewModel extends BaseViewModel {

    private MutableLiveData<String> mSearchByTopQuery = new MutableLiveData<>();

    public SearchByTopViewModel(IFilmRepository repository, Gson gson) {
        super(repository, gson);
        updateFromRepository();
    }


    public MutableLiveData<String> getSearchByTopQuery() {
        return mSearchByTopQuery;
    }

    public void setSearchByTopQuery(CharSequence query) {
        this.mSearchByTopQuery.setValue(query.toString());
        updateFromRepository();
    }

    @Override
    protected void updateFromRepository() {
        int count = 0;
        try {
            count = Integer.valueOf(mSearchByTopQuery.getValue());
        } catch (Throwable t) {
            Timber.d(t);
        }
        mFilmList.postValue(mRepository.getTopFilms(count));
    }
}