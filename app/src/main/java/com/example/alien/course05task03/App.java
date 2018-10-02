package com.example.alien.course05task03;

import android.app.Application;

import com.example.alien.course05task03.di.ApplicationModule;

import io.realm.Realm;
import timber.log.Timber;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.configuration.Configuration;
import toothpick.registries.FactoryRegistryLocator;
import toothpick.registries.MemberInjectorRegistryLocator;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        Toothpick.setConfiguration(Configuration.forProduction().disableReflection());
        Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes());
        MemberInjectorRegistryLocator.setRootRegistry(new com.example.alien.course05task03.MemberInjectorRegistry());
        FactoryRegistryLocator.setRootRegistry(new com.example.alien.course05task03.FactoryRegistry());
        Scope scope = Toothpick.openScope("Application");
        scope.installModules(new ApplicationModule(this));

        Timber.plant(new Timber.DebugTree());

    }
}
