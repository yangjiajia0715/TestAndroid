package com.example.testandroid.common;

import android.util.Log;
import android.view.View;

/**
 * Created by yangjiajia on 2017/1/9 0009.
 */

public class MyCLickHandler {
    private static final String TAG = "MyCLickHandler";

    public void onClick1(View view){
        Log.d(TAG, "onClick1: view=" + view);
    }

    public void onClick2(View view){
        Log.d(TAG, "onClick2: view=" + view);
    }
}
