package com.testandroid.yang.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * test画笔
 * Created by yangjiajia on 2017/6/13 0013.
 */

public class ViewTestPaint extends View {
    private static final String TAG = "ViewTestPaint";
    private Paint paint;
    private int width;
    private int height;
    private final Rect rect;


    public ViewTestPaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        rect = new Rect(0, 0, 100, 100);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged: w=" + w);
        Log.d(TAG, "onSizeChanged: h=" + h);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);

        canvas.drawCircle(width / 2, height / 2, 100, paint);

        paint.setColor(Color.BLUE);
        canvas.drawRect(rect, paint);

        canvas.save();

        canvas.translate(0, 200);

        paint.setColor(Color.GREEN);
        canvas.drawRect(rect, paint);

        canvas.restore();

        rect.set(100, 100, 200, 200);
        paint.setColor(Color.CYAN);
        canvas.drawRect(rect, paint);
    }
}
