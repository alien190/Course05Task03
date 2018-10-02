package com.example.alien.course04task02.ui.search;

import android.arch.lifecycle.MutableLiveData;
import android.support.v4.util.Pair;
import android.text.TextUtils;

import com.example.alien.course04task02.data.IFilmRepository;
import com.example.alien.course04task02.ui.common.BaseViewModel;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

import timber.log.Timber;

public class SearchByYearViewModel extends BaseViewModel {

    private MutableLiveData<String> mSearchByYearQuery = new MutableLiveData<>();

    public SearchByYearViewModel(IFilmRepository repository, Gson gson) {
        super(repository, gson);
        updateFromRepository();
    }

    public MutableLiveData<String> getSearchByYearQuery() {
        return mSearchByYearQuery;
    }

    public void setSearchByYearQuery(CharSequence query) {
        this.mSearchByYearQuery.setValue(query.toString());
        updateFromRepository();
    }

    private Pair<Integer, Integer> parseYearQuery() {
        int startYear = 0;
        int endYear = 0;
        if (mSearchByYearQuery.getValue() != null) {
            String splitResult[] = TextUtils.split(mSearchByYearQuery.getValue(), Pattern.compile("\\D+"));
            try {
                startYear = Integer.valueOf(splitResult[0]);
            } catch (Throwable t) {
                Timber.d(t);
                startYear = 0;
            }

            if (splitResult.length > 1) {
                try {
                    endYear = Integer.valueOf(splitResult[1]);
                } catch (Throwable t) {
                    Timber.d(t);
                    endYear = 0;
                }
            }
        }
        if (endYear < startYear) {
            endYear = 0;
        }
        Timber.d("startYear: %d", startYear);
        Timber.d("endYear: %d", endYear);
        return new Pair<>(startYear, endYear);
    }

    @Override
    protected void updateFromRepository() {
        Pair<Integer, Integer> parseResult = parseYearQuery();
        mFilmList.postValue(mRepository.searchInBounds(parseResult.first, parseResult.second));
    }
}
