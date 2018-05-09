package com.testandroid.yang.activity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.math.MathUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.testandroid.yang.R;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 保存文件
 * Created by yangjiajia on 2017/7/25.
 * file:///D:/sdk/docs/training/basics/data-storage/files.html
 * 外部私有目录是否权限？
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

//        Environment.DIRECTORY_ALARMS;
//        Environment.DIRECTORY_MUSIC;
        //区别：

        //测试请放开
//        getExternalDir();

        //测试请放开
//        getInternalDir();

        //测试请放开
        ioUtils();

        other();
    }

    /**
     * https://github.com/jingle1267/android-utils
     */
    private void other() {
//        URLUtil.guessFileName("", "", "");
//        DatabaseUtils.
//        CommonTimeUtils
        int clamp = MathUtils.clamp(2, 1, 4);//固定 约束范围
        Log.d(TAG, "other: clamp=" + clamp);
//        TimeUtils.
//        DebugUtils.isObjectSelected(3);
//        DrmUtils.
//        PhoneNumberUtils.compare()

//        ItemTouchUIUtil.
//        AsyncListUtil
//        NavUtils.getParentActivityName()

        HashMap<String, String> map;
    }

    /**
     * 系统提供的格式化类： Formatter.formatFileSize(this, 100000);
     */
    private void ioUtils() {

        File ioTestFile = null;
        try {
            ioTestFile = new File(getExternalFilesDir(null), "ioutils.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(ioTestFile);
            String string = getResources().getString(R.string.text_temp_content_long);
            fileOutputStream.write(string.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        FileUtils.cleanDirectory(null);
        String s = FileUtils.byteCountToDisplaySize(1028000);
        Log.d(TAG, "ioUtils--displaySize: " + s);//1003KB
        String displaySize = FileUtils.byteCountToDisplaySize(1028);
        Log.d(TAG, "ioUtils--displaySize: " + displaySize);//1KB
        String displaySize3 = FileUtils.byteCountToDisplaySize(128_000_000_000L);
        Log.d(TAG, "ioUtils--displaySize3: " + displaySize3);//119 GB
        InputStream inputStream = getResources().openRawResource(R.raw.liushishi);
        File externalFilesDir1 = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(externalFilesDir1, "liushishi_copy.jpg");
        try {
            FileUtils.copyToFile(inputStream, file);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
        }

        try {
            List<String> readLines = FileUtils.readLines(ioTestFile, "utf-8");
            for (String readLine : readLines) {
                Log.d(TAG, "--ioUtils-readLine=" + readLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        FileFilter fileFilter = new FileFilter() {
//            @Override
//            public boolean accept(File pathname) {
//                return true;
//            }
//        };
//        ioTestFile.listFiles(fileFilter);

    }

    private void getInternalDir() {
        try {
            FileOutputStream fileInputStream = openFileOutput("test1.txt",Context.MODE_PRIVATE);
            FileOutputStream fileInputStream2 = openFileOutput("test2.txt",Context.MODE_PRIVATE);
            fileInputStream.write("test1".getBytes());
            fileInputStream2.write("test2".getBytes());

            fileInputStream.close();
            fileInputStream2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        /data/data/com.testandroid.yang/cache
        File cacheDir = getCacheDir();
        Log.d(TAG, "--Internal-cacheDir=" + cacheDir);

//        /data/data/com.testandroid.yang/files
        File filesDir = getFilesDir();
        Log.d(TAG, "--Internal-filesDir=" + filesDir);

//        /data/data/com.testandroid.yang/app_getDir
        File getDir = getDir("getDir", Context.MODE_PRIVATE);
        Log.d(TAG, "--Internal-getDir=" + getDir);

        String[] strings = fileList();
        if (strings != null) {
            for (String string : strings) {
                Log.d(TAG, "--Internal-strings=" + string);
            }
        }

        boolean delete = deleteFile("test1.txt");
        Log.d(TAG, "--Internal-delete=" + delete);
    }

    /**
     * 自动创建目录
     * getExternalFileDir()  /strorage/emulated/0/android/date/{package}/file/
     * getExternalCacheDir()
     */
    private void getExternalDir() {
        File externalFilesDir = getExternalFilesDir(null);
        Log.d(TAG, "SignActivity-externalFilesDir=" + externalFilesDir);
//        /storage/emulated/0/Android/data/com.cuotiben.jingzhunketang/files

        File externalFilesDirPic = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        Log.d(TAG, "SignActivity-externalFilesDirPic=" + externalFilesDirPic);

        File externalFilesDownloads = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        Log.d(TAG, "SignActivity-externalFilesDownloads=" + externalFilesDownloads);

//        /storage/emulated/0/Android/data/com.testandroid.yang/files/yangjiajia
        File externalFilesyangjiajia = getExternalFilesDir("yangjiajia");//自动创建该目录
        Log.d(TAG, "SignActivity-externalFilesyangjiajia=" + externalFilesyangjiajia);

        //  /storage/emulated/0/Android/data/com.testandroid.yang/cache
        File externalCacheDir = getExternalCacheDir();
        Log.d(TAG, "SignActivity-externalCacheDir=" + externalCacheDir);

//        /data
        File dataDirectory = Environment.getDataDirectory();
        Log.d(TAG, "SignActivity-dataDirectory=" + dataDirectory);

        if (Build.VERSION.SDK_INT >= 19) {
            File[] externalFilesDirs = getExternalFilesDirs(null);
            for (File filesDir : externalFilesDirs) {
                Log.d(TAG, "SignActivity-filesDir=" + filesDir);
            }
            //  /storage/emulated/0/Android/data/com.cuotiben.jingzhunketang/files
            //  null

            File[] hhhhs = getExternalFilesDirs("hhhh");
            for (File filesDir : hhhhs) {
                Log.d(TAG, "SignActivity-hhhhs=" + filesDir);
            }
            //  /storage/emulated/0/Android/data/com.testandroid.yang/files/hhhh
            //  null

        }
        //  /mnt/sdcard ---> strorage/sdcard0
        //  /strorag/sdcard0
        //  /strorag/sdcard1
        //  /strorag/sdcard2
        //TF卡
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

                    //openFileOutput() 文件不存在会自动创建
                    FileOutputStream output = openFileOutput("openFileOutput目录", MODE_APPEND);//data/data/files
                    output.write("文件不存在会自动创建".getBytes());
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
