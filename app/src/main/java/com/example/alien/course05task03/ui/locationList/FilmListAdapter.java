package com.example.alien.course05task03.ui.locationList;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alien.course05task03.R;
import com.example.alien.course05task03.data.model.Location;


public class FilmListAdapter extends ListAdapter<Location, FilmListViewHolder> {

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


    public FilmListAdapter(IOnItemClickListener onItemClickListener) {
        super(DIFF_CALLBACK);
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public FilmListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.li_tour, viewGroup, false);
        return new FilmListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmListViewHolder filmListViewHolder, int i) {
        filmListViewHolder.bind(getItem(i));
        filmListViewHolder.setOnItemClickListener(mOnItemClickListener);
    }

}
