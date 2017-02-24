package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 根据文档，测试ActionBar 相关
 * Created by yangjiajia on 2017/2/24 0024.
 */

public class ActionBarActivity extends BaseActivity {
    private static final String TAG = "ActionBarActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void start(Context context) {
        Intent starter = new Intent(context, ActionBarActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        Log.d(TAG, "initView: toolbar=" + toolbar);

        toolbar.setNavigationIcon(R.drawable.arrow_left_dart);
        toolbar.setNavigationContentDescription("导航？");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: setNavigationOnClickListener v=" + v);
            }
        });

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("MyActionBar");
//        actionBar.setElevation(0);
        actionBar.setLogo(R.drawable.drop_down_setting);

        Log.d(TAG, "initView: actionBar=" + actionBar);

        //貌似不起作用
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Nullable
    @Override
    public Intent getSupportParentActivityIntent() {
        return super.getSupportParentActivityIntent();
    }

    @Override
    public void onCreateSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        super.onCreateSupportNavigateUpTaskStack(builder);
    }

    @Override
    public void initData() {
        //ActionMenuView

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Drawable icon = item.getIcon();
        Log.d(TAG, "onOptionsItemSelected: item=" + item.getTitle());
        switch (item.getItemId()) {
            case R.id.menu_bar_setting:
                return true;
            case R.id.menu_bar_more:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
