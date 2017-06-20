package com.testandroid.yang.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Utility
 * Created by yangjiajia on 2017/6/20 0020.
 */

public class Utility {
    private static final String TAG = "Utility";
    private static final float density;

    //TypedValue.applyDimension(int unit, float value, DisplayMetrics metrics) 方便dp, px, sp 之间的转换。
//    Resources.getSystem().getDisplayMetrics().density 可以不用 Context 也能获取屏幕密度哦
    static {
        density = Resources.getSystem().getDisplayMetrics().density;
//    Resources.getSystem().getDisplayMetrics().density; //可以不用 Context 也能获取屏幕密度哦
    }

    public static int dp2px(float dp) {
        Log.d(TAG, "dp2px: dp=" + dp);
//        DisplayMetrics metrics = new DisplayMetrics();
//        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,5, metrics);
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        Log.d(TAG, "dp2px: displayMetrics=" + displayMetrics.density);
        Log.d(TAG, "dp2px: dp * density=" + dp * density);
//        Math.floor()
        return Math.round(dp * density);
    }
}
