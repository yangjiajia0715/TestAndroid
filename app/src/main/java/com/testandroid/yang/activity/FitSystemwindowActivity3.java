package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.testandroid.yang.R;

public class FitSystemwindowActivity3 extends BaseActivity {
    private static final String TAG = "FitSystemwindowActivity3";

    public static void start(Context context) {
        Intent starter = new Intent(context, FitSystemwindowActivity3.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit_system_window_3);

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
