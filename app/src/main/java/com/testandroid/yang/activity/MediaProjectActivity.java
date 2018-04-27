package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangjiajia on 2018/4/27.
 */
public class MediaProjectActivity extends BaseActivity {

    @BindView(R.id.meida_1)
    Button mMeida1;
    @BindView(R.id.meida_2)
    Button mMeida2;
    @BindView(R.id.meida_3)
    Button mMeida3;
    @BindView(R.id.meida_4)
    Button mMeida4;
    @BindView(R.id.meida_5)
    Button mMeida5;

    public static void start(Context context) {
        Intent starter = new Intent(context, MediaProjectActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaprojection);
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

    @OnClick({R.id.meida_1, R.id.meida_2, R.id.meida_3, R.id.meida_4, R.id.meida_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.meida_1:
                
                break;
            case R.id.meida_2:
                break;
            case R.id.meida_3:
                break;
            case R.id.meida_4:
                break;
            case R.id.meida_5:
                break;
        }
    }
}
