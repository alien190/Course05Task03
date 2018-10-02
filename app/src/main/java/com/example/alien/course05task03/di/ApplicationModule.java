package com.example.alien.course05task03.di;

import android.app.Application;

import com.example.alien.course05task03.data.IFilmRepository;
import com.example.alien.course05task03.data.RealmFilmRepository;
import com.example.alien.course05task03.ui.common.ViewModelCustomFactory;
import com.google.gson.Gson;

import toothpick.config.Module;

public class ApplicationModule extends Module {

    private Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;

        bind(IFilmRepository.class).toInstance(new RealmFilmRepository());
        bind(Gson.class).toInstance(new Gson());
        bind(ViewModelCustomFactory.class).toProvider(ViewModelCustomFactoryProvider.class).providesSingletonInScope();
        bind(Application.class).toInstance(mApplication);

    }
}
