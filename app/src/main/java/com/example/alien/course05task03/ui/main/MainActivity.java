package com.example.alien.course05task03.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.alien.course05task03.R;
import com.example.alien.course05task03.di.MainActivityModule;
import com.example.alien.course05task03.di.SearchByNameActivityModule;
import com.example.alien.course05task03.ui.locationList.ListAllFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
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
    SettingsFragment mSettingsFragment;

    @Inject
    ListAllFragment mListAllFragment;

    @Inject
    @Named("TitleId")
    protected Integer mTitleId;

    private String mScopeName;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = menuItem -> {
        switch (menuItem.getItemId()) {
            case R.id.mi_search: {
                changeToSearchFragment();
                return true;
            }
            case R.id.mi_settings: {
                changeToSettingsFragment();
                return true;
            }
            default: {
                return false;
            }
        }
    };

    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toothpickInject();
        setContentView(R.layout.ac_double_fragment);
        if (savedInstanceState == null) {
            changeToSearchFragment();
        }

        setTitle(mTitleId);
        mBottomNavigationView = findViewById(R.id.navigation);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onPause() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(null);
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() <= 1) {
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


    protected void changeToSearchFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, mMainFragment)
                .replace(R.id.fragmentListContainer, mListAllFragment)
                .commit();
    }

    protected void changeToSettingsFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .remove(mListAllFragment)
                .remove(mMainFragment)
                .replace(R.id.fragmentListContainer, mSettingsFragment)
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

            case TYPE_SEARCH_BY_NAME: {
                mScopeName = "SEARCH_BY_NAME_SCOPE";
                module = new SearchByNameActivityModule(this, mScopeName, type);
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
