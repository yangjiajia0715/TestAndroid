package com.testandroid.yang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.testandroid.yang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author: yangjiajia
 * create time: 2016/8/4 0004
 * desc:
 */
public class ListViewClipActivity extends Activity {
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_clip);
        initView();
    }

    private void initView() {

        mListView = (ListView) findViewById(R.id.listview);

        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("错题会"+ i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas);
        mListView.setAdapter(adapter);
    }
}
