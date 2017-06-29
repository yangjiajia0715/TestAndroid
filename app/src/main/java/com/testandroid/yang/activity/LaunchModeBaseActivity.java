package com.testandroid.yang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 测试启动模式
 * Created by yangjiajia on 2017/6/28.
 */

public class LaunchModeBaseActivity extends BaseActivity {
    private static final String TAG = "LaunchModeBaseActivity";
    @BindView(R.id.task_id)
    TextView taskId;
    @BindView(R.id.launch_mode_a)
    TextView launchModeA;
    @BindView(R.id.launch_mode_b)
    TextView launchModeB;
    @BindView(R.id.launch_mode_c)
    TextView launchModeC;
    @BindView(R.id.launch_mode_d)
    TextView launchModeD;
    @BindView(R.id.launch_mode_e)
    TextView launchModeE;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode_base);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {

        toolbar.setTitle(getClass().getSimpleName());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        taskId.append("taskId = ");
        taskId.append(String.valueOf(getTaskId()));
        if (getParent() != null) {
            taskId.append(" parent taskId=" + getParent().getTaskId());
        }


    }

    @Override
    public void initData() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: " + getClass().getSimpleName());
    }

    @OnClick({R.id.launch_mode_a, R.id.launch_mode_b, R.id.launch_mode_c, R.id.launch_mode_d, R.id.launch_mode_e})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.launch_mode_a:
                LaunchModeB_Activity.start(this);
                break;
            case R.id.launch_mode_b:
                LaunchModeC_Activity.start(this);
                break;
            case R.id.launch_mode_c:
                LaunchModeD_Activity.start(this);
                break;
            case R.id.launch_mode_d:
                LaunchModeE_Activity.start(this);
                break;
            case R.id.launch_mode_e:
                LaunchModeA_Activity.start(this);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: " + getClass().getSimpleName());
        super.onDestroy();

    }
}
