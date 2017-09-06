package com.testandroid.yang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;


/**
 * author: yangjiajia
 * create time: 2016/10/20 0020
 * desc:
 */

public class BuildConfigFieldActivity extends Activity implements View.OnClickListener {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        android.R.layout
        setContentView(R.layout.activity_build_config_field);
        textView = (TextView) findViewById(R.id.tv_buildconfig);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    }
}
