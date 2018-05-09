package com.testandroid.yang.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.testandroid.yang.R;
import com.testandroid.yang.service.ScreenCaptureService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangjiajia on 2018/5/9.
 */
public class ServiceActivity extends BaseActivity {
    private static final String TAG = "ServiceActivity";

    @BindView(R.id.btn_service_1)
    Button mBtnService1;
    @BindView(R.id.btn_service_2)
    Button mBtnService2;
    @BindView(R.id.btn_service_3)
    Button mBtnService3;
    @BindView(R.id.btn_service_4)
    Button mBtnService4;
    @BindView(R.id.btn_service_5)
    Button mBtnService5;
    @BindView(R.id.btn_service_6)
    Button mBtnService6;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: name=" + name);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: name=" + name);
        }
    };

    public static void start(Context context) {
        Intent starter = new Intent(context, ServiceActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_service_1, R.id.btn_service_2, R.id.btn_service_3, R.id.btn_service_4
            , R.id.btn_service_5, R.id.btn_service_6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_service_1:
                startScreenCaptureService();
                break;
            case R.id.btn_service_2:
                startScreenCaptureService();
                break;
            case R.id.btn_service_3:
                break;
            case R.id.btn_service_4:
                bindScreenCaptureService();
                break;
            case R.id.btn_service_5:
                unBindScreenCaptureService();
                break;
            case R.id.btn_service_6:
                stopScreenCaptureService();
                break;
        }
    }

    private void startScreenCaptureService() {
        Intent intent = new Intent(this, ScreenCaptureService.class);
        startService(intent);
    }

    private void stopScreenCaptureService() {
        Intent intent = new Intent(this, ScreenCaptureService.class);
        stopService(intent);
    }

    private void bindScreenCaptureService() {
        Intent intent = new Intent(this, ScreenCaptureService.class);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    private void unBindScreenCaptureService() {
        unbindService(mServiceConnection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
