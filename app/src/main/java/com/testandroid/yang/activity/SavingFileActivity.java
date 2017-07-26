package com.testandroid.yang.activity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 保存文件
 * Created by yangjiajia on 2017/7/25.
 * file:///D:/sdk/docs/training/basics/data-storage/files.html
 */

public class SavingFileActivity extends ListActivity {
    private static final String TAG = "SavingFileActivity";

    public static void start(Context context) {
        Intent starter = new Intent(context, SavingFileActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> dates = new ArrayList<>();
        dates.add("保存文件");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dates);

        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                try {
                    File externalFilesDir = getExternalFilesDir(null);
                    File filesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//                    /storage/emulated/0/Android/data/com.testandroid.yang/files
                    Log.d(TAG, "onListItemClick: externalFilesDir=" + externalFilesDir);
//                    /storage/emulated/0/Android/data/com.testandroid.yang/files/Pictures
                    Log.d(TAG, "onListItemClick: filesDir=" + filesDir);

                    File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "aa.txt");
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write("下载目录".getBytes());
                    fileOutputStream.close();

                    FileOutputStream output = openFileOutput("test目录", MODE_APPEND);//data/data/files
                    output.write("家教姐姐军军多付军奥扩多付军".getBytes());
                    output.close();

                    File no_exit = new File(getExternalFilesDir("no_exit"), "aa.txt");
                    FileOutputStream fos = new FileOutputStream(no_exit);
                    fos.write("no_exit".getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 1:

                break;
        }
    }
}
