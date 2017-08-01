package com.testandroid.yang.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.testandroid.yang.R;

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangjiajia on 2017/7/27.
 * file:///D:/sdk/docs/training/building-graphics.html
 */

public class GraphicsAndAnimationActivity extends BaseActivity {
    private static final String TAG = "GraphicsAndAnimationAct";

    @BindView(R.id.btn_0)
    TextView btn0;
    @BindView(R.id.btn_1)
    TextView btn1;
    @BindView(R.id.btn_2)
    TextView btn2;
    @BindView(R.id.btn_3)
    TextView btn3;
    @BindView(R.id.btn_4)
    TextView btn4;
    @BindView(R.id.btn_5)
    TextView btn5;
    @BindView(R.id.btn_6)
    TextView btn6;
    @BindView(R.id.btn_7)
    TextView btn7;
    @BindView(R.id.btn_8)
    TextView btn8;
    @BindView(R.id.btn_9)
    TextView btn9;
    @BindView(R.id.loading_spinner)
    ProgressBar mLoadingView;
    @BindView(R.id.mContentView)
    View mContentView;
    @BindView(R.id.liushishi)
    ImageView liushishi;

    public static void start(Context context) {
        Intent starter = new Intent(context, GraphicsAndAnimationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_graphics);
        ButterKnife.bind(this);
        initView();
        initData();

        mContentView.postDelayed(new Runnable() {
            @Override
            public void run() {
                crossfade();
            }
        }, 200);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6,
            R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.liushishi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(getResources(), R.drawable.liushishi, options);
                int outWidth = options.outWidth;
                int outHeight = options.outHeight;
                String outMimeType = options.outMimeType;
//                 outWidth=470,outHeight=300,outMimeType=image/jpeg
                Log.d(TAG, "onViewClicked: outWidth=" + outWidth + ",outHeight=" + outHeight
                        + ",outMimeType=" + outMimeType);
                String fromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(outMimeType);
//                 fromMimeType=jpeg
                Log.d(TAG, "onViewClicked: fromMimeType=" + fromMimeType);
                break;
            case R.id.btn_1:
                try {
                    Bitmap bitmapOrigin = BitmapFactory.decodeResource(getResources(), R.drawable.liushishi);
                    String fileSizeOri = Formatter.formatFileSize(this, bitmapOrigin.getByteCount());
                    Log.d(TAG, "onViewClicked: fileSizeOri=" + fileSizeOri);

                    Bitmap bitmap = decodeSampledBitmapFromResource(getResources(), R.drawable.liushishi, 100, 100);
                    String fileSize = Formatter.formatFileSize(this, bitmap.getByteCount());

                    Log.d(TAG, "onViewClicked: fileSize=" + fileSize);
                    String filePre = DateUtils.formatDateTime(this, System.currentTimeMillis(),
                            DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, openFileOutput(filePre + ".jpg", MODE_PRIVATE));

                    Bitmap bitmap1 = BitmapFactory.decodeStream(openFileInput(filePre + ".jpg"));
                    String fileSize1 = Formatter.formatFileSize(this, bitmap1.getByteCount());
                    Log.d(TAG, "onViewClicked: fileSize1=" + fileSize1);
                    Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_2:
                CrossfadeActivity.start(this);
                break;
            case R.id.btn_3:
                CardFlipActivity.start(this);
                break;
            case R.id.btn_4:
                ZoomActivity.start(this);
                break;
            case R.id.btn_5:
                ScreenSlideActivity.start(this);
                break;
            case R.id.btn_6://ObjectAnimator rotationY
                objectAnimator();
                break;
            case R.id.btn_7://
                LiushishiActivity.start(this);
//                liushishi.setTranslationY(-Utility.dp2px(100));
                break;
            case R.id.btn_8:
//                liushishi.animate()
//                        .translationX(Utility.dp2px(100))
//                        .translationY(Utility.dp2px(100))
//                        .setDuration(1000)
//                        .setListener(new AnimatorListenerAdapter() {
//                            @Override
//                            public void onAnimationEnd(Animator animation) {
//                                super.onAnimationEnd(animation);
//                            }
//                        });

//                liushishi.setTranslationX(Utility.dp2px(100));
                liushishi.animate()
                        .scaleX(0.5f)
                        .setDuration(3000);
                break;
            case R.id.btn_9:
                final Rect startBounds = new Rect();
                final Rect finalBounds = new Rect();
                final Point globalOffset = new Point();

                btn0.getGlobalVisibleRect(startBounds);
                findViewById(R.id.container).getGlobalVisibleRect(finalBounds, globalOffset);
                Log.d(TAG, "onViewClicked: startBounds=" + startBounds.toString());
                Log.d(TAG, "onViewClicked: finalBounds=" + finalBounds.toString());
                Log.d(TAG, "onViewClicked: globalOffset=" + globalOffset.toString());
                startBounds.offset(-globalOffset.x, -globalOffset.y);
                finalBounds.offset(-globalOffset.x, -globalOffset.y);
                Log.d(TAG, "onViewClicked: ==========================");
                Log.d(TAG, "onViewClicked: startBounds=" + startBounds.toString());
                Log.d(TAG, "onViewClicked: finalBounds=" + finalBounds.toString());
                Log.d(TAG, "onViewClicked: globalOffset=" + globalOffset.toString());
                int[] location = new int[2];
                btn0.getLocationOnScreen(location);
                Log.d(TAG, "onViewClicked: location=" + location[0] + "::" + location[1]);
                float startScale = 0.5f;
//                startScale = (float) startBounds.width() / finalBounds.width();
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(ObjectAnimator.ofFloat(btn0, View.X, startBounds.left, finalBounds.left))
                        .with(ObjectAnimator.ofFloat(btn0, View.Y, startBounds.top, finalBounds.top))
                        .with(ObjectAnimator.ofFloat(btn0, View.SCALE_X, startScale, 1f))
                        .with(ObjectAnimator.ofFloat(btn0, View.SCALE_Y, startScale, 1f));
                animatorSet.setDuration(3000);
                animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });
                animatorSet.start();

//                liushishi.setAlpha(0.2f);
//                liushishi.setAlpha(0.2f);
//                liushishi.setScaleX(0.5f);
                break;
            case R.id.liushishi:
                Toast.makeText(this, "刘诗诗", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void objectAnimator() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(liushishi, "rotationY", 0f, 60f);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(liushishi, "rotationY", 180f, 0f);
//        objectAnimator.setRepeatCount(1);
        objectAnimator.setDuration(3000);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();

    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        Log.d(TAG, "decodeSampledBitmapFromResource: inSampleSize=" + options.inSampleSize);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private void crossfade() {
        int mShortAnimationDuration = 1000;

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        mContentView.setAlpha(0f);
        mContentView.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.
        mContentView.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        mLoadingView.animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mLoadingView.setVisibility(View.GONE);
                    }
                });
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        mLoadingView.setVisibility(View.GONE);
//                    }
//                });
    }


}
