package com.testandroid.yang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * RxJavaOperateActivity,模拟网络本地数据库
 * Created by yangjiajia on 2016/11/23 0023.
 */

public class RxJavaOperateActivity extends Activity {

    private static final String TAG = "RxJavaD";
    @BindView(R.id.rx_content)
    TextView rxContent;
    @BindView(R.id.tv_operate_rx_)
    TextView tvOperateRx;
    @BindView(R.id.tv_operate_rx_1)
    TextView tvOperateRx1;
    @BindView(R.id.tv_operate_rx_2)
    TextView tvOperateRx2;
    @BindView(R.id.tv_operate_rx_3)
    TextView tvOperateRx3;
    @BindView(R.id.tv_operate_rx_4)
    TextView tvOperateRx4;
    @BindView(R.id.tv_operate_rx_5)
    TextView tvOperateRx5;
    @BindView(R.id.tv_operate_rx_6)
    TextView tvOperateRx6;
    @BindView(R.id.tv_operate_rx_7)
    TextView tvOperateRx7;
    @BindView(R.id.tv_operate_rx_8)
    TextView tvOperateRx8;
    @BindView(R.id.tv_operate_rx_9)
    TextView tvOperateRx9;

    public static void start(Context context) {
        Intent starter = new Intent(context, RxJavaOperateActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_database);
        ButterKnife.bind(this);
        Log.d(TAG, "--RxJavaOperateActivity--onCreate: ");

    }


    @OnClick({R.id.rx_content, R.id.tv_operate_rx_, R.id.tv_operate_rx_1, R.id.tv_operate_rx_2, R.id.tv_operate_rx_3, R.id.tv_operate_rx_4, R.id.tv_operate_rx_5, R.id.tv_operate_rx_6, R.id.tv_operate_rx_7, R.id.tv_operate_rx_8, R.id.tv_operate_rx_9})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_operate_rx_:

                break;
            case R.id.tv_operate_rx_1:
                break;
            case R.id.tv_operate_rx_2:
                break;
            case R.id.tv_operate_rx_3:
                break;
            case R.id.tv_operate_rx_4:
                break;
            case R.id.tv_operate_rx_5:
                break;
            case R.id.tv_operate_rx_6:
                break;
            case R.id.tv_operate_rx_7:
                break;
            case R.id.tv_operate_rx_8:
                break;
            case R.id.tv_operate_rx_9:
                break;
        }
    }


}
