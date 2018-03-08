package com.testandroid.yang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by yangjiajia on 2017/6/29.
 */

public class LaunchModeA_Activity extends LaunchModeBaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, LaunchModeA_Activity.class);
        context.startActivity(starter);
    }

    public static void startForResult(Activity activity,int reqCode) {
        Intent starter = new Intent(activity, LaunchModeA_Activity.class);
        activity.startActivityForResult(starter,reqCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_SUBJECT, "返回数据--------");
        setResult(RESULT_OK,intent);
    }
}
