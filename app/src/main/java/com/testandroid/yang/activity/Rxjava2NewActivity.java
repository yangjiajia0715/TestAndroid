package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 再次学习Rxjava
 * Created by yangjiajia on 2017/8/13.
 * last time:{@link RxJava2Activity}
 */

public class Rxjava2NewActivity extends BaseActivity {

    @BindView(R.id.rxjava2_new_0)
    TextView rxjava2New0;
    @BindView(R.id.rxjava2_new_1)
    TextView rxjava2New1;
    @BindView(R.id.rxjava2_new_2)
    TextView rxjava2New2;
    @BindView(R.id.rxjava2_new_3)
    TextView rxjava2New3;
    @BindView(R.id.rxjava2_new_4)
    TextView rxjava2New4;
    @BindView(R.id.rxjava2_new_5)
    TextView rxjava2New5;
    @BindView(R.id.rxjava2_new_6)
    TextView rxjava2New6;
    @BindView(R.id.rxjava2_new_7)
    TextView rxjava2New7;
    @BindView(R.id.rxjava2_new_8)
    TextView rxjava2New8;
    @BindView(R.id.rxjava2_new_9)
    TextView rxjava2New9;

    public static void start(Context context) {
        Intent starter = new Intent(context, Rxjava2NewActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_new);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.rxjava2_new_0, R.id.rxjava2_new_1, R.id.rxjava2_new_2, R.id.rxjava2_new_3,
            R.id.rxjava2_new_4, R.id.rxjava2_new_5, R.id.rxjava2_new_6, R.id.rxjava2_new_7,
            R.id.rxjava2_new_8, R.id.rxjava2_new_9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rxjava2_new_0:
                break;
            case R.id.rxjava2_new_1:
                break;
            case R.id.rxjava2_new_2:
                break;
            case R.id.rxjava2_new_3:
                break;
            case R.id.rxjava2_new_4:
                break;
            case R.id.rxjava2_new_5:
                break;
            case R.id.rxjava2_new_6:
                break;
            case R.id.rxjava2_new_7:
                break;
            case R.id.rxjava2_new_8:
                break;
            case R.id.rxjava2_new_9:
                break;
        }
    }
}
