package com.testandroid.yang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.common.House;

import java.util.ArrayList;
import java.util.List;

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
    private String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};;

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
                test();
                break;
            case R.id.tv_operate_rx_1:
                testMap();
                break;
            case R.id.tv_operate_rx_2:
                testGroupBy();
                break;
            case R.id.tv_operate_rx_3:
                testMerge();
                break;
            case R.id.tv_operate_rx_4:
                testinterval();
                break;
            case R.id.tv_operate_rx_5:
                testZip();
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

    private void test() {
        rxContent.setText("");
//        Observable.from()

    }

    private void testMap() {
        rxContent.setText("");


    }

    private void testGroupBy() {
        rxContent.setText("");
        List<House> houses = new ArrayList<>();
        houses.add(new House("中粮·海景壹号", "中粮海景壹号新出大平层！总价4500W起"));
        houses.add(new House("竹园新村", "满五唯一，黄金地段"));
        houses.add(new House("中粮·海景壹号", "毗邻汤臣一品"));
        houses.add(new House("竹园新村", "顶层户型，两室一厅"));
        houses.add(new House("中粮·海景壹号", "南北通透，豪华五房"));

    }

    private void testMerge(){


    }

    private void testinterval() {
        rxContent.setText("");

    }

    private void testZip() {

    }


}
