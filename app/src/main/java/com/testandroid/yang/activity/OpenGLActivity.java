package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.TrafficStats;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.testandroid.yang.R;
import com.testandroid.yang.log.Log;
import com.testandroid.yang.retrofit.ApiClient;

import java.io.IOException;
import java.sql.Timestamp;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by yangjiajia on 2018/5/11.
 *
 * @see MediaProjectActivity
 */
public class OpenGLActivity extends BaseActivity {
    private static final int REQ_CODE_MEDIA = 302;
    private static final String TAG = "OpenGLActivity";

    @BindView(R.id.btn_gl_1)
    Button mBtnGl1;
    @BindView(R.id.btn_gl_2)
    Button mBtnGl2;
    @BindView(R.id.btn_gl_3)
    Button mBtnGl3;
    @BindView(R.id.gl_surfaceview)
    GLSurfaceView mGlSurfaceview;

    GLSurfaceView.Renderer renderer = new GLSurfaceView.Renderer() {

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {

        }

        @Override
        public void onDrawFrame(GL10 gl) {

        }
    };
    private MediaProjectionManager mProjectionManager;
    private MediaProjection mMediaProjection;

    public static void start(Context context) {
        Intent starter = new Intent(context, OpenGLActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gl);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
//        media1();
    }

    @Override
    public void initData() {
        mGlSurfaceview.setRenderer(renderer);
    }

    private void media1() {
        if (Build.VERSION.SDK_INT >= 21) {
//            Toast.makeText(this, "5.0以后才可录屏", Toast.LENGTH_SHORT).show();
            mProjectionManager = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);
            Intent captureIntent = mProjectionManager.createScreenCaptureIntent();
            startActivityForResult(captureIntent, REQ_CODE_MEDIA);
        }
    }

    @OnClick({R.id.btn_gl_1, R.id.btn_gl_2, R.id.btn_gl_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_gl_1:
                WindowManager wm = (WindowManager) getApplicationContext()
                        .getSystemService(Context.WINDOW_SERVICE);
                int screenWidth = wm.getDefaultDisplay().getWidth();
                int screenHeight = wm.getDefaultDisplay().getHeight();

                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int densityDpi = metrics.densityDpi;
                int widthPixels = metrics.widthPixels;
                int heightPixels = metrics.heightPixels;
                //dev local 提交后 dev add
                break;
            case R.id.btn_gl_2:
                //dev
                TrafficStats trafficStats;
                TrafficStats.getTotalRxBytes();
                Timestamp timestamp;
                //
                //br 2018-5-21 12:45:20

                break;
            case R.id.btn_gl_3:
                //devLocal add
//                Intent intent = new Intent();
//                intent.setAction(ScreenCaptureBroadcastReceiver.ACTION_SCREEN_CAPTION);
//                sendBroadcast(intent);
                Toast.makeText(this, "btn_gl_3", Toast.LENGTH_SHORT).show();
                ApiClient.getInstance()
                        .listAll()
                        .subscribe(new Observer<ResponseBody>() {

                            @Override
                            public void onSubscribe(Disposable d) {
                                android.util.Log.d(TAG, "onSubscribe: ");
                            }

                            @Override
                            public void onNext(ResponseBody responseBody) {
                                try {
                                    android.util.Log.d(TAG, "onNext: " + responseBody.string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                android.util.Log.d(TAG, "onError: e=" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                android.util.Log.d(TAG, "onComplete: ");
                            }
                        });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_MEDIA:
                if (Build.VERSION.SDK_INT >= 21) {
                    mMediaProjection = mProjectionManager.getMediaProjection(resultCode, data);
//                    mMediaProjection.registerCallback(mCallback, null);
                    Log.d("获取MediaProjection 成功！");
                }
                break;
        }

    }

    @Override
    protected void onDestroy() {
        if (Build.VERSION.SDK_INT >= 21) {
            if (mMediaProjection != null) {
//                mMediaProjection.unregisterCallback(mCallback);
            }
//
//            if (mSurface != null) {
//                mSurface.release();
//            }
//
//            if (mSurfaceTexture != null) {
//                mSurfaceTexture.release();
//            }
//
//            if (mVirtualDisplay != null) {
//                mVirtualDisplay.release();
//            }

//            mVirtualDisplay = null;
            mMediaProjection = null;

//            mMainHandler.removeCallbacks(mFillFrameRunnable);
        }
        super.onDestroy();
    }
}
