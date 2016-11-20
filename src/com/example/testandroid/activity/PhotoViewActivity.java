package com.example.testandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.testandroid.R;

/**
 * author: yangjiajia
 * create time: 2016/10/20 0020
 * desc:
 */

public class PhotoViewActivity extends Activity {
    private static final String TAG = "PhotoViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);

        Log.e(TAG, "onCreate: PhotoViewActivity");
        Log.d(TAG, "onCreate d: PhotoViewActivity");
        Log.v(TAG, "onCreate v: PhotoViewActivity");
    }
}
