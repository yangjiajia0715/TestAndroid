package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.BuildConfig;
import com.testandroid.yang.R;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 分享数据
 * file:///D:/sdk/docs/training/sharing/index.html
 * Created by yangjiajia on 2017/7/21.
 * 配合{@link SelectFileActivity}
 */

public class ShareSimpleDateActivity extends BaseActivity {
    private static final String TAG = "ShareSimpleDateActivity";
    @BindView(R.id.share_date_0)
    TextView shareDate0;
    @BindView(R.id.share_date_1)
    TextView shareDate1;
    @BindView(R.id.share_date_3)
    TextView shareDate3;
    @BindView(R.id.share_date_4)
    TextView shareDate4;
    @BindView(R.id.share_date_5)
    TextView shareDate5;
    @BindView(R.id.share_date_6)
    TextView shareDate6;
    @BindView(R.id.share_date_7)
    TextView shareDate7;
    @BindView(R.id.share_date_8)
    TextView shareDate8;
    @BindView(R.id.share_date_9)
    TextView shareDate9;
    private ShareActionProvider shareActionProvider;

    public static void start(Context context) {
        Intent starter = new Intent(context, ShareSimpleDateActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_date);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_simple_date, menu);
        MenuItem shareItem = menu.findItem(R.id.menu_item_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.share_date_0, R.id.share_date_1, R.id.share_date_3, R.id.share_date_4,
            R.id.share_date_5, R.id.share_date_6, R.id.share_date_7, R.id.share_date_8,
            R.id.share_date_9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share_date_0:
                ActionProvider provider = new ActionProvider(this) {
                    @Override
                    public View onCreateActionView() {
                        return null;
                    }
                };

                Intent sendIntent1 = new Intent();
                sendIntent1.setAction(Intent.ACTION_SEND);
                sendIntent1.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent1.setType("text/plain");
                startActivity(sendIntent1);
                break;
            case R.id.share_date_1:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
                break;
            case R.id.share_date_3:
//                MimeTypeMap.getFileExtensionFromUrl("");
//                D:\aaWorking\apks\精准课堂
                String uriToImage = "content://media/external/images/media/17039";

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
                //*/*,You can use a MIME type of "*/*",
                // but this will only match activities that are able to handle generic data streams.
                shareIntent.setType("image/jpeg");
                startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));
                break;
            case R.id.share_date_4:
                Uri uri = Uri.parse("content://media/external/images/media/17039");
                ArrayList<Uri> imageUris = new ArrayList<>();
                imageUris.add(uri); // Add your image URIs here
                imageUris.add(uri);

                Intent shareIntent4 = new Intent();
                shareIntent4.setAction(Intent.ACTION_SEND_MULTIPLE);
                shareIntent4.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
                shareIntent4.setType("image/*");
                startActivity(Intent.createChooser(shareIntent4, "Share images to.."));
                break;
            case R.id.share_date_5:
                // Multiple attachment data
//                ArrayList<Uri> uris = getIntent().getParcelableArrayListExtra(Intent.EXTRA_STREAM);
                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.setData(Uri.parse("content://media/external/images/media/17039"));
                intent.putExtra(Intent.EXTRA_STREAM, "content://media/external/images/media/17039");
                intent.setType("image/*");
                shareActionProvider.setShareIntent(intent);
                break;
            case R.id.share_date_6:
//                String schemeFile = ContentResolver.SCHEME_FILE;
//                /data/data/com.testandroid.yang/files
                File filesDir = getFilesDir();
//                /data/data/com.testandroid.yang/cache
                File cacheDir = getCacheDir();
//                /storage/emulated/0/Android/data/com.testandroid.yang/cache
//                Environment.D
                File externalCacheDir = getExternalCacheDir();
//                /storage/emulated/0/Android/data/com.testandroid.yang/files
                File externalFilesDir = getExternalFilesDir(null);
//                /storage/emulated/0
                File storageDirectory = Environment.getExternalStorageDirectory();

                try {
                    Drawable drawable = ContextCompat.getDrawable(this, R.drawable.liushishi);
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

                    FileOutputStream fileOutput = openFileOutput("text.txt", Context.MODE_PRIVATE);
                    fileOutput.write("这是测试文本".getBytes());
                    fileOutput.flush();
                    fileOutput.close();

                    File file = new File(getFilesDir(), "images");
                    if (!file.exists()) {
                        file.mkdirs();
                    }

                    File text11 = new File(file, "text111.txt");
                    FileOutputStream fileOutputStream = new FileOutputStream(text11);
                    fileOutputStream.write("牛牛".getBytes());
                    fileOutputStream.flush();
                    fileOutputStream.close();

                    Uri uriForFile = FileProvider.getUriForFile(this, BuildConfig.FILE_PROVIDER_AUTHORITIES, text11);
                    Log.d(TAG, "onViewClicked: uriForFile=" + uriForFile);//=content://com.testandroid.yang.fileprovider/my_images/text111.txt
//                    grantUriPermission("", uriForFile, Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    revokeUriPermission(uriForFile, Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    Intent resultIntent = new Intent();
                    resultIntent.setDataAndType(uriForFile, getContentResolver().getType(uriForFile));
                    resultIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    resultIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    setResult(RESULT_OK, resultIntent);

                    File file1 = new File(getFilesDir(), "aaaaaa");
                    // Grant temporary read permission to the content URI

                    Log.d(TAG, "onViewClicked: getType=" + getContentResolver().getType(uriForFile));
//                    IOUtils.copy(, outputStream);
//                    setResult(Activity.RESULT_CANCELED,null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.share_date_7:
                Uri uri1 = Uri.fromFile(new File(""));
                try {
//                    ParcelFileDescriptor fileDescriptor = getContentResolver().openFileDescriptor(uri1, "");
//                    FileDescriptor fd = fileDescriptor.getFileDescriptor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.share_date_8:

                break;
            case R.id.share_date_9://thread

                File ggg = getDir("ggg", Context.MODE_PRIVATE);
                Log.d(TAG, "onViewClicked ggg:" + ggg);// /data/data/com.testandroid.yang/app_ggg

                File directory = Environment.getExternalStorageDirectory();
                String path = directory.getPath()
                        + File.separator + "Cuotibao"
                        + File.separator + "Image"
                        + File.separator + "Cache";
                File file = new File(path);
                File[] files = file.listFiles();
                for (int i = 0; i < Math.min(10, files.length); i++) {
                    try {
                        File file1 = files[i];
                        FileInputStream fileInputStream = new FileInputStream(file1);
                        File file2 = new File(getFilesDir().getPath() + File.separator + "images", file1.getName() + ".jpg");
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        IOUtils.copy(fileInputStream, fileOutputStream);
                        Log.d(TAG, "onViewClicked 9: copy success");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
