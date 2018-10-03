package com.example.alien.course05task03.ui.locationList;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.alien.course05task03.R;
import com.example.alien.course05task03.ui.common.BaseFragment;
import com.example.alien.course05task03.ui.common.BaseViewModel;
import com.example.alien.course05task03.ui.locationDetail.LocationDetailActivity;

import java.io.InputStream;
import java.util.Scanner;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class ListAllFragment extends BaseFragment implements IOnItemClickListener {
    View view;
    @BindView(R.id.rvFilmList)
    RecyclerView mRecyclerView;

    @BindView(R.id.ll_error)
    LinearLayout mErrorLayout;

    @Inject
    protected LocationListAdapter mAdapter;

    @Inject
    protected BaseViewModel mViewModel;

    private LinearLayoutManager mLinearLayoutManager;

    public static ListAllFragment newInstance(String parentScopeName) {

        Bundle args = new Bundle();
        args.putString(KEY_PARENT_SCOPE_NAME, parentScopeName);

        ListAllFragment fragment = new ListAllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fr_film_list, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        initRecyclerViewScrollListener();
        mRecyclerView.setAdapter(mAdapter);
        mViewModel.getLocationList().observe(this, list -> mAdapter.submitList(list));
        mViewModel.getIsDataBaseEmpty().observe(this, isEmpty -> {
            if (isEmpty != null && isEmpty) {
                generateData();
            }
        });
    }

    private void initRecyclerViewScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                mViewModel.getVisibleItemPosition().postValue(
                        String.valueOf(mLinearLayoutManager.findFirstVisibleItemPosition() + 1));
                Timber.d("item: %d", mLinearLayoutManager.findFirstVisibleItemPosition());
            }
        });

    }

    private void generateData() {
        String json = "";

        try {
            AssetManager am = getContext().getAssets();
            InputStream is = am.open("initialList.json");
            try (Scanner s = new Scanner(is).useDelimiter("\\A")) {
                json = s.hasNext() ? s.next() : "";
            }
        } catch (Throwable t) {
            Timber.d(t);
        }
        mViewModel.generateData(json);
    }

    @Override
    public void OnItemClick(long id) {
        LocationDetailActivity.start(getContext(), id);
    }


}
