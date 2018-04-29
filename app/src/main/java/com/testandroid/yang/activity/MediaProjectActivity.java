package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangjiajia on 2018/4/27.
 */
public class MediaProjectActivity extends BaseActivity {

    private static final int REQ_CODE_MEDIA = 302;
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
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    private MediaProjection.Callback mCallback = new MediaProjection.Callback() {
        @Override
        public void onStop() {
            super.onStop();
            Log.d(TAG, "onStop: ");
        }
    };
    private MediaProjection mMediaProjection;

    public static void start(Context context) {
        Intent starter = new Intent(context, MediaProjectActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaprojection);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {

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
                break;
            case R.id.meida_3:
                break;
            case R.id.meida_4:
                break;
            case R.id.meida_5:
                break;
        }
    }

    private void media1() {
        if (Build.VERSION.SDK_INT >= 21) {
            Toast.makeText(this, "5.0以后才可录屏", Toast.LENGTH_SHORT).show();
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
        }
        super.onDestroy();
    }
}
