package com.testandroid.yang.provider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Process;

/**
 * 主进程异常时 kill录屏进程
 * Created by yangjiajia on 2018/5/11.
 */
public class ScreenCaptureBroadcastReceiver extends BroadcastReceiver {
    public static final String ACTION_SCREEN_CAPTION = "com.kidle.learning.screencapture";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION_SCREEN_CAPTION.equals(intent.getAction())) {

            ToastUtil.showLengthDebug(context,"收到广播 myPid=" + Process.myPid()
            + ",context=" + context);

//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    System.exit(0);
////                    Process.killProcess(Process.myPid());
//                }
//            }, 3000);
        }
    }
}
