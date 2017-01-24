package com.study.idear.mystudy;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;


/**
 * Created by idear on 17-1-23.
 */
public class AudioFragment extends Fragment {

    private final static String TAG = AudioFragment.class.getSimpleName();

    private RecyclerView mRecyleView;
    private IRecyclerAdapter mAdapter;
    private List<String> mRecycleData;
    private SwipeRefreshLayout mSwip;
    private static AudioFragment mInstance;

    public static AudioFragment getmInstance() {
        if (mInstance == null) {
            mInstance = new AudioFragment();
        }
        return mInstance;
    }

    public AudioFragment() {

        Logger.d(TAG, "VideoFragment created");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content, container, false);
        mRecyleView = (RecyclerView) view.findViewById(R.id.content_list);
        mSwip = (SwipeRefreshLayout) view.findViewById(R.id.content_swipeLayout);
        initRecycle();
        return view;
    }

    private void initRecycle() {
        initRecycleData();
        mAdapter = new IRecyclerAdapter(getActivity(), mRecycleData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyleView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyleView.setAdapter(mAdapter);
        mRecyleView.setItemAnimator(new DefaultItemAnimator());
        mSwip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addData();
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwip.setRefreshing(false);
                    }
                },3000);
            }
        });
        mSwip.setColorSchemeColors(Color.RED);
    }

    private void initRecycleData() {
        mRecycleData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mRecycleData.add("小李飞刀" + i);
        }
    }

    public void addData() {
        if (mRecycleData != null) {
            mRecycleData.add("沉默是金");
        }
        mAdapter.setDatas(mRecycleData);
        mAdapter.notifyDataSetChanged();
    }
}
