package com.testandroid.yang.fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.testandroid.yang.ObservableScrollView.demo.ParallaxToolbarScrollViewActivity;
import com.testandroid.yang.ObservableScrollView.viewpager.ViewPagerTabActivity;
import com.testandroid.yang.R;
import com.testandroid.yang.activity.ActionBarActivity;
import com.testandroid.yang.activity.BannerViewPagerActivity;
import com.testandroid.yang.activity.BuildConfigFieldActivity;
import com.testandroid.yang.activity.CheckTextViewActivity;
import com.testandroid.yang.activity.CheckTextViewNewActivity;
import com.testandroid.yang.activity.ConstraintLayoutActivity;
import com.testandroid.yang.activity.CustomViewActivity;
import com.testandroid.yang.activity.DexClassLoadTestActivity;
import com.testandroid.yang.activity.DialogListActivity;
import com.testandroid.yang.activity.DrawerLayoutActivity;
import com.testandroid.yang.activity.EditTextActivity;
import com.testandroid.yang.activity.FitSystemWindowActivity;
import com.testandroid.yang.activity.FitSystemwindowActivity2;
import com.testandroid.yang.activity.FragmentActivity;
import com.testandroid.yang.activity.FrgStatePagerAdaperActivity;
import com.testandroid.yang.activity.FullScreenActivity;
import com.testandroid.yang.activity.ItemTypeListViewActivity;
import com.testandroid.yang.activity.ListViewClipActivity;
import com.testandroid.yang.activity.ListviewChoiceActivity;
import com.testandroid.yang.activity.ParallaxActivity;
import com.testandroid.yang.activity.PhotoViewActivity;
import com.testandroid.yang.activity.RecycleViewActivity;
import com.testandroid.yang.activity.RecycleViewCeilingActivity;
import com.testandroid.yang.activity.RotationActivity;
import com.testandroid.yang.activity.ScrollViewClipActivity;
import com.testandroid.yang.activity.SearchViewActivity;
import com.testandroid.yang.activity.SearchViewSecondActivity;
import com.testandroid.yang.activity.VLayoutActivity;
import com.testandroid.yang.activity.ViewPagerActivity;
import com.testandroid.yang.activity.ViewPagerAllActivity;
import com.testandroid.yang.activity.ZoomImageActivity;
import com.testandroid.yang.common.HomeInfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 主页
 * Created by yangjiajia on 2017/9/13.
 */

public class HomePageFragment extends HomeBaseFragment {
    private ProgressDialog progressDialog;
    private Context context;

