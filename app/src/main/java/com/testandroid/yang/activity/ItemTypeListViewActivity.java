package com.testandroid.yang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.testandroid.yang.R;
import com.testandroid.yang.adapter.ItemViewTypeAdapter;

/**
 * author: yangjiajia
 * create time: 2016/8/17 0017
 * desc:
 */
public class ItemTypeListViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_type_listview);
        ListView listview = (ListView) findViewById(R.id.listview);
        ItemViewTypeAdapter adapter = new ItemViewTypeAdapter(this);
        listview.setAdapter(adapter);
    }
}
