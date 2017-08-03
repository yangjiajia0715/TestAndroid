package com.testandroid.yang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.CCC;
import com.testandroid.yang.R;
import com.testandroid.yang.adapter.HomeRecyleViewAdapter;
import com.testandroid.yang.common.HomeInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Main
 * Created by Administrator on 2016/7/27 0027.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.temp_trans_animation)
    TextView tempTransAnimation;

    private List<HomeInfo> infos = new ArrayList<>();
    private List<HomeInfo> items;
    private HomeRecyleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
//        toolbarTitle.setText("主页标题");
        toolbar.setNavigationIcon(null);
//        toolbar.setBackground( ContextCompat.getDrawable(this, R.drawable.my_bg));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
//            actionBar.setDisplayShowHomeEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowTitleEnabled(true);
        }
        Log.d(TAG, "MainActivity--onCreate: getTaskId=" + getTaskId());
        Log.d(TAG, "MainActivity--onCreate: CCC.get=" + CCC.get());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "MainActivity--onNewIntent: intent=" + intent);
    }

    @Override
    public void initView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recycleview.setLayoutManager(layoutManager);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_main);

//        toolbar.setLogo(R.mipmap.ic_launcher);

        //设置导航图标要在setSupportActionBar方法之后
//        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);

//        toolbar.setNavigationIcon(R.drawable.ic_search_bg_black);
//        toolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

        infos.add(new HomeInfo("动画类", R.id.type_animation, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("数据库", R.id.type_data_base, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("新技术", R.id.type_new_tech, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("View类", R.id.type_view, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("其他", R.id.type_other, HomeInfo.HomeGroup.View));

        items.addAll(infos);
        adapter = new HomeRecyleViewAdapter(this, items);
        recycleview.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getTag(R.id.tag_first) instanceof HomeInfo) {
            HomeInfo info = (HomeInfo) v.getTag(R.id.tag_first);
            switch (info.id) {
                case R.id.type_animation:
                    TypeAnimationActivity.start(this);
                    break;
                case R.id.type_data_base:
                    TypeDataBaseActivity.start(this);
                    break;
                case R.id.type_new_tech:
                    TypeNewTectActivity.start(this);
                    break;
                case R.id.type_view:
                    TypeViewActivity.start(this);
                    break;
                case R.id.type_other:
                    TypeOtherActivity.start(this);
                    break;
            }
            return;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity--onDestroy: getTaskId=" + getTaskId());
    }
}
