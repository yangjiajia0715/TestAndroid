package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.testandroid.yang.R;
import com.testandroid.yang.adapter.HomeRecyleViewAdapter;
import com.testandroid.yang.common.HomeInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 数据库相关
 * Created by yangjiajia on 2017/3/1 0001.
 */

public class TypeDataBaseActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TypeDataBase";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;

    private List<HomeInfo> infos = new ArrayList<>();
    private List<HomeInfo> items;
    private HomeRecyleViewAdapter adapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, TypeDataBaseActivity.class);
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

        toolbar.setTitle(R.string.title_data_base);

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

        infos.add(new HomeInfo("tv_Dex_ClassLoader", R.id.tv_Dex_ClassLoader, HomeInfo.HomeGroup.DataBase));
        infos.add(new HomeInfo("tv_test_DatabaseUtil", R.id.tv_test_DatabaseUtil, HomeInfo.HomeGroup.DataBase));
        infos.add(new HomeInfo("tv_test_CursorLoader", R.id.tv_test_CursorLoader, HomeInfo.HomeGroup.DataBase));
        infos.add(new HomeInfo("tv_save_userinfo_to_database", R.id.tv_save_userinfo_to_database, HomeInfo.HomeGroup.DataBase));

        items.addAll(infos);
        adapter = new HomeRecyleViewAdapter(this, items);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        //数据库 冲突
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getTag(R.id.tag_first) instanceof HomeInfo) {
            HomeInfo info = (HomeInfo) v.getTag(R.id.tag_first);
            switch (info.id) {
                case R.id.tv_DexClassLoader:
                    intent = new Intent(this, DexClassLoaderActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_Dex_ClassLoader:
                    intent = new Intent(this, DexClassLoadTestActivity.class);
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
                case R.id.tv_save_userinfo_to_database:
                    SaveToDataBaseActivity.start(this);
                    break;
            }
        }
    }
}
