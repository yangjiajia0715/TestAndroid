package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.testandroid.yang.R;
import com.testandroid.yang.common.Animal;
import com.testandroid.yang.common.Tiger;

/**
 * AJavaActivity
 * Created by yangjiajia on 2017/11/30.
 */

public class AJavaActivity extends BaseActivity {
    private static final String TAG = "AJavaActivity";

    public static void start(Context context) {
        Intent starter = new Intent(context, AJavaActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        initView();
        initData();
    }

    @Override
    public void initView() {
        Tiger tiger = new Tiger();
        Animal animal = new Tiger();
        Log.d(TAG, "initView: name=" + ((Animal)tiger).name + ",look=" + tiger.look());
        Log.d(TAG, "initView: name=" + animal.name + ",look=" + animal.look());
    }

    @Override
    public void initData() {

    }
}
