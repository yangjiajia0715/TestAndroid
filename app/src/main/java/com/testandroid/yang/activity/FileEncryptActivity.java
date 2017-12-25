package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.testandroid.yang.R;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileEncryptActivity
 * Created by yangjiajia on 2017/12/15.
 */

public class FileEncryptActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "FileEncryptActivity";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.listview)
    ListView mListview;
    private List<String> mdates = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    private static final String FILE_SUFFIX = ".encrypion";
    private static final String FILE_PREFFIX = "11";
    private String mCurPath;

    public static void start(Context context) {
        Intent starter = new Intent(context, FileEncryptActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_encrpty);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(mToolbar);

        mToolbar.setTitle("文件列表");
        mListview.setOnItemClickListener(this);


        File directory = Environment.getExternalStorageDirectory();
        File[] files = directory.listFiles();
//        Collection<File> files = FileUtils.listFiles(directory, null, false);
        Log.d(TAG, "initView: files=" + files);
//        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        Log.d(TAG, "initView: files=" + files);
        if (files != null) {
            for (File file : files) {
                Log.d(TAG, "initView: file=" + file.getPath());

                String path = file.getPath();
                if (path.contains("aa") || path.contains("00"))
                    mdates.add(file.getPath());
            }
        }
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mdates);
        mListview.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_file_encrypty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_file_encrypy:
                FileUtils.byteCountToDisplaySize(11);
                Formatter.formatFileSize(this, 100);//格式化

                Log.d(TAG, "onOptionsItemSelected: mCurPath=" + mCurPath);
                if (!TextUtils.isEmpty(mCurPath)) {
                    File fileCurDir = new File(mCurPath);
                    Log.d(TAG, "onOptionsItemSelected:  file.isFile()=" + fileCurDir.isFile());
                    File[] files = fileCurDir.listFiles();
                    for (File file : files) {
                        if (file.isFile()) {
                            if (!file.getPath().endsWith(FILE_SUFFIX)) {
                                String fileName = file.getName();
                                try {
//                                    gb2312
                                    fileName = new String(fileName.getBytes("utf-8"),
                                            "iso8859-1");
                                    Log.d(TAG, "onOptionsItemSelected:  fileName=" + fileName);
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
//                                File newfile = new File(file.getPath() + FILE_SUFFIX);
                                File newfile = new File(file.getParent(), fileName + FILE_SUFFIX);
                                boolean success = file.renameTo(newfile);
                                Log.d(TAG, "onOptionsItemSelected:  success=" + success);
                            }
                        }
                    }
                    loader(mCurPath);
                }
                return true;
            case R.id.action_file_decode:
                if (!TextUtils.isEmpty(mCurPath)) {
                    File fileCurDir = new File(mCurPath);
                    Log.d(TAG, "onOptionsItemSelected:ddd file.isFile()=" + fileCurDir.isFile());
                    File[] files = fileCurDir.listFiles();
                    for (File file : files) {
                        if (file.isFile() && file.getPath().endsWith(FILE_SUFFIX)) {
                            String path = file.getPath();
                            String substring = path.substring(0, path.indexOf(FILE_SUFFIX));
                            File newfile = new File(substring);
                            String newfileName = newfile.getName();
                            try {
                                newfileName = new String(newfileName.getBytes("iso8859-1"),
                                        "utf-8");
                                Log.d(TAG, "newfileName" + newfileName);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            boolean success = file.renameTo(new File(newfile.getParent(), newfileName));
                            Log.d(TAG, "onOptionsItemSelected:ddd  success=" + success);
                        }
                    }
                    loader(mCurPath);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mCurPath = mdates.get(position);
        File fileP = new File(mCurPath);
        if (fileP.isFile()) {
            Toast.makeText(this, "文件", Toast.LENGTH_SHORT).show();
            return;
        }
        loader(fileP);
    }

    private void loader(File file){
        loader(file.getPath());
    }
    private void loader(String path){
        if (TextUtils.isEmpty(path)) {
            return;
        }
        mdates.clear();

        File fileP = new File(path);
        File[] files = fileP.listFiles();
        for (File file : files) {
            mdates.add(file.getPath());
        }
        mAdapter.notifyDataSetChanged();
    }
}
