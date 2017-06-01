package com.testandroid.yang.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Service
 * Created by yangjiajia on 2017/6/1 0001.
 */

public class TestServicer extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
