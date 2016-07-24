package com.example.testandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TextView tvtestnothing11 = (TextView) findViewById(R.id.tv_test_nothing_11);
//        TextView tvtestnothing = (TextView) findViewById(R.id.tv_test_nothing);
        findViewById(R.id.tv_zoom_image).setOnClickListener(this);


        //test android
        //yangjiajia 家里电脑 日志 2016年6月26日19:49:18
        //

        //yangjiajia-company 换系统后
        //2016年7月24日14:04:11



//个人电脑 写啥的 nnnname  eeemail email

//个人电脑 再次还系统后，只有ssk没有配置user.name and email
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_zoom_image:
                intent = new Intent(this, ZoomImageActivity.class);
                startActivity(intent);
                break;
        }
    }
}
