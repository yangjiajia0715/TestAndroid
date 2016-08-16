package com.example.testandroid.fragment;

import android.app.Fragment;
import android.graphics.Color;
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
    public static final String KEY_POS ="frgPosition";
    private View mMainView;
    private ListView mListView;
    private SwipeRefreshLayout swipeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return mMainView = inflater.inflate(R.layout.fragment_listview_a, null, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        String[] subjects = {"语文","数学","英语","政治","历史","地理","物理","化学","生物"};
        int[] colors ={Color.BLUE,Color.RED,Color.GRAY,Color.GREEN,Color.BLUE,Color.DKGRAY};

        Bundle bundle = getArguments();
        mListView = (ListView) mMainView.findViewById(R.id.listview);
        List<String> datas = new ArrayList<>();
        String text = "数据";
        if (bundle != null && bundle.containsKey(KEY_POS)) {
            int pos = bundle.getInt(KEY_POS);
            text = subjects[pos % subjects.length];
            getView().setBackgroundColor(colors[pos % colors.length]);
            Log.d(TAG, "--ListViewFragment--initView: text="+ text +"，pos＝" +pos);
        }

        for (int i = 0; i < 20; i++) {
            datas.add(text+ i);
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
        swipeLayout.setBound(0,3* 80,swipeLayout.getMeasuredWidth(),0);
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
