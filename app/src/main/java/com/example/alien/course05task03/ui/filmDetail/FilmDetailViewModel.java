package com.example.alien.course05task03.ui.filmDetail;

import android.arch.lifecycle.MutableLiveData;

import com.example.alien.course05task03.R;
import com.example.alien.course05task03.data.IFilmRepository;
import com.example.alien.course05task03.data.model.Location;
import com.example.alien.course05task03.ui.common.BaseViewModel;
import com.google.gson.Gson;

import timber.log.Timber;

public class FilmDetailViewModel extends BaseViewModel {
    private MutableLiveData<String> mName = new MutableLiveData<>();
    private MutableLiveData<String> mYear = new MutableLiveData<>();
    private MutableLiveData<String> mDirector = new MutableLiveData<>();
    private MutableLiveData<String> mRating = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsSaved = new MutableLiveData<>();
    private Long mFilmId;
    private int mTitleId;

    public FilmDetailViewModel(IFilmRepository repository, Gson gson, Long filmId) {
        super(repository, gson);
        mIsSaved.postValue(false);
        mFilmId = filmId;
        if (mFilmId >= 0) {
            loadFilm();
            mTitleId = R.string.dialog_title_edit_film;
        } else {
            mTitleId = R.string.dialog_title_new_film;
        }
    }

    private void loadFilm() {
        Location location = mRepository.getItem(mFilmId);
        mName.postValue(location.getName());
        mDirector.postValue(location.getDirector());
        mYear.postValue(String.valueOf(location.getYear()));
        mRating.postValue(String.valueOf(location.getRating()));
    }

    public MutableLiveData<String> getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName.postValue(mName);
    }

    public MutableLiveData<String> getYear() {
        return mYear;
    }


    public MutableLiveData<String> getDirector() {
        return mDirector;
    }

    public MutableLiveData<String> getRating() {
        return mRating;
    }


    public void apply(String name, String director, String year, String rating) {
        int yearInt = 0;
        double ratingDbl = 0;
        try {
            yearInt = Integer.valueOf(year);
            ratingDbl = Double.valueOf(rating);
        } catch (Throwable t) {
            Timber.d(t);
        }
        if (mFilmId < 0) {
            mRepository.createFilmAndSave(name, director, yearInt, ratingDbl);
        } else {
            mRepository.createFilmAndUpdate(mFilmId, name, director, yearInt, ratingDbl);
        }
        mIsSaved.postValue(true);
    }

    public MutableLiveData<Boolean> getIsSaved() {
        return mIsSaved;
    }

    public int getTitleId() {
        return mTitleId;
    }

    @Override
    protected void updateFromRepository() {

    }
}
