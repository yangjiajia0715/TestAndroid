package com.testandroid.yang.activity;

import android.app.MediaRouteButton;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 自定义view
 * Created by yangjiajia on 2017/6/13 0013.
 */

public class CustomViewActivity extends BaseActivity {

    @BindView(R.id.view0)
    TextView view0;
    @BindView(R.id.view1)
    TextView view1;
    @BindView(R.id.view2)
    TextView view2;
    @BindView(R.id.view3)
    TextView view3;
    @BindView(R.id.view4)
    TextView view4;
    @BindView(R.id.view5)
    TextView view5;
    @BindView(R.id.view6)
    TextView view6;
    @BindView(R.id.view7)
    TextView view7;
    @BindView(R.id.view8)
    TextView view8;
    @BindView(R.id.view9)
    TextView view9;
    @BindView(R.id.view10)
    TextView view10;

    public static void start(Context context) {
        Intent starter = new Intent(context, CustomViewActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        MediaRouteButton button;
    }

    @OnClick({R.id.view0, R.id.view1, R.id.view2, R.id.view3, R.id.view4, R.id.view5, R.id.view6, R.id.view7, R.id.view8, R.id.view9, R.id.view10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view0:
                CustomView0Activity.start(this);
                break;
            case R.id.view1:
                break;
            case R.id.view2:
                break;
            case R.id.view3:
                break;
            case R.id.view4:
                break;
            case R.id.view5:
                break;
            case R.id.view6:
                break;
            case R.id.view7:
                break;
            case R.id.view8:
                break;
            case R.id.view9:
                break;
            case R.id.view10:
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged: new" + newConfig);
    }
}
