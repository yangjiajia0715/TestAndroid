package com.testandroid.yang.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.adapter.HomeRecyleViewAdapter;
import com.testandroid.yang.common.HomeInfo;
import com.testandroid.yang.fragment.AnimationFragment;
import com.testandroid.yang.fragment.DataBaseFragment;
import com.testandroid.yang.fragment.HomePageFragment;
import com.testandroid.yang.fragment.NewTechFragment;
import com.testandroid.yang.fragment.OtherFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Main
 * Created by Administrator on 2016/7/27 0027.
 */
@RuntimePermissions
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.toolbar_left)
    TextView toolbarLeft;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private List<HomeInfo> infos = new ArrayList<>();
    private List<HomeInfo> items;
    private HomeRecyleViewAdapter adapter;

    // 2019年09月06日16:59:26 from origin/dev
    // 2019-09-09 09:26:00 from origin/dev

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);
        ButterKnife.bind(this);
        initView();
        initData();
        toolbar.setNavigationIcon(null);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
//            actionBar.setDisplayShowHomeEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowTitleEnabled(true);
        }

        Log.d(TAG, "MainActivity--onCreate: getTaskId=" + getTaskId());
        MainActivityPermissionsDispatcher.doSomeThingWithCheck(this);
    }

    @NeedsPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void doSomeThing() {
        Log.d(TAG, "MainActivity--onCreate 已授权----");
    }

    @OnShowRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void showRational(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setTitle("理由")
                .setMessage("文件相关时需要写权限")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "MainActivity--onNewIntent: intent=" + intent);
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_main);

        viewpager.setOffscreenPageLimit(4);
        final HomePageAdapter adapter = new HomePageAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);

//        toolbar.setLogo(R.mipmap.ic_launcher);

        //设置导航图标要在setSupportActionBar方法之后
//        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);

//        toolbar.setNavigationIcon(R.drawable.ic_search_bg_black);
//        toolbar.setOnMenuItemClickListener(this);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        toolbar.setTitle(R.string.title_main);
                        bottomNavigationView.setSelectedItemId(R.id.menu_item_home);
                        break;
                    case 1:
                        toolbar.setTitle(R.string.title_new_tech);
                        bottomNavigationView.setSelectedItemId(R.id.menu_item_new_tech);
                        break;
                    case 2:
                        toolbar.setTitle(R.string.title_animtion);
                        bottomNavigationView.setSelectedItemId(R.id.menu_item_animation);
                        break;
                    case 3:
                        toolbar.setTitle(R.string.title_data_base);
                        bottomNavigationView.setSelectedItemId(R.id.menu_item_database);
                        break;
                    case 4:
                        toolbar.setTitle(R.string.title_other);
                        bottomNavigationView.setSelectedItemId(R.id.menu_item_other);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_home:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.menu_item_new_tech:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.menu_item_animation:
                        viewpager.setCurrentItem(2);
                        break;
                    case R.id.menu_item_database:
                        viewpager.setCurrentItem(3);
                        break;
                    case R.id.menu_item_other:
                        viewpager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                SettingsActivity.start(this);
                return true;
            case R.id.setting_ip:
                SettingIpActivity.start(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity--onDestroy: getTaskId=" + getTaskId());
    }

    private class HomePageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<>(5);

        public HomePageAdapter(FragmentManager fm) {
            super(fm);

            fragments.add(HomePageFragment.newInstance());
            fragments.add(NewTechFragment.newInstance());
            fragments.add(AnimationFragment.newInstance());
            fragments.add(DataBaseFragment.newInstance());
            fragments.add(OtherFragment.newInstance());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
