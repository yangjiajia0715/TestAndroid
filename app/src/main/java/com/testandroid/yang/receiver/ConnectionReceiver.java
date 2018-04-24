package com.testandroid.yang.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.testandroid.yang.log.Log;

/**
 * 7.0行为变更：移除三项隐式广播 以优化内存使用和电量消耗
 * ConnectivityManager.CONNECTIVITY_ACTION
 * Camera.ACTION_NEW_PICTURE
 * Camera.ACTION_NEW_VIDEO
 * Created by yangjiajia on 2018/4/24.
 */

public class ConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("ConnectionReceiver--onReceive---getAction=" + intent.getAction());
    }
}
