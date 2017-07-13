package com.testandroid.yang.activity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.testandroid.yang.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ViewAnimator
 * author: yangjiajia
 * create time: 2016-9-21
 * desc:
 */
public class CheckTextViewNewActivity extends ListActivity {
    private static final String TAG = "CheckTextViewNewActivit";

    public static void start(Context context) {
        Intent starter = new Intent(context, CheckTextViewNewActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(android.R.layout.two_line_list_item);

        List<Map<String, String>> dates = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("item0", "item" + i);
            map.put("item1", "item" + i);
            dates.add(map);
            list.add("选项" + i);
        }

//        SimpleAdapter simpleAdapter = new SimpleAdapter(this, dates, android.R.layout.two_line_list_item,
//                new String[]{"item0", "item1"}, new int[]{android.R.id.text1, android.R.id.text2});

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.item_checked_textview,
                R.id.item_checked_textview_two, list);
        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.d(TAG, "onListItemClick: position=" + position);
    }
}
