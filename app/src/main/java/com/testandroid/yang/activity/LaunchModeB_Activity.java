package com.testandroid.yang.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

/**
 * Created by yangjiajia on 2017/6/29.
 */

public class LaunchModeB_Activity extends LaunchModeBaseActivity {
    private static final String TAG = "LaunchModeB_Activity";

    public static void start(Activity context) {
        Log.d(TAG, "-----------------start: " + context.getTaskId());
        Intent starter = new Intent(context, LaunchModeB_Activity.class);
//        starter.setFlags(starter.getFlags() | Intent.FLAG_ACTIVITY_NEW_TASK);
//        starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK /*| Intent.FLAG_ACTIVITY_CLEAR_TOP*/);
//        starter.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        starter.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(starter);
//        context.startActivity(starter,111);
    }
}
