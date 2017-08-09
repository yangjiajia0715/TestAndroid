package com.testandroid.yang.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.testandroid.yang.ObservableScrollView.demo.ParallaxToolbarScrollViewActivity;
import com.testandroid.yang.ObservableScrollView.viewpager.ViewPagerTabActivity;
import com.testandroid.yang.R;
import com.testandroid.yang.adapter.HomeRecyleViewAdapter;
import com.testandroid.yang.common.HomeInfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
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
    private ProgressDialog progressDialog;

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
        infos.add(new HomeInfo("tv_CheckedTextView_new", R.id.tv_CheckedTextView_new, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_recycleview", R.id.tv_recycleview, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_recycleview_ceiling", R.id.tv_recycleview_ceiling, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_vLayouot", R.id.tv_vLayouot, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_fitsystemwindow", R.id.tv_fitsystemwindow, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_fitsystemwindow2", R.id.tv_fitsystemwindow2, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_rotation_view", R.id.tv_rotation_view, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_customeview_view", R.id.tv_customeview_view, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_edittext", R.id.tv_edittext, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_Dialog", R.id.tv_dialog, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_listview_choice", R.id.tv_listview_choice, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_drawer_layout", R.id.tv_drawer_layout, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("tv_clear_dialog", R.id.tv_clear_dialog, HomeInfo.HomeGroup.View));
        infos.add(new HomeInfo("ActionBar相关", R.id.tv_action_bar, HomeInfo.HomeGroup.View));

        items.addAll(infos);
        adapter = new HomeRecyleViewAdapter(this, items);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Object tag = v.getTag(R.id.tag_first);
        if (tag instanceof HomeInfo) {
            HomeInfo info = (HomeInfo) tag;
            switch (info.id) {
                case R.id.tv_zoom_image:
                    intent = new Intent(this, ZoomImageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_test_view_pager_11:
                    ViewPagerActivity.start(this);
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
                case R.id.tv_CheckedTextView_new:
                    CheckTextViewNewActivity.start(this);
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
                case R.id.tv_recycleview_ceiling:
                    RecycleViewCeilingActivity.start(this);
                    break;
                case R.id.tv_vLayouot:
                    VLayoutActivity.start(this);
                    break;
                case R.id.tv_fitsystemwindow:
                    FitSystemWindowActivity.start(this);
                    break;
                case R.id.tv_fitsystemwindow2:
                    FitSystemwindowActivity2.start(this);
                    break;
                case R.id.tv_rotation_view:
                    RotationActivity.start(this);
                    break;
                case R.id.tv_customeview_view:
                    CustomViewActivity.start(this);
                    break;
                case R.id.tv_edittext:
                    EditTextActivity.start(this);
                    break;
                case R.id.tv_dialog:
                    DialogListActivity.start(this);
                    break;
                case R.id.tv_listview_choice:
                    ListviewChoiceActivity.start(this);
                    break;
                case R.id.tv_drawer_layout:
                    DrawerLayoutActivity.start(this);
                    break;
                case R.id.tv_clear_dialog:
                    showClearCacheDialog(false);
                    break;
                case R.id.tv_action_bar:
                    ActionBarActivity.start(this);
                    break;
            }
        }
    }


    public static boolean checkPermission(Context context, String permission) {
        boolean result = false;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Class<?> clazz = Class.forName("android.content.Context");
                Method method = clazz.getMethod("checkSelfPermission", String.class);
                int rest = (Integer) method.invoke(context, permission);
                if (rest == PackageManager.PERMISSION_GRANTED) {
                    result = true;
                } else {
                    result = false;
                }
            } catch (Exception e) {
                result = false;
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        }
        return result;
    }

    public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String device_id = null;
            if (checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
                device_id = tm.getDeviceId();
            }
            String mac = null;
            FileReader fstream = null;
            try {
                fstream = new FileReader("/sys/class/net/wlan0/address");
            } catch (FileNotFoundException e) {
                fstream = new FileReader("/sys/class/net/eth0/address");
            }
            BufferedReader in = null;
            if (fstream != null) {
                try {
                    in = new BufferedReader(fstream, 1024);
                    mac = in.readLine();
                } catch (IOException e) {
                } finally {
                    if (fstream != null) {
                        try {
                            fstream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            json.put("mac", mac);
            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }
            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);
            }
            json.put("device_id", device_id);
            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void showClearCacheDialog(boolean isProgress) {
        if (isProgress) {
            progressDialog = new ProgressDialog(this);
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMessage("请稍后");
            progressDialog.show();
        } else {
            new ProgressDialog
                    .Builder(this, R.style.ProgressBar)
                    .setTitle("确定要清除缓存吗？")
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                        String deviceInfo = getDeviceInfo(TypeViewActivity.this);
//                        Log.d(TAG, "onClick: de=" + deviceInfo);
                            showClearCacheDialog(true);
                        }
                    })
                    .setNegativeButton(R.string.text_canncel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //格式化很方便 eg. 12.23MB
//                            Formatter.formatFileSize(TypeViewActivity.this,1234L);
                        }
                    })
                    .create()
                    .show();
        }
    }
}
