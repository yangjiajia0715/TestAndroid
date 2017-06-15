package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.testandroid.yang.R;

/**
 * 自定义view
 * Created by yangjiajia on 2017/6/13 0013.
 */

public class CustomView0Activity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, CustomView0Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_00);
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
