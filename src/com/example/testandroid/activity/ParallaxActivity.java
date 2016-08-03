package com.example.testandroid.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.testandroid.R;

/**
 * 视差
 * author: yangjiajia
 * create time: 2016/8/2 0002
 * desc:
 */
public class ParallaxActivity extends Activity {
    private static final String TAG = "ParallaxActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax);
        initView();
    }

    private void initView() {

    }

}
