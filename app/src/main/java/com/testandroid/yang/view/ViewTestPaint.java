package com.testandroid.yang.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.testandroid.yang.R;
import com.testandroid.yang.util.Utility;

import java.util.ArrayList;
import java.util.List;

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

    private List<Path> paths = new ArrayList<>();
    private final Canvas canvasBitmep;
    private final Bitmap bitmapNew;

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

        //------------------不中
        bitmapNew = Bitmap.createBitmap(Utility.dp2px(100), Utility.dp2px(100), Bitmap.Config.ARGB_8888);
        canvasBitmep = new Canvas(bitmapNew);
        Paint newPaint = new Paint(this.paint);
        newPaint.setColor(getResources().getColor(R.color.colorPrimary));
        Path path1 = new Path();
        int bitmapWidth = bitmapNew.getWidth();
        int bitmapHeight = bitmapNew.getHeight();
        path1.lineTo(bitmapWidth / 2, 0);
        path1.moveTo(bitmapWidth, bitmapHeight / 2);
        path1.moveTo(bitmapWidth / 2, bitmapHeight);
        path1.moveTo(0, bitmapHeight / 2);
        canvasBitmep.drawPath(path1, newPaint);
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

        for (Path path1 : paths) {
            canvas.drawPath(path1, paint);
        }

        canvas.drawBitmap(bitmapNew, height - bitmapNew.getWidth(), height - bitmapNew.getHeight(), paint);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown: keyCode=" + keyCode);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyUp: keycode=" + keyCode);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        Log.d(TAG, "onTrackballEvent: event=" + event);
        return super.onTrackballEvent(event);
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
                paths.add(path);
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
                path = new Path();
                Log.d(TAG, "onTouchEvent: ACTION_UP event=x" + x + " ,y=" + y);
                return true;
            default:
                return false;
        }

    }


    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Log.d(TAG, "onFocusChanged: gainFocus=" + gainFocus);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.d(TAG, "onWindowFocusChanged: hasWindowFocus=" + hasWindowFocus);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow: ");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow: ");
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        Log.d(TAG, "onWindowVisibilityChanged: visibility=" + visibility);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "onFinishInflate: ");
    }
}
