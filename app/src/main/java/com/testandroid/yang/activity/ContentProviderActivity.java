package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.testandroid.yang.R;

/**
 * ContentProviderActivity
 * Created by yangjiajia on 2017/9/15.
 */

public class ContentProviderActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, ContentProviderActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        initView();
        initData();
    }

    @Override
    public void initView() {
//        CursorLoader loader = new CursorLoader()
    }

    @Override
    public void initData() {

    }
}
