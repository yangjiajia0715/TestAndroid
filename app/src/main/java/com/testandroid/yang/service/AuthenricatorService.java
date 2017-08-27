package com.testandroid.yang.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.testandroid.yang.account.Authenticator;


/**
 * AuthenricatorService
 * Created by yangjiajia on 2017/8/27.
 */

public class AuthenricatorService extends Service {
    private static final String TAG = "AuthenricatorService";
    private Authenticator authenticator;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        authenticator = new Authenticator(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: intent=" + intent);
        return authenticator.getIBinder();
    }
}
