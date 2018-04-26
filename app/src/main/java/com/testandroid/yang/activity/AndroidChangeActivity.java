package com.testandroid.yang.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.media.RemoteControlClient;
import android.media.session.MediaSession;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.support.annotation.NonNull;
import android.support.v13.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.log.Log;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 行为变更
 * Created by yangjiajia on 2018/4/24.
 */

public class AndroidChangeActivity extends BaseActivity {

    @BindView(R.id.android_change1)
    TextView mAndroidChange1;
    @BindView(R.id.android_change2)
    TextView mAndroidChange2;
    @BindView(R.id.android_change3)
    TextView mAndroidChange3;
    @BindView(R.id.android_change4)
    TextView mAndroidChange4;
    @BindView(R.id.android_change5)
    TextView mAndroidChange5;

    public static void start(Context context) {
        Intent starter = new Intent(context, AndroidChangeActivity.class);
        context.startActivity(starter);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("AndroidChangeActivity--onReceive-------intent=" + intent.getAction()
                    + ",getData=" + intent.getData());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_change);
        ButterKnife.bind(this);

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(Camera.ACTION_NEW_PICTURE);
        registerReceiver(mReceiver, intentFilter);

        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.android_change1, R.id.android_change2, R.id.android_change3,
            R.id.android_change4, R.id.android_change5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.android_change1:
//              null
//                Intent intent = new Intent(Camera.ACTION_NEW_PICTURE);
//                ComponentName componentName = intent.resolveActivity(getPackageManager());
//                if (componentName != null) {
//                    startActivity(intent);
//                }
                storageManager();
                break;
            case R.id.android_change2:
                notification5();

//                new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                Notification.MediaStyle mediaStyle = new Notification.MediaStyle();
//                MediaStore.EXTRA_OUTPUT
                try {
//                    FileProvider.getUriForFile()
                    File.createTempFile("", "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.android_change3:
                permission6();
//                SecurityException 私有文件权限不应该扩大
//                try {
//                    FileOutputStream dd = openFileOutput("dd", MODE_WORLD_READABLE);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
                break;
            case R.id.android_change4:
//                DownloadManager.

                DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                String apk = "https://resource.cookids.com.cn/android/apk/tmall_v1.2.1_6.apk ";
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(apk));
                downloadManager.enqueue(request);
                break;
            case R.id.android_change5:
                Intent intent1 = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
                startActivity(intent1);
                break;
        }
    }

    /**
     * 6.0 运行时权限
     */
    private void permission6() {
        int selfPermission = ContextCompat.checkSelfPermission(this, "");

//        ContextCompat.
        if (Build.VERSION.SDK_INT >= 23) {
//            int permission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//            if (PackageManager.PERMISSION_GRANTED == permission) {
//                Toast.makeText(this, "PERMISSION_GRANTED" + selfPermission, Toast.LENGTH_SHORT).show();
//            } else if (PackageManager.PERMISSION_DENIED == permission) {
//                Toast.makeText(this, "PERMISSION_DENIED" + selfPermission, Toast.LENGTH_SHORT).show();
//            }

            String[] per = {Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CALL_PHONE};
            requestPermissions(per, 999);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("onRequestPermissionsResult--permissions=" + Arrays.toString(permissions)
                + ",grantResults=" + Arrays.toString(grantResults));
    }

    /**
     * 5.0通知变更
     */
    private void notification5() {
        RemoteControlClient remoteControlClient;
        MediaSession mediaSession;

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.icon_home)
                .setContentTitle("标题")
                .setContentText("通知内容")
                .build();


        ActivityCompat.requestPermissions(this, new String[]{}, 3);
//        NotificationManager nm = getSystemService();
//        nm.
    }

    private void storageManager() {
        if (Build.VERSION.SDK_INT >= 24) {
            StorageManager sm = getSystemService(StorageManager.class);
            StorageVolume volume = sm.getPrimaryStorageVolume();
            Intent intent = volume.createAccessIntent(Environment.DIRECTORY_PICTURES);
            startActivityForResult(intent, 3);
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

}
