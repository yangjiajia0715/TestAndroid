package com.testandroid.yang.activity;

import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.testandroid.yang.R;
import com.testandroid.yang.service.RemoteControlReceiver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 多媒体相关
 * Created by yangjiajia on 2017/7/26.
 * file:///D:/sdk/docs/training/building-multimedia.html
 */

public class MutilMediaActivity extends ListActivity {
    private static final String TAG = "MutilMediaActivity";
    private static final int REQUST_CODE_CAMERA_THUMNAIL = 618;
    private static final int REQUST_CODE_CAMERA_BIG_IMAGE = 600;
    private static final int REQUEST_VIDEO_CAPTURE = 601;
    String mCurrentPhotoPath;

    private BroadcastReceiver receiverNoisy = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "onReceive: action=" + action);
            Log.d(TAG, "onReceive: intent=" + intent);
            if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
                KeyEvent event = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
                Log.d(TAG, "onReceive: event=" + event);
                switch (event.getKeyCode()) {
                    case KeyEvent.KEYCODE_MEDIA_PLAY:

                        break;
                    default:
                        break;
                }
                if (KeyEvent.KEYCODE_MEDIA_PLAY == event.getKeyCode()) {
                    // Handle key press.
                }
            } else {
                if (am.isBluetoothA2dpOn()) {
                    Log.d(TAG, "onReceive: isBluetoothA2dpOn");
                    // Adjust output for Bluetooth.
                } else if (am.isSpeakerphoneOn()) {
                    Log.d(TAG, "onReceive: isSpeakerphoneOn");
                    // Adjust output for Speakerphone.
                } else if (am.isWiredHeadsetOn()) {
                    Log.d(TAG, "onReceive: isWiredHeadsetOn");
                    // Adjust output for headsets
                } else {
                    Log.d(TAG, "onReceive: else");
                    // If audio plays and noone can hear it, is it still playing?
                }
            }
        }
    };
    private AudioManager am;

    public static void start(Context context) {
        Intent starter = new Intent(context, MutilMediaActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        List<String> dates = new ArrayList<>();
        dates.add("拍照 获取缩略图");
        dates.add("拍照 获取大图");
        dates.add("添加到相册");//2
        dates.add("录像");//3
        dates.add("Controlling the Camera");//4
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dates);
        setListAdapter(adapter);
//        AudioManager.STREAM_MUSIC

//        audioManage();

        PackageManager packageManager = getPackageManager();
        boolean feature = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA);
        Log.d(TAG, "onCreate: feature = " + feature);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                ComponentName componentName = intent.resolveActivity(getPackageManager());
                Log.d(TAG, "onListItemClick: componentName=" + componentName);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUST_CODE_CAMERA_THUMNAIL);
                }
                break;
            case 1:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    try {
                        File photoFile = createImageFile();
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));

                        startActivityForResult(intent, REQUST_CODE_CAMERA_BIG_IMAGE);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        // Error occurred while creating the File
                        Toast.makeText(this, R.string.something_wrong, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, R.string.no_app_open_it, Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (TextUtils.isEmpty(mCurrentPhotoPath)) {

                } else {
                    galleryAddPic();
                }
                break;
            case 3://录像 ok
                intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_VIDEO_CAPTURE);
                }
                break;
            case 4://Controlling the Camera
                ControllingCameraActivity.start(this);
                break;
            case 5:
                break;
        }
    }

    private void audioManage() {
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
//        setVolumeControlStream(AudioManager.USE_DEFAULT_STREAM_TYPE);
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        AudioManager.OnAudioFocusChangeListener focusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                Log.d(TAG, "onAudioFocusChange: focusChange=" + focusChange);
//                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                    // Pause playback
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    // Resume playback
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
//                    am.unregisterMediaButtonEventReceiver(RemoteControlReceiver);
//                    am.abandonAudioFocus(focusChangeListener);
                    // Stop playback
                }
            }
        };

        //
        int result = am.requestAudioFocus(focusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

//        am.requestAudioFocus(focusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);

        Log.d(TAG, "onCreate: result=" + result);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)

        {
            RemoteControlReceiver receiver = new RemoteControlReceiver();
            Intent intent = new Intent();
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

//            audioManager.registerMediaButtonEventReceiver(pendingIntent);
//            ComponentName componentName = new ComponentName(getPackageName(), RemoteControlReceiver.class);
//            audioManager.registerMediaButtonEventReceiver(pendingIntent,componentName);
            // Start playback.
        }

        // Abandon audio focus when playback complete
//        am.abandonAudioFocus(focusChangeListener);

        IntentFilter intentFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        intentFilter.addAction(Intent.ACTION_MEDIA_BUTTON);
        intentFilter.addAction(AudioManager.ACTION_HEADSET_PLUG);

        registerReceiver(receiverNoisy, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        unregisterReceiver(receiverNoisy);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUST_CODE_CAMERA_THUMNAIL:
                    Uri data1 = data.getData();
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    Log.d(TAG, "onActivityResult: data1=" + data1);
                    Log.d(TAG, "onActivityResult: imageBitmap=" + imageBitmap);
                    break;
                case REQUST_CODE_CAMERA_BIG_IMAGE:
                    Log.d(TAG, "onActivityResult:data= " + data);
                    if (data != null) {
                        Uri bigImageUri = data.getData();
                        if (bigImageUri != null) {
                            String lastPathSegment = bigImageUri.getLastPathSegment();
                            Log.d(TAG, "onActivityResult:bigImageUri= " + bigImageUri);
                            Log.d(TAG, "onActivityResult:lastPathSegment= " + lastPathSegment);
                            Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, R.string.fail, Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case REQUEST_VIDEO_CAPTURE:
                    Log.d(TAG, "onActivityResult capture:data= " + data);
                    Uri videoUri = data.getData();
                    Log.d(TAG, "onActivityResult capture:videoUri= " + videoUri);

                    break;
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        Log.d(TAG, "createImageFile: storageDir =" + storageDir);
        Log.d(TAG, "createImageFile: storageDir exists=" + storageDir.exists());
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.d(TAG, "createImageFile: mCurrentPhotoPath=" + mCurrentPhotoPath);
        Log.d(TAG, "createImageFile: image=" + image);
        Log.d(TAG, "createImageFile: image exists=" + image.exists());
        return image;
    }

    /**
     * 相册？ 不显示？
     */
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
        Toast.makeText(this, "已发广播", Toast.LENGTH_SHORT).show();
    }

    private void setPic() {
        // Get the dimensions of the View
//        int targetW = mImageView.getWidth();
//        int targetH = mImageView.getHeight();
        int targetW = 3;
        int targetH = 3;

        BitmapFactory.Options options = new BitmapFactory.Options();
        BitmapFactory.decodeFile("", options);
//        options.outWidth;
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
//        mImageView.setImageBitmap(bitmap);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
