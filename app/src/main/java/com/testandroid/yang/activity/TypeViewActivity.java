package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.testandroid.yang.ObservableScrollView.demo.ParallaxToolbarScrollViewActivity;
import com.testandroid.yang.ObservableScrollView.viewpager.ViewPagerTabActivity;
import com.testandroid.yang.R;
import com.testandroid.yang.adapter.HomeRecyleViewAdapter;
import com.testandroid.yang.common.HomeInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * View相关
 * Created by yangjiajia on 2017/3/1 0001.
 */

public class TypeViewActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TypeViewActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;

    private List<HomeInfo> infos = new ArrayList<>();
    private List<HomeInfo> items;
    private HomeRecyleViewAdapter adapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, TypeViewActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        toolbar.setTitle(R.string.title_view);

        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
        infos.add(new HomeInfo("tv_parallaxtoolbar_scrollview_activity", R.id.tv_parallaxtoolbar_scrollview_activity, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_fragement_pager_adapter", R.id.tv_fragement_pager_adapter, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_item_type_listview", R.id.tv_item_type_listview, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_CheckedTextView", R.id.tv_CheckedTextView, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_recycleview", R.id.tv_recycleview, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_vLayouot", R.id.tv_vLayouot, HomeInfo.HomeGroup.View));

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
                case R.id.tv_recycleview:
                    RecycleViewActivity.start(this);
                    break;
                case R.id.tv_vLayouot:
                    VLayoutActivity.start(this);
                    break;
            }
        }
    }
}
