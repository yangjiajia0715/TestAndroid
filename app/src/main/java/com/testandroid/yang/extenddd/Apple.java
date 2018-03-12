package com.testandroid.yang.extenddd;

import android.util.Log;

/**
 * 测试继承关系
 * Created by yangjiajia on 2017/5/3 0003.
 */

public class Apple extends Fruit {
    private static final String TAG = "Apple";

    static {
        Log.d(TAG, "Apple static initializer: ");
    }

    public Apple() {
        //默认会调用无参的构造方法
        super("33");
        Log.d(TAG, "Apple: ");
    }

    public void test1(){
        getShape();
    }

    @Override
    public void getColor() {
        super.getColor();
    }
    
}
