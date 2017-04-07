package com.testandroid.yang.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yangjiajia on 2017/4/7 0007.
 */

public class TestReceiverPriorityLow extends BroadcastReceiver {
    private static final String TAG = "BroadcastReceiver";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: getClass=" + getClass().getSimpleName()
                + ",getAction=" + intent.getAction()
                + ",time=" + sdf.format(new Date())
                + ",intent=" + intent);

    }
}
