package com.testandroid.yang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.testandroid.yang.R;


/**
 * author: yangjiajia
 * create time: 2016/8/4 0004
 * desc:
 */
public class ScrollViewClipActivity extends Activity {
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview_clip);
        initView();
    }

    private void initView() {

    }
}
