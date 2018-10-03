package com.example.alien.course05task03.ui.locationDetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alien.course05task03.R;
import com.example.alien.course05task03.di.LocationDetailFragmentModule;
import com.example.alien.course05task03.ui.zoom.ImageZoomActivity;
import com.example.alien.course05task03.utils.ImageUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import toothpick.Scope;
import toothpick.Toothpick;

public class LocationDetailFragment extends Fragment {

    private Scope mScope;
    private static final String KEY_LOCATION_ID = "LocationDetailFragment.KeyLocationId";

    @Inject
    protected LocationDetailViewModel mViewModel;

    @BindView(R.id.ivPicture)
    protected ImageView mIvPicture;
    @BindView(R.id.ivHotelPicture)
    protected ImageView mIvHotelPicture;
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;
    @BindView(R.id.tvDuration)
    protected TextView mTvDuration;
    @BindView(R.id.tvHotelName)
    protected TextView mTvHotelName;
    @BindView(R.id.tvHotelAddress)
    protected TextView mTvHotelAddress;
    @BindView(R.id.tvPrice)
    protected TextView mTvPrice;


    public static LocationDetailFragment newInstance(long id) {

        Bundle args = new Bundle();
        args.putLong(KEY_LOCATION_ID, id);
        LocationDetailFragment fragment = new LocationDetailFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_detail_location_fragment, container, false);
        toothpickInject();
        mViewModel.updateFromRepository();
        initUI(view);
        return view;
    }

    private void toothpickInject() {
        long id = -1;
        if (getArguments() != null) {
            id = getArguments().getLong(KEY_LOCATION_ID, -1);
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

        mViewModel.getImageBase64().observe(this, img ->
                mIvPicture.setImageBitmap(ImageUtils.fromBase64(img)));
        mViewModel.getHotelImageBase64().observe(this,
                img -> mIvHotelPicture.setImageBitmap(ImageUtils.fromBase64(img)));
        mViewModel.getCity().observe(this, mToolbar::setTitle);
        mViewModel.getDuration().observe(this, mTvDuration::setText);
        mViewModel.getHotelName().observe(this, mTvHotelName::setText);
        mViewModel.getHotelAddress().observe(this, mTvHotelAddress::setText);
        mViewModel.getHotelPrice().observe(this, mTvPrice::setText);
        mIvHotelPicture.setOnClickListener(view1 -> ImageZoomActivity.start(getContext()));
    }


}
