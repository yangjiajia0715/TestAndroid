package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.testandroid.yang.R;
import com.testandroid.yang.log.Log;

/**
 * 测试启动就finish
 * Created by yangjiajia on 2018/5/8.
 */
public class EmptyActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, EmptyActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        Log.d("EmptyActivity---onCreate----");
        finish();
    }
}
