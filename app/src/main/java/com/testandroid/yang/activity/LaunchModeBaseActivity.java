package com.testandroid.yang.activity;

import android.app.Activity;
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
 * 测试启动模式：测试手机三星s5
 * flag_activity_new_task和启动模式 singleTask相同 的前提是taskAffinity 不同！！！
 * 如果taskAffinity相同！！！
 * 否则和标准模式一样 启动一个新的activity！！！！
 * <p>
 * 启动模式：
 * 一：standard 默认启动模式
 * 二：singleInstance:
 * 1,该activity 单独位于一个任务栈中
 * 2,startActivity:若有该actiity的实例，则不再创建，调用onNewIntent,
 * 若没有该activity的实例则放在新创建的任务栈中
 * 3,startActivityForResult：放入当前任务栈中，但是从该activity中启动的页面会会放在新任务栈中
 * 三：singleTask A启动B(singleTask)
 * 1，首先看taskAffinity是否相同，若相同且当前任务栈中没有，放在任务栈的顶部，
 * 2，若相同：当前任务栈中没有，放在任务栈的顶部，相同且当前任务栈有该Actiivty的实例则弹出他上面的所有activity，并调用onNewIntent
 * 3，taskAffinity:taskaffinity的栈中有则调用onNewIntent,没有则创建
 * 4，startActivityForResult： onActivityResult中 5.0以后可以获取数据，5.0之前不能得到返回值
 * 四：singleTop：栈顶复用
 *flag-----------
 *
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
        Log.d(TAG, "onCreate: " + getClass().getSimpleName() + ",getTaskId=" + getTaskId());
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
//                if (getPackageManager().resolveActivity(new Intent(), PackageManager.MATCH_DEFAULT_ONLY) !=null) {
//
//                }
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
//                LaunchModeA_Activity.start(this);
                LaunchModeA_Activity.startForResult(this,111);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: resultCode=" + resultCode);
        if (resultCode == Activity.RESULT_OK) {
            Log.d(TAG, "onActivityResult: RESULT_OK");
        }
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: " + getClass().getSimpleName());
        super.onDestroy();

    }
}
