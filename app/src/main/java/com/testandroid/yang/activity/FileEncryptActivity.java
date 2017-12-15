package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.testandroid.yang.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileEncryptActivity
 * Created by yangjiajia on 2017/12/15.
 */

public class FileEncryptActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.listview)
    ListView mListview;
    private List<String> mdates = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    private static final String FILE_SUFFIX = ".encrypion";
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
        for (File file : files) {
            mdates.add(file.getPath());
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
                if (!TextUtils.isEmpty(mCurPath)) {
                    File file = new File(mCurPath);
                    if (file.isFile()) {
                        if (!file.getParent().endsWith(FILE_SUFFIX)) {
                            File newfile = new File(file.getPath() + FILE_SUFFIX);
                            file.renameTo(newfile);
                        }
                    }
                }
                return true;
            case R.id.action_file_decode:
                if (!TextUtils.isEmpty(mCurPath)) {
                    File file = new File(mCurPath);
                    if (file.isFile() && file.getParent().endsWith(FILE_SUFFIX)) {
                        String path = file.getPath();
                        String substring = path.substring(0, path.indexOf(FILE_SUFFIX));
                        File newfile = new File(substring);
                        file.renameTo(newfile);
                    }
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
        mdates.clear();
//        Collection<File> files = FileUtils.listFiles(fileP, null, false);
        File[] files = fileP.listFiles();
        for (File file : files) {
            mdates.add(file.getPath());
        }
        mAdapter.notifyDataSetChanged();
    }
}
