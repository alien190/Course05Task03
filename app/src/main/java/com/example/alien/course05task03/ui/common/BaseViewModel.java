package com.example.alien.course05task03.ui.common;



import com.example.alien.course05task03.data.ILocationRepository;
import com.example.alien.course05task03.data.model.Location;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.realm.OrderedRealmCollection;
import io.realm.RealmResults;

public abstract class BaseViewModel extends ViewModel {
    protected MutableLiveData<List<Location>> mLocationList = new MutableLiveData<>();
    protected OrderedRealmCollection<Location> data;
    private MutableLiveData<Boolean> mIsEmpty = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsDataBaseEmpty = new MutableLiveData<>();
    private MutableLiveData<String> mVisibleItemPosition = new MutableLiveData<>();
    private MutableLiveData<String> mItemCount = new MutableLiveData<>();

    protected ILocationRepository mRepository;

    private Gson mGson;

    public BaseViewModel(ILocationRepository repository, Gson gson) {
        this.mRepository = repository;
        this.mGson = gson;

        EventBus.getDefault().register(this);

        mVisibleItemPosition.postValue("1");

        mLocationList.observeForever(list ->
        {
            mIsEmpty.postValue(list != null && list.isEmpty());
            mItemCount.postValue(list == null ? "0" : String.valueOf(list.size()));

            if (list instanceof RealmResults) {
                RealmResults<Location> locationRealmResults = (RealmResults<Location>) list;
                locationRealmResults.addChangeListener(films -> mIsEmpty.postValue(films.isEmpty()));
            }
        });

        mIsDataBaseEmpty.postValue(mRepository.getItemsCount() == 0);
    }

    public MutableLiveData<List<Location>> getLocationList() {
        return mLocationList;
    }

    public MutableLiveData<Boolean> getIsEmpty() {
        return mIsEmpty;
    }

    public void generateData(String json) {
        Type type = new TypeToken<List<Location>>() {
        }.getType();
        List<Location> locations = mGson.fromJson(json, type);
        mRepository.insertItems(locations);
    }

    public void deleteItem(long id) {
        mRepository.deleteItem(id);
    }

    public OrderedRealmCollection<Location> getData() {
        return data;
    }

    @Override
    protected void onCleared() {
        EventBus.getDefault().unregister(this);
        super.onCleared();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLocationDataBaseUpdate(ILocationRepository.IOnLocationDataBaseUpdate event) {
        updateFromRepository();
    }

    abstract protected void updateFromRepository();

    public MutableLiveData<String> getVisibleItemPosition() {
        return mVisibleItemPosition;
    }


    public MutableLiveData<String> getItemCount() {
        return mItemCount;
    }

    public MutableLiveData<Boolean> getIsDataBaseEmpty() {
        return mIsDataBaseEmpty;
    }
}
