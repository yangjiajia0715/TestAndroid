package com.testandroid.yang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.Toast;

import com.testandroid.yang.ObservableScrollView.demo.ParallaxToolbarScrollViewActivity;
import com.testandroid.yang.ObservableScrollView.viewpager.ViewPagerTabActivity;
import com.testandroid.yang.R;

import java.io.File;

/**
 * Main
 * Created by Administrator on 2016/7/27 0027.
 */
public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

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
        findViewById(R.id.tv_item_type_listview).setOnClickListener(this);
        findViewById(R.id.tv_DexClassLoader).setOnClickListener(this);
        findViewById(R.id.tv_ViewAnimator).setOnClickListener(this);
        findViewById(R.id.tv_CheckedTextView).setOnClickListener(this);
        findViewById(R.id.tv_Dex_ClassLoader).setOnClickListener(this);
        findViewById(R.id.tv_photo_view).setOnClickListener(this);
        findViewById(R.id.tv_buildconfig_field).setOnClickListener(this);
        findViewById(R.id.tv_test_tinker).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava_main).setOnClickListener(this);
        findViewById(R.id.tv_test_DatabaseUtil).setOnClickListener(this);
        findViewById(R.id.tv_test_CursorLoader).setOnClickListener(this);
        findViewById(R.id.tv_Data_Binding).setOnClickListener(this);

        new CheckedTextView(this);
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
            case R.id.tv_item_type_listview:
                intent = new Intent(this, ItemTypeListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_DexClassLoader:
                intent = new Intent(this, DexClassLoaderActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_ViewAnimator:
                intent = new Intent(this, ViewAnimatorActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_CheckedTextView:
                intent = new Intent(this, CheckTextViewActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_Dex_ClassLoader:
                intent = new Intent(this, DexClassLoadTestActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_photo_view:
                intent = new Intent(this, PhotoViewActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_buildconfig_field:
                intent = new Intent(this, BuildConfigFieldActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_test_tinker:
                intent = new Intent(this, TestTinkerActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_test_RxJava_main:
                intent = new Intent(this, RxJavaActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_test_DatabaseUtil:
                String time = DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_DATE);
                Toast.makeText(this, "DatabaseUtil 计划中 time:" + time, Toast.LENGTH_SHORT).show();
                File cacheDir = getCacheDir();
                Log.d(TAG, "tv_test_DatabaseUtil onClick: cacheDir=" + cacheDir);
                break;
            case R.id.tv_test_CursorLoader:
                intent = new Intent(this, CursorLoadActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_Data_Binding:
                DataBindingActivity.start(this);
                break;
        }
    }
}
