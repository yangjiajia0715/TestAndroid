package com.testandroid.yang.extenddd;

import android.util.Log;

/**
 * 测试继承关系
 * Created by yangjiajia on 2017/5/3 0003.
 */

public class Fruit {
    private static final String TAG = "Fruit";

    public Fruit(String str) {
        Log.d(TAG, "Fruit: str=" + str);
    }

    static {
        Log.d(TAG, "Fruit static initializer: ");
    }

    public static void getShape() {

    }

    public void getColor() {

    }
}
