package com.example.testandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * 具有缩放功能的imageview
 * author: yangjiajia
 * create time: 2016/7/17.
 * description:
 * modify time: 2016/7/17 14:54
 */
public class ZoomImage extends ImageView implements ViewTreeObserver.OnGlobalLayoutListener {
    private String TAG = getClass().getSimpleName();
    private boolean mOnce = true;

    public ZoomImage(Context context) {
//        super(context);
        this(context,null);
    }

    public ZoomImage(Context context, AttributeSet attrs) {
//        super(context, attrs);
        this(context,attrs,0);
        Log.d(TAG, "ZoomImage: attrs=" + attrs.toString());
    }

    public ZoomImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow: ");
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow: ");
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        Log.d(TAG, "onGlobalLayout:");

        if (mOnce) {
            int width = getWidth();
            int height = getHeight();

            float scale = 1.0f;

            Log.d(TAG, "onGlobalLayout:width=" + width + ",height=" + height);

            Drawable d = getDrawable();
            if (d == null) {
                return;
            }

            int dw = d.getIntrinsicWidth();
            int dh = d.getIntrinsicHeight();
            Log.d(TAG, "onGlobalLayout:dw="+ dw + ",dh=" + dh);

            if (dw > width && dh < height) {
                scale = width * 1.0f / dw;
            }

            if (dh > height && dw < width)
                scale = height * 1.0f / dh;

            if ((dw > width && dh < height) && (dh < height && dw < width)) {
                scale = Math.min(width * 1.0f /dw,height* 1.0f/dh);
            }

            mOnce = false;
        }



    }
}
