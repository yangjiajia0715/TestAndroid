package com.testandroid.yang.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v13.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.testandroid.yang.R;
import com.testandroid.yang.log.Log;
import com.testandroid.yang.util.GlUtil;

import java.lang.ref.WeakReference;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangjiajia on 2018/4/27.
 * @see OpenGLActivity
 */
public class MediaProjectActivity extends BaseActivity implements SurfaceTexture.OnFrameAvailableListener {
    @BindView(R.id.surfaceview)
    SurfaceView mSurfaceview;
    private int mWidth = 1280;  // mWidth
    private int mHeight = 720;  // mHeight

    private static final int REQ_CODE_MEDIA = 302;
    private static final int PERMISSION_REQ_ID_RECORD_AUDIO = 22;
    private static final String TAG = "MediaProjectActivity";

    @BindView(R.id.meida_1)
    Button mMeida1;
    @BindView(R.id.meida_2)
    Button mMeida2;
    @BindView(R.id.meida_3)
    Button mMeida3;
    @BindView(R.id.meida_4)
    Button mMeida4;
    @BindView(R.id.meida_5)
    Button mMeida5;
    private MediaProjectionManager mProjectionManager;
    private Handler mMainHandler = new MainHandler(this);

    private MediaProjection.Callback mCallback = new MediaProjection.Callback() {
        @Override
        public void onStop() {
            super.onStop();
            Log.d("MediaProjection.Callback--onStop: ");
        }
    };
    private VirtualDisplay mVirtualDisplay;

    private static class MainHandler extends Handler {
        private WeakReference<MediaProjectActivity> mWeakcapture;

        public MainHandler(MediaProjectActivity screenCapture) {
            super();
            this.mWeakcapture = new WeakReference<>(screenCapture);
        }

        @Override
        public void handleMessage(Message msg) {
            MediaProjectActivity mediaProjectActivity = mWeakcapture.get();
            if (mediaProjectActivity != null) {
                Log.d("MainHandler--handleMessage--msg=" + msg);
            }
        }
    }

    private MediaProjection mMediaProjection;
    private SurfaceTexture mSurfaceTexture;
    private Surface mSurface;
    // fill extra frame
    private Runnable mFillFrameRunnable = new Runnable() {
        @Override
        public void run() {
//            if (mState.get() == SCREEN_STATE_CAPTURING) {
//                mGLRender.requestRender();
            onDrawFrame();
            mMainHandler.postDelayed(mFillFrameRunnable, 1000);
//            }
        }
    };

    private void onDrawFrame() {
        long pts = System.nanoTime() / 1000 / 1000;
        try {
            mSurfaceTexture.updateTexImage();
        } catch (Exception e) {
            Log.d("onDrawFrame---updateTexImage failed, ignore");
            return;
        }

        float[] texMatrix = new float[16];
        mSurfaceTexture.getTransformMatrix(texMatrix);
        Log.d("onDrawFrame---texMatrix--" + Arrays.toString(texMatrix));
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MediaProjectActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    PERMISSION_REQ_ID_RECORD_AUDIO);
        }
//        2018-5-6 23:33:28 save appinfo 获取size=0

        setContentView(R.layout.activity_mediaprojection);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    @Override
    public void initView() {
//        mSurface = mSurfaceview.getHolder().getSurface();
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.meida_1, R.id.meida_2, R.id.meida_3, R.id.meida_4, R.id.meida_5})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.meida_1:
                media1();
                break;
            case R.id.meida_2:
                if (Build.VERSION.SDK_INT >= 21 && mMediaProjection != null) {
                    mSurfaceTexture = new SurfaceTexture(GlUtil.createOESTextureObject());
                    mSurfaceTexture.setDefaultBufferSize(mWidth, mHeight);
                    mSurfaceTexture.setOnFrameAvailableListener(this);

                    mSurface = new Surface(mSurfaceTexture);

                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                    mVirtualDisplay = mMediaProjection.createVirtualDisplay("virtual", mWidth, mHeight,
                            displayMetrics.densityDpi, DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC,
                            mSurface, null, null);

                    //显示在surfaceView上，initView()获取
//                    mVirtualDisplay = mMediaProjection.createVirtualDisplay("Virtual"
//                            , displayMetrics.widthPixels, displayMetrics.heightPixels,
//                            displayMetrics.densityDpi, DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC,
//                            mSurface, null, null);
                } else {
                    Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.meida_3:
                if (Build.VERSION.SDK_INT >= 21) {
                    mMediaProjection.stop();
                }
                break;
            case R.id.meida_4:
                break;
            case R.id.meida_5:
                EmptyActivity.start(this);
                break;
        }
    }

    private void media1() {
        if (Build.VERSION.SDK_INT >= 21) {
//            Toast.makeText(this, "5.0以后才可录屏", Toast.LENGTH_SHORT).show();
            mProjectionManager = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);
            Intent captureIntent = mProjectionManager.createScreenCaptureIntent();
            startActivityForResult(captureIntent, REQ_CODE_MEDIA);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_MEDIA:
                if (Build.VERSION.SDK_INT >= 21) {
                    mMediaProjection = mProjectionManager.getMediaProjection(resultCode, data);
                    mMediaProjection.registerCallback(mCallback, null);
                    Log.d("获取MediaProjection 成功！");
                }
                break;
        }

    }

    @Override
    protected void onDestroy() {
        if (Build.VERSION.SDK_INT >= 21) {
            if (mMediaProjection != null) {
                mMediaProjection.unregisterCallback(mCallback);
            }

            if (mSurface != null) {
                mSurface.release();
            }

            if (mSurfaceTexture != null) {
                mSurfaceTexture.release();
            }

            if (mVirtualDisplay != null) {
                mVirtualDisplay.release();
            }

            if (mMediaProjection != null) {
                mMediaProjection.stop();
            }

            mVirtualDisplay = null;
            mMediaProjection = null;

            mMainHandler.removeCallbacks(mFillFrameRunnable);
        }
        super.onDestroy();
    }

    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        Log.d("onFrameAvailable--surfaceTexture--" + surfaceTexture);
        if (mMainHandler != null) {
            mMainHandler.removeCallbacks(mFillFrameRunnable);
            mMainHandler.postDelayed(mFillFrameRunnable, 1000);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQ_ID_RECORD_AUDIO:
                boolean grantR = true;
                for (int grantResult : grantResults) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        grantR = false;
                        break;
                    }
                }

                if (grantR) {
                    Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
