package com.testandroid.yang.activity;

import android.os.Bundle;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * BaseActivity
 * Created by yangjiajia on 2017/1/13 0013.
 */
//AppCompatActivity v7 25.3

public abstract class BaseActivity extends RxAppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract void initView();
    public abstract void initData();

}
