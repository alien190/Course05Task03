package com.example.alien.course05task03.ui.common;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.alien.course05task03.data.IFilmRepository;
import com.example.alien.course05task03.data.model.Film;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmResults;

public abstract class BaseViewModel extends ViewModel {
    protected MutableLiveData<List<Film>> mFilmList = new MutableLiveData<>();
    protected OrderedRealmCollection<Film> data;
    private MutableLiveData<Boolean> mIsEmpty = new MutableLiveData<>();

    protected IFilmRepository mRepository;

    private Gson mGson;

    public BaseViewModel(IFilmRepository repository, Gson gson) {
        this.mRepository = repository;
        this.mGson = gson;

        EventBus.getDefault().register(this);

        mFilmList.observeForever(list ->
        {
            mIsEmpty.postValue(list != null && list.isEmpty());

            if (list instanceof RealmResults) {
                RealmResults<Film> filmRealmResults = (RealmResults<Film>) list;
                filmRealmResults.addChangeListener(films -> mIsEmpty.postValue(films.isEmpty()));
            }
        });
    }

    public MutableLiveData<List<Film>> getFilmList() {
        return mFilmList;
    }

    public MutableLiveData<Boolean> getIsEmpty() {
        return mIsEmpty;
    }

    public void generateData(String json) {
        Type type = new TypeToken<List<Film>>() {
        }.getType();
        List<Film> films = mGson.fromJson(json, type);
        mRepository.insertItems(films);
    }

    public void deleteItem(long id) {
        mRepository.deleteItem(id);
    }

    public OrderedRealmCollection<Film> getData() {
        return data;
    }

    @Override
    protected void onCleared() {
        EventBus.getDefault().unregister(this);
        super.onCleared();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFilmDataBaseUpdate(IFilmRepository.IOnFilmDataBaseUpdate event)
    {
        updateFromRepository();
    }

    abstract protected void updateFromRepository();
}
