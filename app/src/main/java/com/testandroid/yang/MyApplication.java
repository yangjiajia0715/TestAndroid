package com.testandroid.yang;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 *
 * @author yangjiajia
 * @date 2018/5/8
 */
public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    Thread.UncaughtExceptionHandler uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread t, Throwable e) {

            Log.d(TAG, "线程对象的uncaughtExceptionHandler: e=" + e.getMessage());

        }
    };

    Thread.UncaughtExceptionHandler sUncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            Log.d(TAG, "全局的uncaughtExceptionHandler: e=" + e.getMessage());
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

//        setUnCaughtExceptionHandler();

        if (BuildConfig.DEBUG) {
            registerCallbacks();
        }

    }

    private void setUnCaughtExceptionHandler() {
        Thread currentThread = Thread.currentThread();
        Log.d(TAG, "Application---onCreate currentThread: " + currentThread.getName());
        currentThread.setUncaughtExceptionHandler(uncaughtExceptionHandler);

        Thread.setDefaultUncaughtExceptionHandler(sUncaughtExceptionHandler);
    }

    /**
     * 测试用
     */
    private void registerCallbacks() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.d(TAG, "LearningApplication--onActivityCreated: " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.d(TAG, "LearningApplication--onActivityStarted: " + activity.getClass().getSimpleName());

            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.d(TAG, "LearningApplication--onActivityResumed: " + activity.getClass().getSimpleName());

            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d(TAG, "LearningApplication--onActivityPaused: " + activity.getClass().getSimpleName());

            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.d(TAG, "LearningApplication--onActivityStopped: " + activity.getClass().getSimpleName());

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.d(TAG, "LearningApplication--onActivitySaveInstanceState: " + activity.getClass().getSimpleName());

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.d(TAG, "LearningApplication--onActivityDestroyed: " + activity.getClass().getSimpleName());
            }
        });
    }
}
