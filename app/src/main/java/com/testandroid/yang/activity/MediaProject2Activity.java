package com.testandroid.yang.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangjiajia on 2018/6/13.
 */
public class MediaProject2Activity extends BaseActivity {
    private static final String TAG = "MediaProject2Activity";
    private static final int REQ_CODE_CAPTURE = 111;

    @BindView(R.id.btn_0)
    Button mBtn0;
    @BindView(R.id.btn_1)
    Button mBtn1;
    @BindView(R.id.btn_2)
    Button mBtn2;
    @BindView(R.id.btn_3)
    Button mBtn3;
    @BindView(R.id.btn_4)
    Button mBtn4;
    private MediaProjectionManager mMediaProjectionManager;
    private MediaProjection mMediaProjection;

    public static void start(Context context) {
        Intent starter = new Intent(context, MediaProject2Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaprojection_2);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
//        getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//        getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);

        String callingPackage = getCallingPackage();
        ComponentName callingActivity = getCallingActivity();
        Log.d(TAG, "initView: callingPackage=" + callingPackage);
        Log.d(TAG, "initView: callingActivity=" + callingActivity);

        if (Build.VERSION.SDK_INT >= 21) {
            mMediaProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
            Intent captureIntent = mMediaProjectionManager.createScreenCaptureIntent();
//            startActivity(captureIntent);
            startActivityForResult(captureIntent,REQ_CODE_CAPTURE);
        }
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
                break;
            case R.id.btn_1:
                break;
            case R.id.btn_2:
                break;
            case R.id.btn_3:
                break;
            case R.id.btn_4:
                Toast.makeText(this, "mMediaProjection=" + mMediaProjection, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK != resultCode) {
            return;
        }

        switch (requestCode) {
            case REQ_CODE_CAPTURE:
                if (Build.VERSION.SDK_INT >= 21) {
                    Log.d(TAG, "onActivityResult: data=" + data);
                    mMediaProjection = mMediaProjectionManager.getMediaProjection(resultCode, data);
                }
                break;
        }
    }
}
