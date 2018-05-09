package com.testandroid.yang.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 录屏
 * 如何跨进程通信（不能调试打log?）
 * Created by yangjiajia on 2018/5/9.
 */
public class ScreenCaptureService extends Service {
    private static final String TAG = "ScreenCaptureService";
    private static final String CHANNEL = "CHANNEL";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String channel = intent.getStringExtra(CHANNEL);
        Log.d(TAG, "onStartCommand: channel=" + channel + ",startId=" + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String channel = intent.getStringExtra(CHANNEL);
        Log.d(TAG, "onBind: channel=" + channel );
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
