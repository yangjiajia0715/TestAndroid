package com.example.testandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.example.testandroid.R;

import java.io.File;

import dalvik.system.DexClassLoader;

/**
 * DexClassLoadTestActivity
 * author: yangjiajia
 * create time: 2016-10-4
 * desc:
 */
public class DexClassLoadTestActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG = "CheckTextViewActivity";

    private ListView mListView;

    private boolean flag;

    private TextView tvShow;

    private TextSwitcher textSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dex_test);
        findViewById(R.id.dex_get_code_Cache).setOnClickListener(this);
        findViewById(R.id.dex_getexternalstoragedirectory).setOnClickListener(this);

        textSwitcher = (TextSwitcher) findViewById(R.id.dex_text_switcher);

        //yangjiajia
        initView();

        DexClassLoader dexClassLoader = new DexClassLoader("", getCodeCacheDir().getAbsolutePath(), null, getClassLoader());
//        dexClassLoader.
    }

    private void initView() {


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dex_get_code_Cache:
                File codeCacheDir = getCodeCacheDir();
                textSwitcher.setText("getCodeCacheDir=" + codeCacheDir.getAbsolutePath());
                break;
            case R.id.dex_getexternalstoragedirectory:

                File externalCacheDir = getExternalCacheDir();
                File externalStorageDirectory = Environment.getExternalStorageDirectory();

                String infos = "getExternalCacheDir=" + externalCacheDir + ",getExternalStorageDirectory=" + externalStorageDirectory;
                textSwitcher.setText(infos);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemClick: position=" + position);
    }
}
