package com.example.alien.course05task03.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.alien.course05task03.R;
import com.example.alien.course05task03.di.MainActivityModule;
import com.example.alien.course05task03.di.SearchByDirectorActivityModule;
import com.example.alien.course05task03.di.SearchByNameActivityModule;
import com.example.alien.course05task03.di.SearchByTopActivityModule;
import com.example.alien.course05task03.di.SearchByYearActivityModule;
import com.example.alien.course05task03.ui.locationList.ListAllFragment;

import javax.inject.Inject;
import javax.inject.Named;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class MainActivity extends AppCompatActivity {

    public static final int TYPE_SEARCH_BY_NAME = 1;
    public static final int TYPE_SEARCH_BY_DIRECTOR = 2;
    public static final int TYPE_SEARCH_BY_YEAR = 3;
    public static final int TYPE_SEARCH_BY_TOP = 4;
    private static final String TYPE_KEY = "SearchActivityTypeKey";

    @Inject
    MainFragment mMainFragment;

    @Inject
    ListAllFragment mListAllFragment;

    @Inject
    @Named("TitleId")
    protected Integer mTitleId;

    private String mScopeName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toothpickInject();

        setContentView(R.layout.ac_double_fragment);
        if (savedInstanceState == null) {
            changeFragment();
        }

        setTitle(mTitleId);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == 1) {
            finish();
        } else {
            fragmentManager.popBackStack();
        }
    }

    public static void startActivity(Context context, int type) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.putExtra(TYPE_KEY, type);
        context.startActivity(intent);
    }


    protected void changeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, mMainFragment)
                .addToBackStack(mMainFragment.getClass().getSimpleName())
                .replace(R.id.fragmentListContainer, mListAllFragment)
                .addToBackStack(mListAllFragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
         Toothpick.closeScope(mScopeName);
    }

    private void toothpickInject() {

        Module module;
        mScopeName = this.getClass().getSimpleName() + ".";
        int type = getIntent().getIntExtra(TYPE_KEY, 1);

        switch (type) {
            case TYPE_SEARCH_BY_DIRECTOR: {
                mScopeName = "SEARCH_BY_DIRECTOR_SCOPE";
                module = new SearchByDirectorActivityModule(this, mScopeName, type);
                break;
            }
            case TYPE_SEARCH_BY_NAME: {
                mScopeName = "SEARCH_BY_NAME_SCOPE";
                module = new SearchByNameActivityModule(this, mScopeName, type);
                break;
            }
            case TYPE_SEARCH_BY_YEAR: {
                mScopeName = "SEARCH_BY_YEAR_SCOPE";
                module = new SearchByYearActivityModule(this, mScopeName, type);
                break;
            }
            case TYPE_SEARCH_BY_TOP: {
                mScopeName = "SEARCH_BY_TOP_SCOPE";
                module = new SearchByTopActivityModule(this, mScopeName, type);
                break;
            }
            default: {
                mScopeName = "MAIN_SCOPE";
                module = new MainActivityModule(this, mScopeName, type);
                break;
            }
        }

        Scope scope = Toothpick.openScopes("Application", mScopeName);
        scope.installModules(module);
        Toothpick.inject(this, scope);

    }

    public String getScopeName() {
        return mScopeName;
    }
}
