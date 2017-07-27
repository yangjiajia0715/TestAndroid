package com.testandroid.yang.activity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.testandroid.yang.BuildConfig;
import com.testandroid.yang.R;
import com.testandroid.yang.adapter.ImageAdapter;
import com.testandroid.yang.common.ImageItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 选择文件 分享
 * Created by yangjiajia on 2017/7/24.
 * 配合{@link ShareSimpleDateActivity}
 */

public class SelectFileActivity extends ListActivity {
    private static final String TAG = "SelectFileActivity";

    private List<ImageItem> datas;
    private ListView listView;
    private File[] files;
    private Uri uri;

    public static void start(Context context) {
        Intent starter = new Intent(context, SelectFileActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = getListView();
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        datas = new ArrayList<>();
        File filesDir = new File(getFilesDir(), "images");
        files = filesDir.listFiles();

        if (files != null) {
            for (File file : files) {
                ImageItem imageItem = new ImageItem();
                imageItem.setName(file.getName());
                datas.add(imageItem);
            }

            datas.add(new ImageItem("撤销com.yang.thirdapp uri权限"));
            datas.add(new ImageItem("保存"));

            ImageAdapter imageAdapter = new ImageAdapter(datas);
            listView.setAdapter(imageAdapter);

            try {
                String pathSegment = Uri.fromFile(new File("")).getLastPathSegment();
                File tempFile = File.createTempFile(pathSegment, null, getCacheDir());

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, R.string.no_date, Toast.LENGTH_SHORT).show();
        } 

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.d(TAG, "onCreate: position=" + position);
        String aPackage = getIntent().getPackage();
        Log.d(TAG, "onCreate: aPackage=" + aPackage);

        if (position == datas.size() - 1) {
            finish();
        } else if (position == datas.size() - 2) {
            if (uri != null) {
                revokeUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Toast.makeText(this, "撤销权限成功", Toast.LENGTH_SHORT).show();
            }
        } else {
            uri = FileProvider.getUriForFile(this, BuildConfig.FILE_PROVIDER_AUTHORITIES, files[position]);
            Intent intent = new Intent();
            //使用该方法授权 如果客户app不在需要该权限，需要通过revokeUriPermission撤销，或者系统重启(reboot)
//            测试 好用！
//            grantUriPermission("com.yang.thirdapp", uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);

            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, getContentResolver().getType(uri));
            setResult(RESULT_OK, intent);
        }
    }

}
