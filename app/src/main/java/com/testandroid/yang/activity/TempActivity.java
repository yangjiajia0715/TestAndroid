package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.testandroid.yang.R;

/**
 * 打算作为模板用
 * Created by yangjiajia on 2017/1/13 0013.
 */

public class TempActivity extends BaseActivity implements View.OnClickListener {
    private View tvYang;
    public static void start(Context context) {
        Intent starter = new Intent(context, TempActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        initView();

        initData();
        //你好你好 打点滴
    }

    private void initView() {
        tvYang = findViewById(R.id.temp_yang);
        tvYang.setOnClickListener(this);
    }

    private void initData() {
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "哈哈哈哈", Toast.LENGTH_SHORT).show();
    }
}
