package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.testandroid.yang.R;

import butterknife.ButterKnife;

/**
 * 自定义view
 * Created by yangjiajia on 2017/6/13 0013.
 */

public class CustomView0Activity extends BaseActivity {
    private static final String TAG = "CustomView0Activity";

    public static void start(Context context) {
        Intent starter = new Intent(context, CustomView0Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_00);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
