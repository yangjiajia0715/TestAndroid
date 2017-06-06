package com.testandroid.yang.activity;

import android.content.ComponentName;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.UserDictionary;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.MyAsynTask;
import com.testandroid.yang.R;
import com.testandroid.yang.common.User;
import com.testandroid.yang.db.UserContract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 数据库交互   参考文档Save Data
 * Created by yangjiajia on 2017/5/18.
 */

public class SaveToDataBaseActivity extends BaseActivity {

    private static final String TAG = "SaveToDataBaseActivity";
    @BindView(R.id.save_to_db0)
    TextView saveToDb0;
    @BindView(R.id.save_to_db1)
    TextView saveToDb1;
    @BindView(R.id.save_to_db2)
    TextView saveToDb2;
    @BindView(R.id.save_to_db3)
    TextView saveToDb3;
    @BindView(R.id.save_to_db4)
    TextView saveToDb4;
    @BindView(R.id.save_to_db5)
    TextView saveToDb5;
    @BindView(R.id.save_to_db6)
    TextView saveToDb6;
    @BindView(R.id.save_to_db7)
    TextView saveToDb7;
    private int appId = 1;
    private Random random;

    public static void start(Context context) {
        Intent starter = new Intent(context, SaveToDataBaseActivity.class);
//        starter.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        starter.setDataAndType(,"text/")
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_to_db);
        ButterKnife.bind(this);
        random = new Random();
        initView();
        initData();

//        Service service = null;
//        service.startForeground(1,null);
//        service.stopForeground(true);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });

        saveToDb2.post(new Runnable() {
            @Override
            public void run() {
            }
        });

        MyAsynTask myAsynTask = new MyAsynTask();
        myAsynTask.cancel(true);

        SharedPreferences sharedPreferences = getSharedPreferences("hh", 0);

