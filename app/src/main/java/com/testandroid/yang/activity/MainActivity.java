package com.testandroid.yang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.testandroid.yang.ObservableScrollView.demo.ParallaxToolbarScrollViewActivity;
import com.testandroid.yang.ObservableScrollView.viewpager.ViewPagerTabActivity;
import com.testandroid.yang.R;
import com.testandroid.yang.adapter.HomeRecyleViewAdapter;
import com.testandroid.yang.common.HomeInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Main
 * Created by Administrator on 2016/7/27 0027.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener {
    private static final String TAG = "MainActivity";
    private List<HomeInfo> infos = new ArrayList<>();
    private List<HomeInfo> items;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private HomeRecyleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
//        RecyclerView.LayoutManager  layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Title");
//        toolbar.setSubtitle("SubTitle");
        toolbar.setLogo(R.mipmap.ic_launcher);
//        toolbar.inflateMenu(R.menu.main);

        //设置导航图标要在setSupportActionBar方法之后
//        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_search_bg_black);
        toolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    @Override
    public void initData() {
        items = new ArrayList<>();

        infos.add(new HomeInfo("tv_zoom_image", R.id.tv_zoom_image, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_test_view_pager_11", R.id.tv_test_view_pager_11, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_parallax", R.id.tv_parallax, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_test_listview_clip", R.id.tv_test_listview_clip, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_test_scrollview_clip", R.id.tv_test_scrollview_clip, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_test_viewpager_all", R.id.tv_test_viewpager_all, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_animation", R.id.tv_animation, HomeInfo.HomeGroup.Animator));
        infos.add(new HomeInfo("tv_ViewAnimator", R.id.tv_ViewAnimator, HomeInfo.HomeGroup.Animator));
        infos.add(new HomeInfo("tv_parallaxtoolbar_scrollview_activity", R.id.tv_parallaxtoolbar_scrollview_activity, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_fragement_pager_adapter", R.id.tv_fragement_pager_adapter, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_item_type_listview", R.id.tv_item_type_listview, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_DexClassLoader", R.id.tv_DexClassLoader, HomeInfo.HomeGroup.DataBase));
        infos.add(new HomeInfo("tv_CheckedTextView", R.id.tv_CheckedTextView, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_Dex_ClassLoader", R.id.tv_Dex_ClassLoader, HomeInfo.HomeGroup.DataBase));
        infos.add(new HomeInfo("tv_photo_view", R.id.tv_photo_view, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_buildconfig_field", R.id.tv_buildconfig_field, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("tv_test_tinker", R.id.tv_test_tinker, HomeInfo.HomeGroup.NewTech));
        infos.add(new HomeInfo("tv_test_RxJava_main", R.id.tv_test_RxJava_main, HomeInfo.HomeGroup.NewTech));
        infos.add(new HomeInfo("tv_test_DatabaseUtil", R.id.tv_test_DatabaseUtil, HomeInfo.HomeGroup.DataBase));
        infos.add(new HomeInfo("tv_test_CursorLoader", R.id.tv_test_CursorLoader, HomeInfo.HomeGroup.DataBase));
        infos.add(new HomeInfo("tv_Data_Binding", R.id.tv_Data_Binding, HomeInfo.HomeGroup.NewTech));
        infos.add(new HomeInfo("tv_temp", R.id.tv_temp, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("tv_CoordinatorLayout", R.id.tv_CoordinatorLayout, HomeInfo.HomeGroup.NewTech));
        infos.add(new HomeInfo("tv_CoordinatorLayout_appbar", R.id.tv_CoordinatorLayout_appbar, HomeInfo.HomeGroup.NewTech));
        infos.add(new HomeInfo("tv_appbar_tab_layout", R.id.tv_appbar_tab_layout, HomeInfo.HomeGroup.NewTech));

        items.addAll(infos);
        adapter = new HomeRecyleViewAdapter(this, items);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getTag(R.id.tag_first) instanceof HomeInfo) {
            HomeInfo info = (HomeInfo) v.getTag(R.id.tag_first);
            switch (info.id) {
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
                case R.id.tv_temp:
                    TempActivity.start(this);
//                LoginActivity.start(this);
                    /////
                    break;
                case R.id.tv_CoordinatorLayout:
                    CoordinatorLayoutActivity.start(this);
                    break;
                case R.id.tv_CoordinatorLayout_appbar:
                    CoordinatorAppBarActivity.start(this);
                    break;
                case R.id.tv_appbar_tab_layout:
//                    CoordinatorAppBarActivity.start(this);
                    AppBarTabLayout.start(this);
                    break;
            }
            return;
        }

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        items.clear();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        switch (item.getItemId()) {
            case R.id.filter_newTech:
                for (HomeInfo info : infos) {
                    if (info.group == HomeInfo.HomeGroup.NewTech)
                        items.add(info);
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.filter_view:
                for (HomeInfo info : infos) {
                    if (info.group == HomeInfo.HomeGroup.View)
                        items.add(info);
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.filter_animator:
                for (HomeInfo info : infos) {
                    if (info.group == HomeInfo.HomeGroup.Animator)
                        items.add(info);
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.filter_database:
                for (HomeInfo info : infos) {
                    if (info.group == HomeInfo.HomeGroup.DataBase)
                        items.add(info);
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.filter_other:
                for (HomeInfo info : infos) {
                    if (info.group == HomeInfo.HomeGroup.Other)
                        items.add(info);
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.filter_all:
                recyclerView.setLayoutManager(new GridLayoutManager(this,2));
                items.addAll(infos);
                adapter.notifyDataSetChanged();
                break;
        }
        return true;
    }
}
