package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.testandroid.yang.R;

/**
 * Created by yangjiajia on 2018/4/8.
 */

public class PanelActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, PanelActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);
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
