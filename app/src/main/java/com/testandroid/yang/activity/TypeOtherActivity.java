package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.testandroid.yang.R;
import com.testandroid.yang.adapter.HomeRecyleViewAdapter;
import com.testandroid.yang.common.HomeInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 数据库相关
 * Created by yangjiajia on 2017/3/1 0001.
 */

public class TypeOtherActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TypeOther";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;

    private List<HomeInfo> infos = new ArrayList<>();
    private List<HomeInfo> items;
    private HomeRecyleViewAdapter adapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, TypeOtherActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
        Log.d(TAG, "TypeOtherActivity--onCreate: getTaskId=" + getTaskId());
    }

    @Override
    public void initView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        toolbar.setTitle(R.string.title_other);

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

        infos.add(new HomeInfo("tv_buildconfig_field", R.id.tv_buildconfig_field, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("tv_temp", R.id.tv_temp, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("三种菜单", R.id.tv_menu, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("ActionBar相关", R.id.tv_action_bar, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("pdf相关", R.id.tv_pdf, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("Overview Screen", R.id.tv_overview_screen, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("Notification", R.id.tv_notification, HomeInfo.HomeGroup.Other));

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
                case R.id.tv_buildconfig_field:
                    intent = new Intent(this, BuildConfigFieldActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_temp:
                    TempActivity.start(this);
                    break;
                case R.id.tv_menu:
                    MenuActivity.start(this);
                    break;
                case R.id.tv_action_bar:
                    ActionBarActivity.start(this);
                    break;
                case R.id.tv_pdf:
                    PDFCreateActivity.start(this);
//                    OverviewScreen01Activity.start(this);
                    break;
                case R.id.tv_overview_screen:
                    OverviewScreen01Activity.start(this);
                    break;
                case R.id.tv_notification:
                    NotificationActivity.start(this);
                    break;
            }
        }
    }
}
