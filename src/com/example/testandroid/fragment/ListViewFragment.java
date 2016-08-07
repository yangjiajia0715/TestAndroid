package com.example.testandroid.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.testandroid.R;
import com.example.testandroid.view.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * author: yangjiajia
 * create time: 2016/8/7 0007.
 * desc:
 */
public class ListViewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static final String TAG = "ListViewFragment";
    private View mMainView;
    private ListView mListView;
    private SwipeRefreshLayout swipeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return mMainView = inflater.inflate(R.layout.fragment_listview, null, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mListView = (ListView) mMainView.findViewById(R.id.listview);
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("数据"+ i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, datas);
        mListView.setAdapter(adapter);

        //下拉刷新和上拉加载更多
        swipeLayout = (SwipeRefreshLayout) mMainView.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeLayout.setOnLoadListener(this);
        swipeLayout.setMode(SwipeRefreshLayout.Mode.BOTH);
    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "ListViewFragment--onRefresh: ");
    }

    @Override
    public void onLoad() {
        Log.d(TAG, "ListViewFragment--onLoad: ");
    }
}
