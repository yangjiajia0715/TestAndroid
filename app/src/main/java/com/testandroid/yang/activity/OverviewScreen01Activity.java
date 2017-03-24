package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;

/**
 * overview
 * Created by yangjiajia on 2017/3/23.
 */

public class OverviewScreen01Activity extends BaseActivity {

    private static final String TAG = "OVerviewScreen";
    private TextView tvContext;

    public static void start(Context context) {
        Intent starter = new Intent(context, OverviewScreen01Activity.class);
//        starter.putExtra();
//        starter.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
//        starter.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        context.startActivity(starter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_screen);
        tvContext = (TextView) findViewById(R.id.overview_text);
//        getSystemService()
        findViewById(R.id.overview_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "OverviewScreen01Activity--onClick: getTaskId=" + getTaskId());
                OverviewScreen01Activity.start(OverviewScreen01Activity.this);
            }
        });
        Log.d(TAG, "OverviewScreen01Activity--onCreate: getTaskId=" + getTaskId());
    }


    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "OverviewScreen01Activity--onNewIntent: getTaskId=" + getTaskId());
        tvContext.setText("onnew intent");
        super.onNewIntent(intent);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
