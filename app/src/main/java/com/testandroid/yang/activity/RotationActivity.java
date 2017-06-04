package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangjiajia on 2017/5/31.
 */

public class RotationActivity extends BaseActivity {
    private static final String TAG = "RotationActivity";
    @BindView(R.id.iamgeview)
    ImageView imageview;
    @BindView(R.id.rotation)
    TextView rotation;

    private Bitmap bitmap;
    private int time = 1;
    private Matrix matrix;

    public static void start(Context context) {
        Intent starter = new Intent(context, RotationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation_view);
        ButterKnife.bind(this);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.liushishi);
        matrix = new Matrix();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.rotation)
    public void onViewClicked() {

//        matrix.reset();
//        matrix.postRotate(90);
        matrix.setRotate(90 * time);
//        matrix.postRotate(90 * time);

        Log.d(TAG, "onViewClicked: " + 90 * time);

        Bitmap bitmap1 = Bitmap.createBitmap(this.bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        imageview.setImageBitmap(bitmap1);

        time++;

    }
}
