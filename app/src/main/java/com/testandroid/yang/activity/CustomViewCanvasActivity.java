package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.testandroid.yang.R;
import com.testandroid.yang.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangjiajia on 2017/6/30.
 */

public class CustomViewCanvasActivity extends BaseActivity {
    private static final String TAG = "CustomViewCanvasActivit";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_canvas)
    ImageView ivCanvas;
    private Bitmap bitmap1;

    public static void start(Context context) {
        Intent starter = new Intent(context, CustomViewCanvasActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        toolbar.setTitle("画布相关");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.liushishi);

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        //目的，canvas 上画bitmap，path, 放大缩小
        Path path = new Path();
        path.moveTo(0, bitmapHeight / 2);
        path.quadTo(bitmapWidth / 8, bitmapHeight / 4, bitmapWidth / 4, 0);
        path.quadTo(bitmapWidth / 2, 0, bitmapWidth / 2, bitmapHeight / 2);
        path.lineTo(bitmapWidth, bitmapHeight / 2);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(ContextCompat.getColor(this, R.color.app_color_normal));
        paint.setStrokeWidth(Utility.dp2px(10));
        paint.setDither(true);

        try {
            Log.d(TAG, "initData: isMutable " + bitmap.isMutable());
            if (bitmap.isMutable()) {
                bitmap1 = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            }
            Canvas canvas = new Canvas(bitmap1);

            canvas.drawPath(path, paint);

            ivCanvas.setImageBitmap(bitmap1);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "initData: e=" + e);
        }

        ImageView imageView = new ImageView(this);
        imageView.setAdjustViewBounds(true);

        GLSurfaceView glSurfaceView;

    }
}
