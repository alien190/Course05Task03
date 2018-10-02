package com.example.alien.course05task03.ui.filmList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.alien.course05task03.data.model.Location;


public class FilmListViewHolder extends RecyclerView.ViewHolder {
    private View view;
    private long mId;

//    @BindView(R.id.tvName)
//    TextView mTvName;
//    @BindView(R.id.tvDirector)
//    TextView mTvDirector;
//    @BindView(R.id.tvYear)
//    TextView mTvYear;
//    @BindView(R.id.tvRate)
//    TextView mTvRate;


    public FilmListViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
//        ButterKnife.bind(this, view);
    }

    public void bind(Location location) {
//        mTvName.setText(location.getName());
//        mTvDirector.setText(location.getDirector());
//        mTvYear.setText(String.valueOf(location.getYear()));
//        mTvRate.setText(StringUtils.rateToString(location.getRating()));
        mId = location.getId();
    }

    public void setOnItemClickListener(final IOnItemClickListener listener) {
        view.setOnLongClickListener(view -> listener != null && listener.OnItemLongClick(mId));
        view.setOnClickListener(view -> {
            if (listener != null) listener.OnItemClick(mId);
        });
    }

}
