package com.testandroid.yang.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.testandroid.yang.R;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 权限
 * Created by yangjiajia on 2017/10/31.
 */
//@RuntimePermissions
public class PermissiondispatcherActivity extends BaseActivity {
    private static final String TAG = "PermissiondispatcherAct";
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 105;
    private static final int REQUEST_CAMERA_PERMISSION = 106;
    @BindView(R.id.permission_button_01)
    Button permissionButton01;
    @BindView(R.id.permission_button_02)
    Button permissionButton02;
    @BindView(R.id.permission_button_03)
    Button permissionButton03;
    @BindView(R.id.permission_button_04)
    Button permissionButton04;
    @BindView(R.id.permission_button_05)
    Button permissionButton05;

    public static void start(Context context) {
        Intent starter = new Intent(context, PermissiondispatcherActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_dispatcher);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void showContacts() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                        PERMISSIONS_REQUEST_READ_CONTACTS);
            } else {
//            doShowContacts();
            }
        }
    }

    //    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
////        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS
////                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////            showContacts();
////        }
//    }


    @Override
    public void initView() {
        Log.d(TAG, "initView: " + isAppliedPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE));
    }

    @Override
    public void initData() {

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//    }

    //    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        PermissionsDispatcherActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
//    }

//    //给用户解释要请求什么权限，为什么需要此权限
//    @OnShowRationale({Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    public void showRationale(final PermissionRequest request) {
//        new AlertDialog.Builder(this)
//                .setMessage("使用此功能需要WRITE_EXTERNAL_STORAGE和RECORD_AUDIO权限，下一步将继续请求权限")
//                .setPositiveButton("下一步", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        request.proceed();//继续执行请求
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        request.cancel();//取消执行请求
//                    }
//                })
//                .show();
//    }

    @OnClick({R.id.permission_button_01, R.id.permission_button_02, R.id.permission_button_03,
            R.id.permission_button_04, R.id.permission_button_05})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.permission_button_01:
                getSingle();
                break;
            case R.id.permission_button_02:
                getMulti();
                break;
            case R.id.permission_button_03:

                break;
            case R.id.permission_button_04:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 2);
                }
                break;
            case R.id.permission_button_05:
                Log.d(TAG, "onViewClicked: ----Build.VERSION = " + Build.VERSION.SDK_INT);
                Log.d(TAG, "onViewClicked: ----permission = " + ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA));
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    pickImageByCapture();
                } else {
                    requestPermission(Manifest.permission.CAMERA, "拍照需要个权限", REQUEST_CAMERA_PERMISSION);
                }
//                } else {
//                    pickImageByCapture();
//                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length == 0) {
            return;
        }
        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageByCapture();
                } else {
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
                break;
            default:
                break;
        }
    }

    private void requestPermission(final String permission, String rationale, final int requestCode) {
        Log.d(TAG, "requestPermission: " + ActivityCompat.shouldShowRequestPermissionRationale(this, permission));
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.title_activity_about)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(PermissiondispatcherActivity.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.text_canncel, null)
                    .create()
                    .show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    private void pickImageByCapture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String time = DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME);
        File file = new File(getExternalCacheDir(), time + ".jpg");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "pickImageByCapture: " + file.getPath());
        Log.d(TAG, "pickImageByCapture: " + file.exists());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, 11);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        Log.d(TAG, "onRequestPermissionsResult: requestCode=" + requestCode);
//        Log.d(TAG, "onRequestPermissionsResult: permissions=" + permissions);
//        Log.d(TAG, "onRequestPermissionsResult: grantResults=" + grantResults);
//    }

    //获取单个权限
//    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//    @NeedsPermission(Manifest.permission.RECORD_AUDIO)
    public void getSingle() {
        Toast.makeText(this, "getSingle", Toast.LENGTH_SHORT).show();
    }

    //获取多个权限
//    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO})
    public void getMulti() {
        Toast.makeText(this, "getMulti", Toast.LENGTH_SHORT).show();
    }

    // 查看权限是否已申请
    private static boolean isAppliedPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) ==
                PackageManager.PERMISSION_GRANTED;
//        return context.checkSelfPermission(permission) ==
//                PackageManager.PERMISSION_GRANTED;
    }
}
