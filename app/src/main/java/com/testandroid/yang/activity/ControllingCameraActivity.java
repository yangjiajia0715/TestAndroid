package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ControllingCameraActivity
 * Created by yangjiajia on 2017/7/26.
 */

public class ControllingCameraActivity extends BaseActivity implements SurfaceHolder.Callback {
    private static final String TAG = "ControllingCameraActivi";

    @BindView(R.id.preview)
    TextView preview;
    @BindView(R.id.take_photo)
    TextView takePhoto;
    @BindView(R.id.surfaceview)
    SurfaceView surfaceview;
    private Camera mCamera;
    SurfaceHolder mHolder;
    List<Camera.Size> mSupportedPreviewSizes;

    public static void start(Context context) {
        Intent starter = new Intent(context, ControllingCameraActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controll_camera);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = surfaceview.getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        try {
            Camera camera = Camera.open();
            Log.d(TAG, "initView: camera " + camera);
            if (camera != null) {
                setCamera(camera);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "initView: e " + e.getMessage());
        }

    }

    @Override
    public void initData() {

    }

    private boolean safeCameraOpen(int id) {
        boolean qOpened = false;

        try {
            releaseCameraAndPreview();
            mCamera = Camera.open(id);
            qOpened = (mCamera != null);
        } catch (Exception e) {
            Log.e(getString(R.string.app_name), "failed to open Camera");
            e.printStackTrace();
        }

        return qOpened;
    }

    private void releaseCameraAndPreview() {
//        mPreview.setCamera(null);
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    @OnClick({R.id.take_photo, R.id.surfaceview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.take_photo:

                break;
            case R.id.surfaceview:
                break;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//        // Now that the size is known, set up the camera parameters and begin
//        // the preview.
//        Camera.Parameters parameters = mCamera.getParameters();
////        parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
//        parameters.setPreviewSize(width, height);
////        requestLayout();
//        mCamera.setParameters(parameters);
//
//        // Important: Call startPreview() to start updating the preview surface.
//        // Preview must be started before you can take a picture.
//        mCamera.startPreview();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Surface will be destroyed when we return, so stop the preview.
        if (mCamera != null) {
            // Call stopPreview() to stop updating the preview surface.
            mCamera.stopPreview();
        }

    }

    /**
     * When this function returns, mCamera will be null.
     */
    private void stopPreviewAndFreeCamera() {

        if (mCamera != null) {
            // Call stopPreview() to stop updating the preview surface.
            mCamera.stopPreview();

            // Important: Call release() to release the camera for use by other
            // applications. Applications should release the camera immediately
            // during onPause() and re-open() it during onResume()).
            mCamera.release();

            mCamera = null;
        }
    }

    public void setCamera(Camera camera) {
        if (mCamera == camera) {
            return;
        }

        stopPreviewAndFreeCamera();

        mCamera = camera;

        if (mCamera != null) {
            List<Camera.Size> localSizes = mCamera.getParameters().getSupportedPreviewSizes();
            mSupportedPreviewSizes = localSizes;
//            requestLayout();

            try {
                mCamera.setPreviewDisplay(mHolder);
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "setCamera: e=" + e.getMessage());
            }

            // Important: Call startPreview() to start updating the preview
            // surface. Preview must be started before you can take a picture.
            mCamera.startPreview();
        }
    }

}
