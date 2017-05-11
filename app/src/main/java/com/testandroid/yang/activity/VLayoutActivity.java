package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.testandroid.yang.R;
import com.testandroid.yang.adapter.HomePage1Adapter;
import com.testandroid.yang.adapter.HomePagew2Adapter;

/**
 * Created by yangjiajia on 2017/5/8.
 */

public class VLayoutActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "VLayoutActivity";
    private SwipeRefreshLayout swipeRefreshLayout;

    public static void start(Context context) {
        Intent starter = new Intent(context, VLayoutActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlayout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayou);
        swipeRefreshLayout.setOnRefreshListener(this);

//        swipeRefreshLayout.setColorSchemeResources();
//        swipeRefreshLayout.setProgressBackgroundColorSchemeResource();



        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this, VirtualLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);

        delegateAdapter.addAdapter(new HomePage1Adapter(this));//
        delegateAdapter.addAdapter(new HomePagew2Adapter(this));

        recyclerView.setAdapter(delegateAdapter);

    }

    @Override
    public void initView() {
        getTitle();//label 

    }

    @Override
    public void initData() {

    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh: ");
    }

}
