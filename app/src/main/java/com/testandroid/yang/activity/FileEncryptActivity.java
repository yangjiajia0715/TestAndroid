package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.testandroid.yang.R;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
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

        mToolbar.setTitle("文件列表");
        mListview.setOnItemClickListener(this);

        File directory = Environment.getExternalStorageDirectory();
        Collection<File> files = FileUtils.listFiles(directory, null, false);
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

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String path = mdates.get(position);
        File fileP = new File(path);
        mdates.clear();
        Collection<File> files = FileUtils.listFiles(fileP, null, false);
        for (File file : files) {
            mdates.add(file.getPath());
        }
        mAdapter.notifyDataSetChanged();
    }
}
