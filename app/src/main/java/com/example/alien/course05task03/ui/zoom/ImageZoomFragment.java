package com.example.alien.course05task03.ui.zoom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.alien.course05task03.R;
import com.example.alien.course05task03.ui.locationDetail.LocationDetailFragment;
import com.example.alien.course05task03.ui.locationDetail.LocationDetailViewModel;
import com.example.alien.course05task03.utils.ImageUtils;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import toothpick.Toothpick;

public class ImageZoomFragment extends Fragment {

    private ImageView mImageView;
    private View mView;
    @Inject
    protected LocationDetailViewModel mViewModel;
    private ScaleGestureDetector mScaleGestureDetector;
    private GestureDetector mGestureDetector;

    private float mScaleFactor = 1.0f;

    public static ImageZoomFragment newInstance() {

        Bundle args = new Bundle();

        ImageZoomFragment fragment = new ImageZoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fr_image_zoom, container, false);
        Toothpick.inject(this, Toothpick.openScope(LocationDetailFragment.class.getSimpleName()));
        mImageView = mView.findViewById(R.id.ivZoom);
        mImageView.setImageBitmap(ImageUtils.fromBase64(mViewModel.getHotelImageBase64().getValue()));
        mScaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
        mGestureDetector = new GestureDetector(getContext(), new ClickListener());
        return mView;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onResume() {
        super.onResume();
        mView.setOnClickListener(view1 -> getActivity().onBackPressed());
        mView.setOnTouchListener((view, motionEvent) -> {
            mGestureDetector.onTouchEvent(motionEvent);
            return mScaleGestureDetector.onTouchEvent(motionEvent);
        });
    }

    @Override
    public void onPause() {
        mView.setOnClickListener(null);
        mView.setOnTouchListener(null);
        super.onPause();
    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(1f, Math.min(mScaleFactor, 10.0f));
            mImageView.setScaleX(mScaleFactor);
            mImageView.setScaleY(mScaleFactor);
            return true;
        }
    }

    private class ClickListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            mView.performClick();
            return true;
        }


        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            float orgImageHeight = mImageView.getDrawable().getIntrinsicHeight();
            float scaledHeight = orgImageHeight * mImageView.getScaleX();

            float orgImageWidth = mImageView.getDrawable().getIntrinsicWidth();
            float scaledWidth = orgImageWidth * mImageView.getScaleX();

            if (mImageView.getScaleY() > 1) {
                float newPivotY = mImageView.getPivotY() + distanceY / (mImageView.getScaleY() - 1);

                if (newPivotY * (mImageView.getScaleY() - 1) / mImageView.getScaleY() >=
                        (mImageView.getHeight() - orgImageHeight) / 2
                        &&
                        newPivotY <= ((mImageView.getHeight() - orgImageHeight) / 2 *
                                mImageView.getScaleY() + scaledHeight - mImageView.getHeight()) / (mImageView.getScaleY() - 1)
                        ) {
                    mImageView.setPivotY(newPivotY);
                }
            }
            if (mImageView.getScaleX() > 1) {
                float newPivotX = mImageView.getPivotX() + distanceX / (mImageView.getScaleX() - 1);


                if (newPivotX * (mImageView.getScaleX() - 1) / mImageView.getScaleX() >=
                        (mImageView.getWidth() - orgImageWidth) / 2
                        &&
                        newPivotX <= ((mImageView.getWidth() - orgImageWidth) / 2 *
                                mImageView.getScaleX() + scaledWidth - mImageView.getWidth()) / (mImageView.getScaleX() - 1)
                        ) {
                    mImageView.setPivotX(newPivotX);
                }

            }

            return true;
        }
    }
}
