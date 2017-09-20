package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;

import com.testandroid.yang.R;

/**
 * Created by yangjiajia on 2017/9/19.
 */

public class DateUtilActivity extends BaseActivity {
    private static final String TAG = "DateUtilActivity";

    public static void start(Context context) {
        Intent starter = new Intent(context, DateUtilActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_util);
        initView();
        initData();
    }

    @Override
    public void initView() {
        long lastDay = System.currentTimeMillis() - 28 * 60 * 60 * 1000;
//        long tomo = System.currentTimeMillis() + 4 * 60 * 60 * 1000;
        long tomo = System.currentTimeMillis() + 10 * 60 * 1000;
        Log.d(TAG, "initView: " + DateUtils.getRelativeTimeSpanString(this, System.currentTimeMillis() + DateUtils.DAY_IN_MILLIS+ 66*10000));

//        昨天
        Log.d(TAG, "initView: " + DateUtils.getRelativeTimeSpanString(tomo, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS, DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME));
//        Log.d(TAG, "initView: " + DateUtils.getRelativeTimeSpanString(tomo, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS, DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_TIME));
//        Log.d(TAG, "initView: " + DateUtils.getRelativeTimeSpanString(System.currentTimeMillis() - lastDay));//1 分钟前,1970年1月2日
//        Log.d(TAG, "initView: " + DateUtils.getRelativeTimeSpanString(System.currentTimeMillis() + tomo));//2065年6月9日
//        Log.d(TAG, "initView: " + DateUtils.getRelativeTimeSpanString(this,System.currentTimeMillis()- 60*100,false));//14:16
//        Log.d(TAG, "initView: " + DateUtils.getRelativeTimeSpanString(this, System.currentTimeMillis()));//14:17
    }

    @Override
    public void initData() {

    }
}
