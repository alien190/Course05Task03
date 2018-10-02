package com.example.alien.course05task03.di;

import android.app.Application;

import com.example.alien.course05task03.data.ILocationRepository;
import com.example.alien.course05task03.data.RealmLocationRepository;
import com.example.alien.course05task03.ui.common.ViewModelCustomFactory;
import com.google.gson.Gson;

import toothpick.config.Module;

public class ApplicationModule extends Module {

    private Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;

        bind(ILocationRepository.class).toInstance(new RealmLocationRepository());
        bind(Gson.class).toInstance(new Gson());
        bind(ViewModelCustomFactory.class).toProvider(ViewModelCustomFactoryProvider.class).providesSingletonInScope();
        bind(Application.class).toInstance(mApplication);

    }
}
