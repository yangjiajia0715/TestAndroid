package com.testandroid.yang.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yangjiajia on 2017/4/7 0007.
 */

public class TestReceiverPriorityMiddle extends BroadcastReceiver {
    private static final String TAG = "BroadcastReceiver";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: getClass=" + getClass().getSimpleName()
                + ",getAction=" + intent.getAction()
                + ",time=" + sdf.format(new Date())
                + ",intent=" + intent);

        int key_data = intent.getIntExtra("key_data", 0);

        int resultCode = getResultCode();
        String resultData = getResultData();
        Bundle resultExtras = getResultExtras(true);

        Log.d(TAG, "Middle--onReceive: resultCode=" + resultCode);
        Log.d(TAG, "Middle--onReceive: resultData=" + resultData);
        Log.d(TAG, "Middle--onReceive: resultExtras=" + resultExtras);
        Log.d(TAG, "Middle--onReceive: resultExtras result=" + resultExtras.getString("resultExtras"));

        Log.d(TAG, "onReceive: key_data=" + key_data);
        if (key_data == 0) {
            boolean abortBroadcast = getAbortBroadcast();
            Log.d(TAG, "onReceive: abortBroadcast=" + abortBroadcast);
            abortBroadcast();
            abortBroadcast = getAbortBroadcast();
            Log.d(TAG, "onReceive: abortBroadcast=" + abortBroadcast);
        }
    }
}
