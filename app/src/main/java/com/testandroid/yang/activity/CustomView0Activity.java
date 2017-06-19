package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.view.ViewTestPaint;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义view
 * Created by yangjiajia on 2017/6/13 0013.
 */

public class CustomView0Activity extends BaseActivity {
    private static final String TAG = "CustomView0Activity";
    @BindView(R.id.viewtestpaint)
    ViewTestPaint viewtestpaint;

    public static void start(Context context) {
        Intent starter = new Intent(context, CustomView0Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_00);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        int measuredHeight = viewtestpaint.getMeasuredHeight();
        int measuredWidth = viewtestpaint.getMeasuredWidth();
        Log.d(TAG, "initView: measuredWidth="+ measuredWidth + ",measuredHeight=" + measuredHeight);

        int width = viewtestpaint.getWidth();
        int height = viewtestpaint.getHeight();
        Log.d(TAG, "initView: width="+ width + ",height=" + height);

        test();
    }

    private void test() {
        TextView textView = new TextView(this);
        textView.setError("错了");
//        ViewCompat.gen
    }

    @Override
    public void initData() {

    }
}
