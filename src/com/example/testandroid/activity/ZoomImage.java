package com.example.testandroid.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * 具有缩放功能的imageview
 * author: yangjiajia
 * create time: 2016/7/17.
 * description:
 * modify time: 2016/7/17 14:54
 */
public class ZoomImage extends ImageView implements ViewTreeObserver.OnGlobalLayoutListener, ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener {
    private String TAG = getClass().getSimpleName();
    private boolean mOnce = true;
    private Matrix matrix;
    private float mInitScale;
    private float mMidScale;
    private float mMaxScale;
    private ScaleGestureDetector gestureDetector;

    public ZoomImage(Context context) {
        this(context, null);
    }

    public ZoomImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Log.d(TAG, "ZoomImage: attrs=" + attrs.toString());
    }

    public ZoomImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setScaleType(ScaleType.MATRIX);
        matrix = new Matrix();
        gestureDetector = new ScaleGestureDetector(context,this);
        setOnTouchListener(this);
        init();
    }

    private void init() {

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
            Log.d(TAG, "onGlobalLayout:dw=" + dw + ",dh=" + dh);

            if (dw > width && dh < height) {
                scale = width * 1.0f / dw;
            }

            if (dh > height && dw < width)
                scale = height * 1.0f / dh;

            if ((dw > width && dh > height) || (dh < height && dw < width)) {
                scale = Math.min(width * 1.0f / dw, height * 1.0f / dh);
            }

            mInitScale = scale;
            mMidScale = scale * 2;
            mMaxScale = scale * 4;

            matrix.postTranslate(getWidth()/2 - dw/2,getHeight()/2 - dh /2);
            matrix.postScale(scale,scale,getWidth() / 2,getHeight()/2);
            setImageMatrix(matrix);

            mOnce = false;
        }


    }

    public float getScale(){
        float[] valuse = new float[9];
        matrix.getValues(valuse);
        return valuse[Matrix.MSCALE_X];
    }

    //缩放的区间：mid,max
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scaleFactor = detector.getScaleFactor();
        Log.d(TAG, "onScale: detector--scaleFactor=" + scaleFactor);
        if (getDrawable() == null) {
            return true;
        }
        float scale = getScale();
        if ((scale < mMaxScale && scaleFactor > 1.0f) || (scale > mInitScale && scaleFactor < 1.0f)) {

            if (scale * scaleFactor < mInitScale) {
                scaleFactor = mInitScale / scale;
            }

            if (scale * scaleFactor > mMaxScale) {
                scaleFactor = mMaxScale / scale;
            }

            matrix.postScale(scaleFactor, scaleFactor, getWidth() / 2, getHeight() / 2);
            setImageMatrix(matrix);
        }

        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        Log.d(TAG, "onScaleBegin: detector=" + detector);
//        return false;//default
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        Log.d(TAG, "onScaleEnd: detector");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }
}
