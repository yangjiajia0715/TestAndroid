package com.example.testandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TextView tvtestnothing11 = (TextView) findViewById(R.id.tv_test_nothing_11);
//        TextView tvtestnothing = (TextView) findViewById(R.id.tv_test_nothing);
        findViewById(R.id.tv_test_nothing).setOnClickListener(this);



        //test android
        //yangjiajia 家里电脑 日志 2016年6月26日19:49:18
        //
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.tv_test_nothing:
                break;
        }
    }
}
