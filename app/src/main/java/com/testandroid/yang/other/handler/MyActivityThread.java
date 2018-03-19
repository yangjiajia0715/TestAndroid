package com.testandroid.yang.other.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by yangjiajia on 2018/3/13.
 */

public final class MyActivityThread {
    final H mH = new H();

    void performLaunchActivity(){

    }

    private class H extends Handler{
        public static final int MSG_SUCCESS = 111;
        public static final int MSG_FAILED = 222;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

            }
        }
    }

    public static void main(String[] args){
        Looper.prepareMainLooper();

//        new Handler();

        Looper.loop();
    }

    public void sendMessage(int what,Object obj,int arg1,int arg2,boolean async){
        Message msg = Message.obtain();
        msg.what = what;
        msg.obj = obj;
        msg.arg1 = arg1;
        msg.arg2 = arg2;
        if (async) {
//            msg.setAsynchronous(true);
        }

        mH.sendMessage(msg);
    }
}
