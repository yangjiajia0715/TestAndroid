package com.example.testandroid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.testandroid.ObservableScrollView.demo.ParallaxToolbarScrollViewActivity;
import com.example.testandroid.ObservableScrollView.viewpager.ViewPagerTabActivity;
import com.example.testandroid.R;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_zoom_image).setOnClickListener(this);
        findViewById(R.id.tv_test_view_pager_11).setOnClickListener(this);
        findViewById(R.id.tv_parallax).setOnClickListener(this);
        findViewById(R.id.tv_test_listview_clip).setOnClickListener(this);
        findViewById(R.id.tv_test_scrollview_clip).setOnClickListener(this);
        findViewById(R.id.tv_test_viewpager_all).setOnClickListener(this);
        findViewById(R.id.tv_parallaxtoolbar_scrollview_activity).setOnClickListener(this);
        findViewById(R.id.tv_test_view_pager_tab_dif).setOnClickListener(this);
        findViewById(R.id.tv_animation).setOnClickListener(this);
        findViewById(R.id.tv_fragement_pager_adapter).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_zoom_image:
                intent = new Intent(this, ZoomImageActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_test_view_pager_11:
                intent = new Intent(this, ViewPagerActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_parallax:
                intent = new Intent(this, ParallaxActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_test_listview_clip:
                intent = new Intent(this, ListViewClipActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_test_scrollview_clip:
                intent = new Intent(this, ScrollViewClipActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_test_viewpager_all:
                intent = new Intent(this, ViewPagerAllActivity.class);
                startActivity(intent);
            case R.id.tv_animation:
                intent = new Intent(this, AnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_parallaxtoolbar_scrollview_activity:
                intent = new Intent(this, ParallaxToolbarScrollViewActivity.class);
                startActivity(intent);
            case R.id.tv_test_view_pager_tab_dif:
                intent = new Intent(this, ViewPagerTabActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_fragement_pager_adapter:
                intent = new Intent(this, FrgStatePagerAdaperActivity.class);
                startActivity(intent);

                break;
        }
    }
}
