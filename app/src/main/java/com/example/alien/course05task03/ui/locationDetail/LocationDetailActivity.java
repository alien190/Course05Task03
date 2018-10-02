package com.example.alien.course05task03.ui.locationDetail;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.alien.course05task03.ui.common.SingleFragmentActivity;

public class LocationDetailActivity extends SingleFragmentActivity {
    private static final String ID_KEY = "LocationDetailActivity.IdKey";

    @Override
    protected Fragment getFragment() {
        Intent intent = getIntent();
        return LocationDetailFragment.newInstance(intent.getLongExtra(ID_KEY, 1));
    }

    @Override
    protected void closeScope() {
    }

    public static void start(Context context, long id) {
        Intent intent = new Intent(context, LocationDetailActivity.class);
        intent.putExtra(ID_KEY, id);
        context.startActivity(intent);
    }
}
