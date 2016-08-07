package com.example.testandroid.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testandroid.R;

/**
 * author: yangjiajia
 * create time: 2016/8/7 0007.
 * desc:
 */
public class ScrollViewFragment extends Fragment {
    private View mMainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return mMainView = inflater.inflate(R.layout.fragment_scrollview, null, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
    }
}
