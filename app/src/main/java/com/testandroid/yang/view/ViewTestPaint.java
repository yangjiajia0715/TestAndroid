package com.testandroid.yang.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
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
    private Path path;

    private float oldX;
    private float oldY;

    public ViewTestPaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(10);
        paint.setDither(true);//防抖动
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        rect = new Rect(0, 0, 100, 100);

        path = new Path();
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
        paint.setStyle(Paint.Style.FILL);

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

        paint.setStrokeWidth(10);
        paint.setColor(Color.parseColor("#fea73d"));

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        Log.d(TAG, "onTouchEvent: x=" + x + " ,y=" + y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.reset();
                path.moveTo(x, y);
                oldX = x;
                oldY = y;
                invalidate();
                Log.d(TAG, "onTouchEvent: ACTION_DOWN event=x" + x + " ,y=" + y);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.quadTo(oldX, oldY, (x + oldX) / 2, (y + oldY) / 2);
//                Math.sqrt()
                oldX = x;
                oldY = y;
                invalidate();
                Log.d(TAG, "onTouchEvent: ACTION_MOVE event=x" + x + " ,y=" + y);
                return true;
            case MotionEvent.ACTION_UP:
                path.lineTo(x, y);
                Log.d(TAG, "onTouchEvent: ACTION_UP event=x" + x + " ,y=" + y);
                return true;
            default:
                return false;
        }

    }
}