        ComponentName componentName = getComponentName();
        Log.d(TAG, "onCreate: componentName=" + componentName);
        String className = componentName.getClassName();
        Log.d(TAG, "onCreate: className=" + className);

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);

        preferences.edit().putInt(className, 11111).apply();

        testFileInputSteam();
    }

    /**
     * {@link SaveToDataBaseActivity#extralDirectoryPictures()}
     */
    private void testFileInputSteam() {
        try {
            //内部存储
            FileOutputStream fileOutput = openFileOutput("FileOutput", Context.MODE_PRIVATE);
            fileOutput.write("内部存储 内容内部存储 内容内部存储 内容内部存储 内容".getBytes());
            fileOutput.close();

//            /storage/emulated/0/Android/data/com.testandroid.yang/files
            File externalFilesDir = getExternalFilesDir(null);//外部私有目录 data/android/报名 卸载时自动删除
            File file = new File(externalFilesDir, "外部存储文件.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            FileInputStream fileInput = openFileInput("FileOutput");

            byte[] d = new byte[1024];
            int len;
            while ((len = fileInput.read(d)) != -1) {
                fileOutputStream.write(d, 0, len);
            }

            fileOutputStream.close();
            fileInput.close();

            Log.d(TAG, "onCreate: externalFilesDir=" + externalFilesDir);

            //内部目录
            File filesDir = getFilesDir();//  /data/data/com.testandroid.yang/files
            File cacheDir = getCacheDir();//  /data/data/com.testandroid.yang/cache

            Log.d(TAG, "onCreate: 内部目录 filesDir=" + filesDir);
            Log.d(TAG, "onCreate: 内部目录 cacheDir=" + cacheDir);//系统不足会删除，最好自己删除

            File dir = getDir("内部自定义目录", Context.MODE_PRIVATE);//  /data/data/com.testandroid.yang/app_自定义目录
            File file0 = new File(dir, "自定义文件0");
//            file0.setReadable(true);
            file0.createNewFile();
            File file1 = new File(dir, "自定义文件1");
            file1.createNewFile();

            Log.d(TAG, "onCreate: dir=" + dir);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 外部文件（图片）目录，设置媒体可见
     */
    private void extralDirectoryPictures() {

//        Cursor cursor = getContentResolver().query(UserContract.CONTENT_URI, null, null, null, null);

//        File.createTempFile()
//        try {
//            File.createTempFile("",null,getCacheDir());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        Log.d(TAG, "extralDirectoryPictures: externalStorageDirectory=" + externalStorageDirectory);
        Log.d(TAG, "extralDirectoryPictures: externalStoragePublicDirectory=" + externalStoragePublicDirectory);

        //私有，卸载时删除,通常对Media不可见
        //但是从4.4其他应用也可以访问（有读写权限）
        // Starting in KITKAT, no permissions are required to read or write to the returned path;
        // it's always accessible to the calling app.
        // This only applies to paths generated for package name of the calling application.
        // To access paths belonging to other packages, WRITE_EXTERNAL_STORAGE and/or READ_EXTERNAL_STORAGE are required.
//         external private directory.  外部私有目录
        File externalFilesDirPic = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        /storage/emulated/0/Android/data/com.testandroid.yang/files/Pictures
        Log.d(TAG, "testFileInputSteam: externalFilesDirPic=" + externalFilesDirPic);

        File directoryPictureLiushishi = new File(externalFilesDirPic, "liushishi.jpg");
        InputStream inputStream = getResources().openRawResource(R.raw.liushishi);
        try {
            FileOutputStream fileOutputStream1 = new FileOutputStream(directoryPictureLiushishi);
            int len2;
            byte[] bbb = new byte[1024];
            while ((len2 = inputStream.read(bbb)) != -1) {
                fileOutputStream1.write(bbb, 0, len2);
                Log.d(TAG, "testFileInputSteam: len2=" + len2);
            }
            inputStream.close();
            fileOutputStream1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Tell the media scanner about the new file so that it is
        // immediately available to the user.
        MediaScannerConnection.scanFile(this, new String[]{directoryPictureLiushishi.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                        SaveToDataBaseActivity.this.sendBroadcast(new Intent(android.hardware.Camera.ACTION_NEW_PICTURE, uri));
                        SaveToDataBaseActivity.this.sendBroadcast(new Intent("com.android.camera.NEW_PICTURE", uri));
                        SaveToDataBaseActivity.this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                                uri));
                    }
                });

//        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        Uri uri = Uri.fromFile(directoryPictureLiushishi);
//        intent.setData(uri);
//        sendBroadcast(intent);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.save_to_db0, R.id.save_to_db1, R.id.save_to_db2, R.id.save_to_db3, R.id.save_to_db4, R.id.save_to_db5, R.id.save_to_db6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.save_to_db0:
                testSqliteQueryBuilder();
                break;
            case R.id.save_to_db1:
                ContentResolver contentResolver = getContentResolver();
                Log.d(TAG, "onViewClicked: contentResolver=" + contentResolver);

//                contentResolver.fi
//                contentResolver.insert()
                Cursor cursor = contentResolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);
                int count = cursor.getCount();
                Log.d(TAG, "onViewClicked: count=" + count);
                Uri uri = UserDictionary.Words.CONTENT_URI;

                Log.d(TAG, "onViewClicked: uri=" + uri);
                Uri withAppendedId = ContentUris.withAppendedId(uri, 2);
                Log.d(TAG, "onViewClicked: withAppendedId=" + withAppendedId);
                long parseId = ContentUris.parseId(withAppendedId);
                Log.d(TAG, "onViewClicked: parseId=" + parseId);

                int columnIndex = cursor.getColumnIndex(UserDictionary.Words.WORD);
                String string = cursor.getString(columnIndex);

                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        String string1 = cursor.getString(columnIndex);

                        int type = cursor.getType(columnIndex);
                        if (Cursor.FIELD_TYPE_BLOB == type) {

                        }
                    }
                }
                break;
            case R.id.save_to_db2:

                break;
            case R.id.save_to_db3:
                saveWord();
                break;
            case R.id.save_to_db4:
                ContentProviderOperation contentProviderOperation;
                ArrayList<ContentProviderOperation> list = new ArrayList<>();
                try {
                    getContentResolver().applyBatch("", list);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (OperationApplicationException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.save_to_db5:
//                int nextInt = random.nextInt(1000);
//                Log.d(TAG, "onViewClicked: nextint=" + nextInt);
//                final User user = new User();
//                user.name = "用户" + nextInt;
//                user.age = nextInt;
//                user.phoneNumber = String.valueOf(nextInt);
//                Log.d(TAG, "onViewClicked: user=" + user);
//                UserContract.Users.addUser(getApplicationContext(), user);

                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        int count = 0;
                        boolean continueM = true;
                        while (continueM) {
                            try {
                                Thread.sleep(2000);
                                int nextInt = random.nextInt(1000);
                                Log.d(TAG, "onViewClicked: nextint=" + nextInt);
                                final User user = new User();
                                user.name = "用户" + nextInt;
                                user.age = nextInt;
                                user.phoneNumber = String.valueOf(nextInt);
                                Log.d(TAG, "onViewClicked: user=" + user);
                                UserContract.Users.addUser(getApplicationContext(), user);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            count++;
                            if (count > 1) {
                                continueM = false;
                            }
                        }
                    }
                }.start();

                DisplayUserInfoActivity.start(this);
                break;
            case R.id.save_to_db6:
                extralDirectoryPictures();
                break;
        }
    }

    private void testSqliteQueryBuilder() {

    }

    private void saveWord() {
        appId++;

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserDictionary.Words.APP_ID, appId);
        contentValues.put(UserDictionary.Words.WORD, "张三" + appId);

        contentValues.putNull(UserDictionary.Words.CONTENT_TYPE);
//        contentValues.putNull("");
        Uri uri = UserDictionary.Words.CONTENT_URI;

        Uri insert = getContentResolver().insert(uri, contentValues);

//        getContentResolver().update()
        ContactsContract.Data data;
        Log.d(TAG, "saveWord: insert=" + insert);


    }
}
