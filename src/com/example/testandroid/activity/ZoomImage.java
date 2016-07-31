package com.example.testandroid.activity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
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
    private Matrix mMatrix;
    private float mInitScale;
    private float mMidScale;
    private float mMaxScale;
    private ScaleGestureDetector mScaleGestureDetector;
    //-------------------------------自由移动
    private int mLastPointerCount;//
    private float mLastX;//
    private float mLastY;//
    private int mTouchSlop;
    private boolean isCanDrag;
    //---------------------双击放大与缩小


    private boolean isCheckLeftAndRight;
    private boolean isCheckTopAndBottom;

    GestureDetector mGestureDetector;

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
        mMatrix = new Matrix();
        mScaleGestureDetector = new ScaleGestureDetector(context, this);
        setOnTouchListener(this);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                final float x = e.getX();
                final float y = e.getY();

                if (getScale() < mMidScale) {
                    ValueAnimator animator = ValueAnimator.ofFloat(getScale(), mMaxScale / getScale());
                    animator.setDuration(1000);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Log.d(TAG, "onAnimationUpdate: animation="+animation.getAnimatedValue());
//                            mMatrix.postScale((float)animation.getAnimatedValue(),(float)animation.getAnimatedValue(),x,y);

//                            setImageMatrix(mMatrix);
                        }
                    });
                    animator.start();


                }else {
                    mMatrix.postScale(mInitScale/getScale(),mInitScale/getScale(),x,y);
                    setImageMatrix(mMatrix);
                }
                return true;
            }
        });
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

            mMatrix.postTranslate(getWidth() / 2 - dw / 2, getHeight() / 2 - dh / 2);
            mMatrix.postScale(scale, scale, getWidth() / 2, getHeight() / 2);
            setImageMatrix(mMatrix);

            mOnce = false;
        }


    }

    public float getScale() {
        float[] valuse = new float[9];
        mMatrix.getValues(valuse);
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

            mMatrix.postScale(scaleFactor, scaleFactor, getWidth() / 2, getHeight() / 2);
            setImageMatrix(mMatrix);
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
        if (mGestureDetector.onTouchEvent(event))
            return true;


        mScaleGestureDetector.onTouchEvent(event);
        float x = 0;
        float y = 0;
        int pointerCount = event.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            x += event.getX(i);
            y += event.getY(i);
        }

        x = x / pointerCount;
        y = y / pointerCount;

        if (mLastPointerCount != pointerCount) {
            isCanDrag = false;
            mLastX = x;
            mLastY = y;
        }

        mLastPointerCount = pointerCount;

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dx = x - mLastX;
                float dy = y - mLastY;

                if (!isCanDrag) {
                    isCanDrag = isMoveAction(dx, dy);
                }

                if (isCanDrag) {
                    RectF rectF = getMatritRectF();
                    if (getDrawable() != null) {

                        isCheckLeftAndRight = isCheckTopAndBottom = true;

                        if (rectF.width() < getWidth()){
                            isCheckLeftAndRight = false;
                            dx =0;
                        }

                        if (rectF.height() <getHeight()){
                            isCheckTopAndBottom = false;
                            dy = 0;
                        }

                        mMatrix.postTranslate(dx,dy);
                        checkBorderWhenTranslate();
                        setImageMatrix(mMatrix);
                    }
                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isCanDrag = false;
                mLastPointerCount = 0;

                break;
            default:
                break;
        }

        return true;
    }

    private void checkBorderWhenTranslate() {
        RectF rectF = getMatritRectF();
        float deltaX = 0;
        float deltaY = 0;

        int width = getWidth();
        int height = getHeight();

        if (rectF.top > 0 && isCheckTopAndBottom) {
            deltaY = -rectF.top;
        }
        if (rectF.bottom < height && isCheckTopAndBottom) {
            deltaY = height - rectF.bottom;
        }
///////////
        if (rectF.left > 0 && isCheckLeftAndRight) {
            deltaX =  - rectF.left;
        }

        if (rectF.right < width && isCheckLeftAndRight) {
            deltaX = width - rectF.right;
        }

        mMatrix.postTranslate(deltaX,deltaY);

    }

    private RectF getMatritRectF() {
        Matrix matrix = mMatrix;
        RectF rectF = new RectF();
        Drawable drawable = getDrawable();
        if ((drawable!=null)) {
            rectF.set(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
            matrix.mapRect(rectF);
        }
        Log.d(TAG, "getMatritRectF: rectF=" + rectF);
        return rectF;
    }

    private boolean isMoveAction(float dx, float dy) {
        return Math.sqrt(dx * dx + dy * dy) > mTouchSlop;
    }
}
