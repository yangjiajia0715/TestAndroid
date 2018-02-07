package com.testandroid.yang.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.testandroid.yang.R;
import com.testandroid.yang.activity.ContentProviderActivity;
import com.testandroid.yang.activity.CursorLoadActivity;
import com.testandroid.yang.activity.DexClassLoadTestActivity;
import com.testandroid.yang.activity.DexClassLoaderActivity;
import com.testandroid.yang.activity.DisplayUserInfoActivity;
import com.testandroid.yang.activity.DownloadActivity;
import com.testandroid.yang.activity.OkIOActivity;
import com.testandroid.yang.activity.SaveToDataBaseActivity;
import com.testandroid.yang.common.HomeInfo;

import java.io.File;

import static com.netease.nimlib.sdk.media.player.AudioPlayer.TAG;

/**
 * 数据库
 * Created by yangjiajia on 2017/9/13.
 */

public class DataBaseFragment extends HomeBaseFragment {

    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public static DataBaseFragment newInstance() {
        Bundle bundle = new Bundle();
        DataBaseFragment fragment = new DataBaseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initView() {
        items.add(new HomeInfo("tv_Dex_ClassLoader", R.id.tv_Dex_ClassLoader, HomeInfo.HomeGroup.DataBase));
        items.add(new HomeInfo("tv_test_DatabaseUtil", R.id.tv_test_DatabaseUtil, HomeInfo.HomeGroup.DataBase));
        items.add(new HomeInfo("tv_test_CursorLoader", R.id.tv_test_CursorLoader, HomeInfo.HomeGroup.DataBase));
        items.add(new HomeInfo("tv_save_userinfo_to_database", R.id.tv_save_userinfo_to_database, HomeInfo.HomeGroup.DataBase));
        items.add(new HomeInfo("数据展示页--内部&外部", R.id.tv_save_data_display, HomeInfo.HomeGroup.DataBase));
        items.add(new HomeInfo("ContentProvider", R.id.tv_content_provider, HomeInfo.HomeGroup.DataBase));
        items.add(new HomeInfo("下载相关", R.id.tv_download, HomeInfo.HomeGroup.DataBase));
        items.add(new HomeInfo("OkIO", R.id.tv_ok_io, HomeInfo.HomeGroup.DataBase));
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
                case R.id.tv_DexClassLoader:
                    intent = new Intent(context, DexClassLoaderActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_Dex_ClassLoader:
                    intent = new Intent(context, DexClassLoadTestActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_test_DatabaseUtil:
                    String time = DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_DATE);
                    Toast.makeText(context, "DatabaseUtil 计划中 time:" + time, Toast.LENGTH_SHORT).show();
                    File cacheDir = context.getCacheDir();
                    Log.d(TAG, "tv_test_DatabaseUtil onClick: cacheDir=" + cacheDir);
                    break;
                case R.id.tv_test_CursorLoader:
                    intent = new Intent(context, CursorLoadActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_save_userinfo_to_database:
                    SaveToDataBaseActivity.start(context);
                    break;
                case R.id.tv_save_data_display:
                    DisplayUserInfoActivity.start(context);
                    break;
                case R.id.tv_content_provider:
                    ContentProviderActivity.start(context);
                    break;
                case R.id.tv_download:
                    DownloadActivity.start(context);
                    break;
                case R.id.tv_ok_io:
                    OkIOActivity.start(context);
                    break;
            }
        }
    }
}
