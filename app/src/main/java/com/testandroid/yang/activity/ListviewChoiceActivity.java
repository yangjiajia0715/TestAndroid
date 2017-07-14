package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * listview 选择问题 综合
 * Created by yangjiajia on 2017/7/14.
 * 需求：点击跳转，可以多选/单选，选中的高亮
 */

public class ListviewChoiceActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, ListviewChoiceActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
