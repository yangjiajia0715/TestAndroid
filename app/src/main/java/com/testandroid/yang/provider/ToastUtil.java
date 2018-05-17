package com.testandroid.yang.provider;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.testandroid.yang.BuildConfig;


/**
 * 防止重复toast,并且新的Toast会立刻覆盖旧的Toast，不会排队等待
 * Created by yangjiajia on 2018/5/10.
 */
public final class ToastUtil {
    private static final String TAG = "ToastUtil";
    private static final boolean isDebug = BuildConfig.DEBUG;

    private static String sLastText;
    private static Toast sToast;
    private static long sLastTime;

    /**
     * 只有debug的时候才显示
     */
    public static void showShortDebug(Context context, String text) {
        if (isDebug) {
            show(context, text, true);
        }
    }

    /**
     * 只有debug的时候才显示
     */
    public static void showLengthDebug(Context context, String text) {
        if (isDebug) {
            show(context, text, false);
        }
    }

    public static void showShort(Context context, String text) {
        show(context, text, true);
    }

    public static void showLength(Context context, String text) {
        show(context, text, false);
    }

    private static void show(Context context, String text, boolean showShort) {
        if (sToast == null) {
            sToast = Toast.makeText(context.getApplicationContext(), text
                    , showShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
            sToast.show();
            sLastTime = System.currentTimeMillis();
            Log.d(TAG, "show: init");
        } else {
            if (TextUtils.equals(sLastText, text)) {
                Log.d(TAG, "show: 内容一样返回");
                return;
            }
            sToast.setText(text);
            sLastText = text;
            long nowTime = System.currentTimeMillis();
            if (nowTime - sLastTime < (showShort ? 400 : 600)) {
                Log.d(TAG, "show: 时间短返回 ");
                return;
            }
            sToast.setDuration(showShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
            sToast.show();
            sLastTime = nowTime;
            Log.d(TAG, "show: text=" + text);
        }
    }

    public static void cancel(){
        Log.d(TAG, "cancel: sToast=" + sToast);
        if (sToast != null) {
            sToast.cancel();
        }
    }
}
