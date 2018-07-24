package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.testandroid.yang.R;


/**
 *
 * @author yangjiajia
 * @date 2018/7/23
 */
public class BlueToothActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, BlueToothActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);
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
