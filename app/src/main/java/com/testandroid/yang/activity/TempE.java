package com.testandroid.yang.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.testandroid.yang.R;

public class TempE extends Activity implements View.OnClickListener {

    private View tvYang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_e);
        initView();
        initData();
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
