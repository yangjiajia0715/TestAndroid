package com.example.testandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.testandroid.BuildConfig;
import com.example.testandroid.R;

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
        setContentView(R.layout.activity_build_config_field);
        textView = (TextView) findViewById(R.id.tv_buildconfig);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n APPLICATION_ID=" + BuildConfig.APPLICATION_ID);
        builder.append("\n BUILD_TYPE=" + BuildConfig.BUILD_TYPE);
        builder.append("\n FLAVOR=" + BuildConfig.FLAVOR);
        builder.append("\n FLAVOR_abc=" + BuildConfig.FLAVOR_abc);
        builder.append("\n VERSION_NAME=" + BuildConfig.VERSION_NAME);
        builder.append("\n DEBUG=" + BuildConfig.DEBUG);
        builder.append("\n Message=" + BuildConfig.Message);
        textView.setText("BuildConfig=" + builder.toString());
    }
}
