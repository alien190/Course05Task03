package com.example.alien.course05task03.ui.locationDetail;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alien.course05task03.R;
import com.example.alien.course05task03.di.LocationDetailFragmentModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import toothpick.Scope;
import toothpick.Toothpick;

public class LocationDetailFragment extends Fragment {

    private Scope mScope;
    private static final String KEY_FILM_ID = "LocationDetailFragment.KeyFilmId";

    @Inject
    protected LocationDetailViewModel mViewModel;

    @BindView(R.id.tvTitle)
    protected TextView tvTitle;

    @BindView(R.id.etName)
    protected EditText etName;

    @BindView(R.id.etDirector)
    protected EditText etDirector;

    @BindView(R.id.etYear)
    protected EditText etYear;

    @BindView(R.id.etRate)
    protected EditText etRate;

    private DialogInterface.OnClickListener mOnClickListener = (dialogInterface, i) -> {
        mViewModel.apply(etName.getText().toString(),
                etDirector.getText().toString(),
                etYear.getText().toString(),
                etRate.getText().toString());

    };

    public static LocationDetailFragment newInstance(long id) {

        Bundle args = new Bundle();
        args.putLong(KEY_FILM_ID, id);
        LocationDetailFragment fragment = new LocationDetailFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void toothpickInject() {
        long id = -1;
        if (getArguments() != null) {
            id = getArguments().getLong(KEY_FILM_ID, -1);
        }

        mScope = Toothpick.openScopes("Application", this.getClass().getSimpleName());
        mScope.installModules(new LocationDetailFragmentModule(this, id));
        Toothpick.inject(this, mScope);
    }


    @Override
    public void onDetach() {
        Toothpick.closeScope(this.getClass().getSimpleName());
        super.onDetach();
    }

    private void initUI(View view) {
        ButterKnife.bind(this, view);

        tvTitle.setText(mViewModel.getTitleId());
        mViewModel.getName().observe(this, str -> etName.setText(str));
        mViewModel.getDirector().observe(this, str -> etDirector.setText(str));
        mViewModel.getYear().observe(this, str -> etYear.setText(str));
        mViewModel.getRating().observe(this, str -> etRate.setText(str));
    }


}
