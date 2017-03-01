package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.testandroid.yang.R;

/**
 * Material Design 动画相关
 * Created by yangjiajia on 2017/2/27 0027.
 */

public class MDAnimationAnimationActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, MDAnimationAnimationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_animation);
        initView();
        initData();
    }

    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning to this activity
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
