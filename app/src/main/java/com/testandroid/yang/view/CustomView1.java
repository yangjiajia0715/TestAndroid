package com.testandroid.yang.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import com.testandroid.yang.R;

/**
 * 自定义view
 * Created by yangjiajia on 2017/6/14.
 */

public class CustomView1 extends View {
    private static final String TAG = "CustomView1";
    private Paint paint;
    private final Bitmap originBitmap;
    private int width;
    private int height;
    private Matrix matrix;
    private final float density;


    public CustomView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(6);
        paint.setAntiAlias(true);
        paint.setDither(true);//防抖动

        originBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.liushishi);

        matrix = new Matrix();
//        matrix.setConcat()
//        matrix.

        ViewConfiguration configuration = ViewConfiguration.get(context);
        int scaledTouchSlop = configuration.getScaledTouchSlop();

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        density = metrics.density;
        Log.d(TAG, "CustomView1: density=" + density);
    }

    int dp2Px(int dp) {
        return (int) (dp * density);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        Log.d(TAG, "onSizeChanged: width=" + width + ",height=" + height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(originBitmap, 30, 30, null);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(60, 60, 200, 200, paint);
    }
}
