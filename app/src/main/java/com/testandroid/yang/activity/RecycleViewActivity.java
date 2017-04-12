package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.adapter.AutoLineLayoutManage;
import com.testandroid.yang.adapter.RecycleviewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecycleViewActivity
 * {@link RecycleviewAdapter}
 * {@link AutoLineLayoutManage}
 * Created by yangjiajia on 2017/4/11.
 */

public class RecycleViewActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.rv_layoutmanager)
    TextView textView;

    public static void start(Context context) {
        Intent starter = new Intent(context, RecycleViewActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //custom
        AutoLineLayoutManage autoLineLayoutManage = new AutoLineLayoutManage();

        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void initData() {


    }

}
