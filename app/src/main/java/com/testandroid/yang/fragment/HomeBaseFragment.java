package com.testandroid.yang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testandroid.yang.R;
import com.testandroid.yang.adapter.HomeRecyleViewAdapter;
import com.testandroid.yang.common.HomeInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * HomeBaseFragment
 * Created by yangjiajia on 2017/9/13.
 */

public abstract class HomeBaseFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "HomeBaseFragment";

    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    Unbinder unbinder;
    private HomeRecyleViewAdapter adapter;
    List<HomeInfo> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_base, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recycleview.setLayoutManager(layoutManager);
        adapter = new HomeRecyleViewAdapter(getActivity(), items);
        recycleview.setAdapter(adapter);
        adapter.setClickListener(this);

        Log.d(TAG, "onActivityCreated: getSimpleName=" + getClass().getSimpleName());
        initView();
        initData();
    }

    public abstract void initView();

    public abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        Log.d(TAG, "onDestroyView: getSimpleName=" + getClass().getSimpleName());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: getSimpleName=" + getClass().getSimpleName());
    }

    @Override
    public void onClick(View v) {

    }
}
