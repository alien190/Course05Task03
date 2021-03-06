package com.example.alien.course05task03.ui.common;

import android.content.Context;
import android.os.Bundle;

import com.example.alien.course05task03.di.LocationListFragmentModule;
import com.example.alien.course05task03.ui.main.MainActivity;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import toothpick.Scope;
import toothpick.Toothpick;

public abstract class BaseFragment extends Fragment {

    public static final String KEY_PARENT_SCOPE_NAME = "KeyParentScopeName";

    private String mScopeName;
    private String mParentScopeName;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mParentScopeName = ((MainActivity)context).getScopeName();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mParentScopeName = getArguments().getString(KEY_PARENT_SCOPE_NAME);

        mScopeName = mParentScopeName + "." + this.getClass().getSimpleName();
        Scope scope = Toothpick.openScopes(mParentScopeName, mScopeName);
        scope.installModules(new LocationListFragmentModule(this));
        Toothpick.inject(this, scope);
    }

    @Override
    public void onDestroy() {
        Toothpick.closeScope(mScopeName);
        super.onDestroy();
    }

    public void setScopeName(String scopeName) {
        this.mScopeName = scopeName;
    }
}
