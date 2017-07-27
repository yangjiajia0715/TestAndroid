package com.testandroid.yang.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 监听媒体按钮事件
 * Created by yangjiajia on 2017/7/26.
 */

public class RemoteControlReceiver extends BroadcastReceiver {
    private static final String TAG = "RemoteControlReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "onReceive: action=" + action);
        Log.d(TAG, "onReceive: intent=" + intent);
    }
}
