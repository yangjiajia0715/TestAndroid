package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.widget.TextView;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangjiajia on 2017/2/7 0007.
 */

public class TestAppBar extends BaseActivity {
    private static final String TAG = "TestAppBar";
//    @BindView(R.id.fragment)
//    FrameLayout videoLayout;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.nested_scrollview)
    NestedScrollView nestedScrollview;

    public static void start(Context context) {
        Intent starter = new Intent(context, TestAppBar.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_appbar);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged: newConfig=" + newConfig);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            RelativeLayout.LayoutParams params = getScreenSizeParamsLandScape();
//            params.addRule(RelativeLayout.CENTER_IN_PARENT);//RelativeLayout.CENTER_IN_PARENT |
//            surfaceView.setLayoutParams(params);
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//            RelativeLayout.LayoutParams params = getScreenSizeParams(currentScreenSizeFlag);
//            params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//            surfaceView.setLayoutParams(params);
        }
    }
}
