package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;

/**
 * Created by yangjiajia on 2017/6/29.
 */

public class LaunchModeB_Activity extends LaunchModeBaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, LaunchModeB_Activity.class);
        context.startActivity(starter);
    }
}