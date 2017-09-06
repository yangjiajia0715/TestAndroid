package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.testandroid.yang.R;

/**
 * 约束布局 减少view层次嵌套 加快想染
 * Created by yangjiajia on 2017/9/6.
 */

public class ConstraintLayoutActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, ConstraintLayoutActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
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
