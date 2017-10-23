package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.fragment.FullScreenFragment;
import com.testandroid.yang.fragment.FullScreenFragmentTwo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主页全屏模式 沉浸式？
 * Created by yangjiajia on 2017/10/23.
 */

public class FullScreenActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottom_navi)
    LinearLayout bottomNavi;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.bottom_letter)
    TextView bottomLetter;
    @BindView(R.id.bottom_month)
    TextView bottomMonth;

    public static void start(Context context) {
        Intent starter = new Intent(context, FullScreenActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, FullScreenFragment.newInstance())
                .commitAllowingStateLoss();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
//            actionBar.hide();
        }
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.bottom_letter, R.id.bottom_month})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bottom_letter:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, FullScreenFragment.newInstance())
                        .commitAllowingStateLoss();
                break;
            case R.id.bottom_month:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, FullScreenFragmentTwo.newInstance())
                        .commitAllowingStateLoss();
                break;
        }
    }
}
