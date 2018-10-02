package com.example.alien.course04task02;

import android.app.AlertDialog;
import android.app.Application;
import android.widget.Toast;

import com.example.alien.course04task02.di.ApplicationModule;

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
        MemberInjectorRegistryLocator.setRootRegistry(new com.example.alien.course04task02.MemberInjectorRegistry());
        FactoryRegistryLocator.setRootRegistry(new com.example.alien.course04task02.FactoryRegistry());
        Scope scope = Toothpick.openScope("Application");
        scope.installModules(new ApplicationModule(this));

        Timber.plant(new Timber.DebugTree());

        Toast.makeText(this, R.string.start_greeting, Toast.LENGTH_LONG).show();
    }
}
