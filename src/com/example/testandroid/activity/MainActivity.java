package com.example.testandroid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;

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
        findViewById(R.id.tv_item_type_listview).setOnClickListener(this);
        findViewById(R.id.tv_DexClassLoader).setOnClickListener(this);
        findViewById(R.id.tv_ViewAnimator).setOnClickListener(this);
        findViewById(R.id.tv_CheckedTextView).setOnClickListener(this);
        findViewById(R.id.tv_Dex_ClassLoader).setOnClickListener(this);
        findViewById(R.id.tv_photo_view).setOnClickListener(this);
        findViewById(R.id.tv_buildconfig_field).setOnClickListener(this);
        findViewById(R.id.tv_test_tinker).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava).setOnClickListener(this);

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
            case R.id.tv_test_RxJava:
                intent = new Intent(this, RxJavaActivity.class);
                startActivity(intent);
                break;
        }
    }
}
