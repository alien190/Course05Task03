package com.example.alien.course05task03.ui.filmList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alien.course05task03.R;
import com.example.alien.course05task03.data.model.Location;
import com.example.alien.course05task03.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FilmListViewHolder extends RecyclerView.ViewHolder {
    private View view;
    private long mId;

    @BindView(R.id.ivPicture)
    protected ImageView mIvPicture;
    @BindView(R.id.tvCity)
    protected TextView mTvCity;
    @BindView(R.id.tvCountry)
    protected TextView mTvCountry;
    @BindView(R.id.tvDuration)
    protected TextView mTvDuration;
    @BindView(R.id.tvDurationUnit)
    protected TextView mTvDurationUnit;
    @BindView(R.id.tvPrice)
    protected TextView mTvPrice;
    @BindView(R.id.tvPriceUnit)
    protected TextView mTvPriceUnit;


    public FilmListViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        ButterKnife.bind(this, view);
    }

    public void bind(Location location) {
        mTvCity.setText(location.getCity());
        mTvCountry.setText(location.getCountry());
        mTvDuration.setText(String.valueOf(location.getDuration()));
        mTvDurationUnit.setText(location.getDurationUnit());
        mTvPrice.setText(String.valueOf(location.getPrice()));
        mTvPriceUnit.setText(location.getPriceUnit());
        mIvPicture.setImageBitmap(ImageUtils.fromBase64(location.getImageBase64()));
        mId = location.getId();
    }

    public void setOnItemClickListener(final IOnItemClickListener listener) {
        view.setOnClickListener(view -> {
            if (listener != null) listener.OnItemClick(mId);
        });
    }

}
