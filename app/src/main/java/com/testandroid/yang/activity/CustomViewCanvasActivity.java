package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangjiajia on 2017/6/30.
 */

public class CustomViewCanvasActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void start(Context context) {
        Intent starter = new Intent(context, CustomViewCanvasActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        toolbar.setTitle("画布相关");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }
}
