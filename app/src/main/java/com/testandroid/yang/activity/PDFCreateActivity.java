package com.testandroid.yang.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.testandroid.yang.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PDFCreateActivity extends AppCompatActivity {

    private static final String TAG = "PDFCreateActivity";
    public static void start(Context context) {
        Intent starter = new Intent(context, PDFCreateActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfcreate);

        ButterKnife.bind(this);

        requestNeededPermission();
    }

    public void requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(PDFCreateActivity.this,
                        "I need it for pdf creating", Toast.LENGTH_SHORT).show();
            }

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    101);
        } else {
            Toast.makeText(PDFCreateActivity.this,
                    "WRITE_EXTERNAL_STORAGE perm granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 101: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(PDFCreateActivity.this,
                            "WRITE_EXTERNAL_STORAGE perm granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PDFCreateActivity.this,
                            "WRITE_EXTERNAL_STORAGE perm NOT granted", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    @OnClick(R.id.btnCreatePdf)
    public void createPdfClicked(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            new Thread() {
                @TargetApi(Build.VERSION_CODES.KITKAT)
                public void run() {
                    // Get the directory for the app's private pictures directory.
                    final File file = new File(
                            Environment.getExternalStorageDirectory(), "" + "test.pdf");

                    if (file.exists()) {
                        file.delete();
                    }

                    FileOutputStream out = null;
                    try {
                        out = new FileOutputStream(file);

                        PdfDocument document = new PdfDocument();
                        Point windowSize = new Point();
                        getWindowManager().getDefaultDisplay().getSize(windowSize);
                        PdfDocument.PageInfo pageInfo =
                                new PdfDocument.PageInfo.Builder(
                                        windowSize.x, windowSize.y, 1).create();

                        Log.d(TAG, "PDFCreateActivity--run: windowSize=" + windowSize);

                        PdfDocument.Page page = document.startPage(pageInfo);
                        View content = getWindow().getDecorView();
                        content.draw(page.getCanvas());
                        document.finishPage(page);
                        document.writeTo(out);
                        document.close();
                        out.flush();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PDFCreateActivity.this, "File created: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
                            }
                        });
                    } catch (Exception e) {
                        Log.d("TAG_PDF", "File was not created: " + e.getMessage());
                    } finally {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();

        }
    }
}
