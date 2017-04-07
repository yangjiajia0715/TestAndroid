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

public class TestReceiverPriorityHigh extends BroadcastReceiver {
    private static final String TAG = "BroadcastReceiver";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: getClass=" + getClass().getSimpleName()
                + ",getAction=" + intent.getAction()
                + ",time=" + sdf.format(new Date())
                + ",intent=" + intent);

        intent.putExtra("key_data", 100);

        Log.d(TAG, "onReceive: intent=" + intent);

        int resultCode = getResultCode();
        String resultData = getResultData();
        Bundle resultExtras = getResultExtras(true);

        Log.d(TAG, "onReceive: resultCode=" + resultCode);
        Log.d(TAG, "onReceive: resultData=" + resultData);
        Log.d(TAG, "onReceive: resultExtras=" + resultExtras);
        resultExtras.putString("resultExtras", "resultExtrasjkjkjk");
        setResult(1, "高优先级", resultExtras);
    }
}