    public static HomePageFragment newInstance() {
        Bundle bundle = new Bundle();
        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void initView() {

        items.add(new HomeInfo("tv_zoom_image", R.id.tv_zoom_image, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_view_pager_11", R.id.tv_test_view_pager_11, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_parallax", R.id.tv_parallax, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_test_listview_clip", R.id.tv_test_listview_clip, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_test_scrollview_clip", R.id.tv_test_scrollview_clip, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_test_viewpager_all", R.id.tv_test_viewpager_all, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_parallaxtoolbar_scrollview_activity", R.id.tv_parallaxtoolbar_scrollview_activity, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_fragement_pager_adapter", R.id.tv_fragement_pager_adapter, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_item_type_listview", R.id.tv_item_type_listview, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_CheckedTextView", R.id.tv_CheckedTextView, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_CheckedTextView_new", R.id.tv_CheckedTextView_new, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_recycleview", R.id.tv_recycleview, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_recycleview_ceiling", R.id.tv_recycleview_ceiling, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_vLayouot", R.id.tv_vLayouot, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_full_screen", R.id.tv_full_screen, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_fitsystemwindow", R.id.tv_fitsystemwindow, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_fitsystemwindow2", R.id.tv_fitsystemwindow2, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_rotation_view", R.id.tv_rotation_view, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_customeview_view", R.id.tv_customeview_view, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_edittext", R.id.tv_edittext, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_Dialog", R.id.tv_dialog, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_listview_choice", R.id.tv_listview_choice, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_drawer_layout", R.id.tv_drawer_layout, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("tv_clear_dialog", R.id.tv_clear_dialog, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("ActionBar相关", R.id.tv_action_bar, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("fragment相关", R.id.tv_fragment, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("constraint_layout相关", R.id.tv_constraint_layout, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("SearchView", R.id.tv_search_view, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("SearchView2", R.id.tv_search_view_2, HomeInfo.HomeGroup.View));
        items.add(new HomeInfo("Banner_viewpager", R.id.tv_banner_viewpager, HomeInfo.HomeGroup.View));

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent;
        Object tag = v.getTag(R.id.tag_first);
        if (tag instanceof HomeInfo) {
            HomeInfo info = (HomeInfo) tag;
            switch (info.id) {
                case R.id.tv_zoom_image:
                    intent = new Intent(context, ZoomImageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_test_view_pager_11:
                    ViewPagerActivity.start(context);
                    break;
                case R.id.tv_parallax:
                    intent = new Intent(context, ParallaxActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_test_listview_clip:
                    intent = new Intent(context, ListViewClipActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_test_scrollview_clip:
                    intent = new Intent(context, ScrollViewClipActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_test_viewpager_all:
                    intent = new Intent(context, ViewPagerAllActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_parallaxtoolbar_scrollview_activity:
                    intent = new Intent(context, ParallaxToolbarScrollViewActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_test_view_pager_tab_dif:
                    intent = new Intent(context, ViewPagerTabActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_fragement_pager_adapter:
                    intent = new Intent(context, FrgStatePagerAdaperActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_item_type_listview:
                    intent = new Intent(context, ItemTypeListViewActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_CheckedTextView:
                    intent = new Intent(context, CheckTextViewActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_CheckedTextView_new:
                    CheckTextViewNewActivity.start(context);
                    break;
                case R.id.tv_Dex_ClassLoader:
                    intent = new Intent(context, DexClassLoadTestActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_photo_view:
                    intent = new Intent(context, PhotoViewActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_buildconfig_field:
                    intent = new Intent(context, BuildConfigFieldActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_recycleview:
                    RecycleViewActivity.start(context);
                    break;
                case R.id.tv_recycleview_ceiling:
                    RecycleViewCeilingActivity.start(context);
                    break;
                case R.id.tv_vLayouot:
                    VLayoutActivity.start(context);
                    break;
                case R.id.tv_full_screen:
                    FullScreenActivity.start(context);
                    break;
                case R.id.tv_fitsystemwindow:
                    FitSystemWindowActivity.start(context);
                    break;
                case R.id.tv_fitsystemwindow2:
                    FitSystemwindowActivity2.start(context);
                    break;
                case R.id.tv_rotation_view:
                    RotationActivity.start(context);
                    break;
                case R.id.tv_customeview_view:
                    CustomViewActivity.start(context);
                    break;
                case R.id.tv_edittext:
                    EditTextActivity.start(context);
                    break;
                case R.id.tv_dialog:
                    DialogListActivity.start(context);
                    break;
                case R.id.tv_listview_choice:
                    ListviewChoiceActivity.start(context);
                    break;
                case R.id.tv_drawer_layout:
                    DrawerLayoutActivity.start(context);
                    break;
                case R.id.tv_clear_dialog:
                    showClearCacheDialog(false);
                    break;
                case R.id.tv_action_bar:
                    ActionBarActivity.start(context);
                    break;
                case R.id.tv_fragment:
                    FragmentActivity.start(context);
                    break;
                case R.id.tv_constraint_layout:
                    ConstraintLayoutActivity.start(context);
                    break;
                case R.id.tv_search_view:
                    SearchViewActivity.start(context);
                    break;
                case R.id.tv_search_view_2:
                    SearchViewSecondActivity.start(context);
                    break;
                case R.id.tv_banner_viewpager:
                    BannerViewPagerActivity.start(context);
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
            progressDialog = new ProgressDialog(context);
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMessage("请稍后");
            progressDialog.show();
        } else {
            new ProgressDialog
                    .Builder(context, R.style.ProgressBar)
                    .setTitle("确定要清除缓存吗？")
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                        String deviceInfo = getDeviceInfo(TypeViewActivity.context);
//                        Log.d(TAG, "onClick: de=" + deviceInfo);
                            showClearCacheDialog(true);
                        }
                    })
                    .setNegativeButton(R.string.text_canncel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //格式化很方便 eg. 12.23MB
//                            Formatter.formatFileSize(TypeViewActivity.context,1234L);
                        }
                    })
                    .create()
                    .show();
        }
    }
}
