package com.testandroid.yang.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.testandroid.yang.R;
import com.testandroid.yang.activity.AndroidChangeActivity;
import com.testandroid.yang.activity.AppBarTabLayout;
import com.testandroid.yang.activity.BlueToothActivity;
import com.testandroid.yang.activity.BottomSheetActivity;
import com.testandroid.yang.activity.CoordinatorAppBarActivity;
import com.testandroid.yang.activity.CoordinatorLayoutActivity;
import com.testandroid.yang.activity.DataBindingActivity;
import com.testandroid.yang.activity.GlideActivity;
import com.testandroid.yang.activity.MediaProjectActivity;
import com.testandroid.yang.activity.MenuActivity;
import com.testandroid.yang.activity.OkHttpActivity;
import com.testandroid.yang.activity.OpenGLActivity;
import com.testandroid.yang.activity.PanelActivity;
import com.testandroid.yang.activity.PermissiondispatcherActivity;
import com.testandroid.yang.activity.RxJava2Activity;
import com.testandroid.yang.activity.RxJavaOperateActivity;
import com.testandroid.yang.activity.Rxjava2New2Activity;
import com.testandroid.yang.activity.Rxjava2NewActivity;
import com.testandroid.yang.activity.ServiceActivity;
import com.testandroid.yang.activity.TestAppBar;
import com.testandroid.yang.common.HomeInfo;

/**
 * 新技术
 * Created by yangjiajia on 2017/9/13.
 */

public class NewTechFragment extends HomeBaseFragment {
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public static NewTechFragment newInstance() {
        Bundle bundle = new Bundle();
        NewTechFragment fragment = new NewTechFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initView() {
        items.add(new HomeInfo("tv_test_tinker", R.id.tv_test_tinker, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("tv_test_RxJava2_main", R.id.tv_test_RxJava_main, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("Rxjava2_2017年8月", R.id.tv_test_RxJava_new, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("Rxjava2_2018年6月", R.id.tv_test_RxJava_new_2, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("tv_test_RxJava2_operate", R.id.tv_test_RxJava_operate, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("tv_Data_Binding", R.id.tv_Data_Binding, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("tv_CoordinatorLayout", R.id.tv_CoordinatorLayout, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("tv_CoordinatorLayout_appbar", R.id.tv_CoordinatorLayout_appbar, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("tv_appbar_tab_layout", R.id.tv_appbar_tab_layout, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("tv_tv_test_app_bar", R.id.tv_tv_test_app_bar, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("RxJava", R.id.tv_tv_test_app_bar, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("OkHttp3", R.id.tv_ok_http, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("BottomSheet", R.id.tv_bottomsheet, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("tv_panel", R.id.tv_panel, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("Permissionsdispatcher", R.id.tv_permissionsdispatcher, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("Glide", R.id.tv_glide, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("系统行为变更", R.id.tv_android_change, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("MediaProjection", R.id.tv_media_projection, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("Service相关", R.id.tv_service, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("OpenGL相关", R.id.tv_open_gl, HomeInfo.HomeGroup.NewTech));
        items.add(new HomeInfo("蓝牙相关", R.id.tv_bluetooth, HomeInfo.HomeGroup.NewTech));
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent;
        if (v.getTag(R.id.tag_first) instanceof HomeInfo) {
            HomeInfo info = (HomeInfo) v.getTag(R.id.tag_first);
            switch (info.id) {
                case R.id.tv_test_tinker:
                    break;
                case R.id.tv_test_RxJava_main:
                    RxJava2Activity.start(context);
                    break;
                case R.id.tv_test_RxJava_new:
                    Rxjava2NewActivity.start(context);
                    break;
                case R.id.tv_test_RxJava_new_2:
                    Rxjava2New2Activity.start(context);
                    break;
                case R.id.tv_test_RxJava_operate:
                    RxJavaOperateActivity.start(context);
                    break;
                case R.id.tv_Data_Binding:
                    DataBindingActivity.start(context);
                    break;
                case R.id.tv_CoordinatorLayout:
                    CoordinatorLayoutActivity.start(context);
                    break;
                case R.id.tv_CoordinatorLayout_appbar:
                    CoordinatorAppBarActivity.start(context);
                    break;
                case R.id.tv_appbar_tab_layout:
                    AppBarTabLayout.start(context);
                    break;
                case R.id.tv_tv_test_app_bar:
                    TestAppBar.start(context);
                    break;
                case R.id.tv_menu:
                    MenuActivity.start(context);
                    break;
                case R.id.tv_ok_http:
                    OkHttpActivity.start(context);
                    break;
                case R.id.tv_bottomsheet:
                    BottomSheetActivity.start(context);
                    break;
                case R.id.tv_panel:
                    PanelActivity.start(context);
                    break;
                case R.id.tv_permissionsdispatcher:
                    PermissiondispatcherActivity.start(context);
                    break;
                case R.id.tv_glide:
                    GlideActivity.start(context);
                    break;
                case R.id.tv_android_change:
                    AndroidChangeActivity.start(context);
                    break;
                case R.id.tv_media_projection:
                    MediaProjectActivity.start(context);
                    break;
                case R.id.tv_service:
                    ServiceActivity.start(context);
                    break;
                case R.id.tv_open_gl:
                    OpenGLActivity.start(context);
                    break;
                case R.id.tv_bluetooth:
                    BlueToothActivity.start(context);
                    break;
                default:
                    break;

            }
        }
    }
}
