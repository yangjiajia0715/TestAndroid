package com.testandroid.yang.adapter.decoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.testandroid.yang.R;
import com.testandroid.yang.util.Utility;

/**
 * MyItemDecoration
 * Created by yangjiajia on 2017/8/2.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "MyItemDecoration";
    private TextPaint textPaint;
    private Paint paint;
    private int topGap;
    private int alignBottom;
    private Paint.FontMetrics fontMetrics;

    public MyItemDecoration(Context context) {
        //设置悬浮栏的画笔
        paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        //设置悬浮栏中文本的画笔
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
//        textPaint.setTextSize(Utility.dp2px(12));
        textPaint.setTextSize(Utility.dp2px(18));
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setColor(Color.WHITE);
        fontMetrics = new Paint.FontMetrics();
        //决定悬浮栏的高度等
        topGap = Utility.dp2px(10);
//        topGap=resources.getDimensionPixelSize(R.dimen.sectioned_top);
        //决定文本的显示位置等
        alignBottom = 10;
//        alignBottom = resources.getDimensionPixelSize(R.dimen.sectioned_alignBottom);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        Log.d(TAG, "onDraw: getChildCount=" + parent.getChildCount());

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);

//            String groupId = callback.getGroupId(position);
//            if (groupId.equals("-1")) return;
//            String textLine = callback.getGroupFirstLine(position).toUpperCase();
            String textLine = "分类" + position;
            if (position == 0 || (position % 3 == 0)) {
//                Rect clipBounds = c.getClipBounds();
//                Log.d(TAG, "onDraw: clipBounds=" + clipBounds);//(0, 0 - 1080, 1845)
                Log.d(TAG, "onDraw: position=" + position + " getTop=" + view.getTop());
//                c.drawColor(Color.BLUE);
                float top = view.getTop() - topGap;
                float bottom = view.getTop();
//                //绘制悬浮栏
                c.drawRect(left, top - topGap, right, bottom, paint);
//                //绘制文本
                c.drawText(textLine, left, bottom, textPaint);
            }
//            if (textLine == "") {
//                float top = view.getTop();
//                float bottom = view.getTop();
//                c.drawRect(left, top, right, bottom, paint);
//                return;
//            } else {
////                if (position == 0 || isFirstInGroup(position)) {
//                if (position == 0 || (position % 4 == 0)) {
//                    float top = view.getTop() - topGap;
//                    float bottom = view.getTop();
//                    //绘制悬浮栏
//                    c.drawRect(left, top - topGap, right, bottom, paint);
//                    //绘制文本
//                    c.drawText(textLine, left, bottom, textPaint);
//                }
//
//            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
//        Log.d(TAG, "getItemOffsets: density=" + metrics.density);//3.0
        Log.d(TAG, "getItemOffsets: outRect=" + outRect);//3.0
        int position = parent.getChildAdapterPosition(view);
        if (position % 3 == 0) {
            outRect.top = Utility.dp2px(30);
        } else {
            outRect.top = 0;
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
//        Log.d(TAG, "onDrawOver: ");
//        c.drawCircle(parent.getWidth() / 2, parent.getHeight() / 2, Utility.dp2px(30), paint);
        int itemCount = state.getItemCount();
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        float textY = 0;
        String textLine = "";
        View childAt = parent.getChildAt(0);
        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            if (position % 3 == 2) {
                textY = Math.min(Utility.dp2px(30), Math.max(0, childAt.getBottom()));
                break;
            }
        }

//        //第一个view所在的分组 组名
//        for (int i = 0; i < childCount; i++) {
//            View view = parent.getChildAt(i);
//            int position = parent.getChildAdapterPosition(view);
//
//            textLine = "哈哈" + (position % 4);
//
//            if (position % 3 == 0) {
//                textY = Math.max(topGap, view.getTop());
//            }
//        }
        Log.d(TAG, "onDrawOver: textY=" + textY);
        c.drawRect(left + Utility.dp2px(40), textY - Utility.dp2px(40), right, textY, paint);
        //left+2*alignbottom决定了文本往右偏移的多少
        c.drawText(textLine, left + 30 * alignBottom, textY, textPaint);
    }
}
