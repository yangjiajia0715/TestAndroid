package com.testandroid.yang.retrofit;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * TestIntercept
 * Created by yangjiajia on 2017/4/25 0025.
 */

public class Test2Intercept implements Interceptor {
    private static final String TAG = "Test2Intercept";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.d(TAG, "intercept: Test2Intercept---chain= " + chain.toString());
        return chain.proceed(chain.request());
    }
}
