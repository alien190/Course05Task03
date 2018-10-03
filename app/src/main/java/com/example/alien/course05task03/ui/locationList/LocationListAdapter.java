package com.example.alien.course05task03.ui.locationList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alien.course05task03.R;
import com.example.alien.course05task03.data.model.Location;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;


public class LocationListAdapter extends ListAdapter<Location, LocationListViewHolder> {

    private IOnItemClickListener mOnItemClickListener;

    private static DiffUtil.ItemCallback<Location> DIFF_CALLBACK = new DiffUtil.ItemCallback<Location>() {
        @Override
        public boolean areItemsTheSame(@NonNull Location location, @NonNull Location t1) {
            return location.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Location location, @NonNull Location t1) {
            return location.equals(t1);
        }
    };


    public LocationListAdapter(IOnItemClickListener onItemClickListener) {
        super(DIFF_CALLBACK);
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public LocationListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.li_tour, viewGroup, false);
        return new LocationListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationListViewHolder locationListViewHolder, int i) {
        locationListViewHolder.bind(getItem(i));
        locationListViewHolder.setOnItemClickListener(mOnItemClickListener);
    }

}
