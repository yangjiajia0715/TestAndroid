package com.testandroid.yang;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by yangjiajia on 2018/5/8.
 */
public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            registerCallbacks();
        }
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
