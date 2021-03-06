package com.testandroid.yang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.testandroid.yang.R;

import java.io.File;

import dalvik.system.DexClassLoader;

/**
 * DexClassLoader
 * author: yangjiajia
 * create time: 2016-9-11
 * desc:
 */
public class DexClassLoaderActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "DexClassLoaderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dex_class_loader);
        initView();
        File dataDirectory = Environment.getDataDirectory();
        File file = Environment.getExternalStorageDirectory();
        Log.d(TAG, "onCreate: dataDirectory=" + dataDirectory + ",file=" + file);

//        D/DexClassLoaderActivity: onCreate: dataDirectory=/data,file=/storage/emulated/0

        String path = Environment.getExternalStorageDirectory() + File.separator;
        String filename = "TestB.apk";

        DexClassLoader classLoader = new DexClassLoader(path + filename, path,
                null, getClassLoader());



    }

    private void initView() {
        findViewById(R.id.btn_viewanimator).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_viewanimator:
                Toast.makeText(this, "DexClassLoader", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
