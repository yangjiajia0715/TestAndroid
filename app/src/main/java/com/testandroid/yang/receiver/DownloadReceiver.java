package com.testandroid.yang.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.testandroid.yang.log.Log;

/**
 * DownloadReceiver
 * Created by yangjiajia on 2018/4/26.
 */
public class DownloadReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("--DownloadReceiver----onReceive----intent=" + intent);
    }
}
