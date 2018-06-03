package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @see RecycleViewActivity
 * Created by yangjiajia on 2018/6/1.
 */
public class FlexLayoutActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    public static void start(Context context) {
        Intent starter = new Intent(context, FlexLayoutActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_layout);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new FlexboxLayoutManager(this));

    }

    @Override
    public void initData() {

    }
}
