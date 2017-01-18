package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.testandroid.yang.R;

/**
 * Created by yangjiajia on 2017/1/18 0018.
 */

public class CoordinatorLayoutActivity extends BaseActivity {
    private static final String TAG = "CoordinatorLayoutActivi";
    public static void start(Context context) {
        Intent starter = new Intent(context, CoordinatorLayoutActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        initView();
        String packageName = getPackageName();
        Log.d(TAG, "onCreate: packageName=" + packageName);
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
