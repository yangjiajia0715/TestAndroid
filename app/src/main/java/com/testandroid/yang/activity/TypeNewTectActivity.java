package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.testandroid.yang.R;
import com.testandroid.yang.adapter.HomeRecyleViewAdapter;
import com.testandroid.yang.common.HomeInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 新技术相关
 * Created by yangjiajia on 2017/3/1 0001.
 */

public class TypeNewTectActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TypeNewTectActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;

    private List<HomeInfo> infos = new ArrayList<>();
    private List<HomeInfo> items;
    private HomeRecyleViewAdapter adapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, TypeNewTectActivity.class);
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

        toolbar.setTitle(R.string.title_new_tech);

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

        infos.add(new HomeInfo("tv_test_tinker", R.id.tv_test_tinker, HomeInfo.HomeGroup.NewTech));
        infos.add(new HomeInfo("tv_test_RxJava2_main", R.id.tv_test_RxJava_main, HomeInfo.HomeGroup.NewTech));
        infos.add(new HomeInfo("Rxjava2_2017年8月", R.id.tv_test_RxJava_new, HomeInfo.HomeGroup.NewTech));
        infos.add(new HomeInfo("tv_test_RxJava2_operate", R.id.tv_test_RxJava_operate, HomeInfo.HomeGroup.NewTech));
        infos.add(new HomeInfo("tv_Data_Binding", R.id.tv_Data_Binding, HomeInfo.HomeGroup.NewTech));
        infos.add(new HomeInfo("tv_CoordinatorLayout", R.id.tv_CoordinatorLayout, HomeInfo.HomeGroup.NewTech));
        infos.add(new HomeInfo("tv_CoordinatorLayout_appbar", R.id.tv_CoordinatorLayout_appbar, HomeInfo.HomeGroup.NewTech));
        infos.add(new HomeInfo("tv_appbar_tab_layout", R.id.tv_appbar_tab_layout, HomeInfo.HomeGroup.NewTech));
        infos.add(new HomeInfo("tv_tv_test_app_bar", R.id.tv_tv_test_app_bar, HomeInfo.HomeGroup.NewTech));
        infos.add(new HomeInfo("RxJava", R.id.tv_tv_test_app_bar, HomeInfo.HomeGroup.NewTech));
        infos.add(new HomeInfo("OkHttp3", R.id.tv_ok_http, HomeInfo.HomeGroup.NewTech));
        infos.add(new HomeInfo("BottomSheet", R.id.tv_bottomsheet, HomeInfo.HomeGroup.NewTech));

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
                case R.id.tv_test_tinker:
                    break;
                case R.id.tv_test_RxJava_main:
                    RxJava2Activity.start(this);
                    break;
                case R.id.tv_test_RxJava_new:
                    Rxjava2NewActivity.start(this);
                    break;
                case R.id.tv_test_RxJava_operate:
                    RxJavaOperateActivity.start(this);
                    break;
                case R.id.tv_Data_Binding:
                    DataBindingActivity.start(this);
                    break;
                case R.id.tv_CoordinatorLayout:
                    CoordinatorLayoutActivity.start(this);
                    break;
                case R.id.tv_CoordinatorLayout_appbar:
                    CoordinatorAppBarActivity.start(this);
                    break;
                case R.id.tv_appbar_tab_layout:
                    AppBarTabLayout.start(this);
                    break;
                case R.id.tv_tv_test_app_bar:
                    TestAppBar.start(this);
                    break;
                case R.id.tv_menu:
                    MenuActivity.start(this);
                    break;
                case R.id.tv_ok_http:
                    OkHttpActivity.start(this);
                    break;
                case R.id.tv_bottomsheet:
                    BottomSheetActivity.start(this);
                    break;
            }
        }
    }
}
