package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * CoordinatorLayout配合AppBar
 * Created by yangjiajia on 2017/1/19 0019.
 */

public class CoordinatorAppBarActivity extends BaseActivity {
    private static final String TAG = "CoordinatorAppBarActivi";

    //    @BindView(R.id.toolbar)
//    Toolbar toolBar;
//    @BindView(R.id.top_image)
//    ImageView topImage;
    @BindView(R.id.appbar)
    AppBarLayout appBar;
    @BindView(R.id.nested_scrollview)
    NestedScrollView nestedScrollView;
    @BindView(R.id.collapsingtoolbarlayout)
    CollapsingToolbarLayout collapsingtoolbarlayout;

    public static void start(Context context) {
        Intent starter = new Intent(context, CoordinatorAppBarActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordi_app_bar);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
//        ButtonBarLayout
//        https://github.com/Bilibili/ijkplayer
    }

    @Override
    public void initData() {
        Log.d(TAG, "initData: appBar=" + appBar);
        if (appBar != null)
            appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    Log.d(TAG, "onOffsetChanged: verticalOffset=" + verticalOffset);
                }
            });

//        if (nestedScrollView != null) {
//            nestedScrollView.setNestedScrollingEnabled(false);
//        }
    }
}
