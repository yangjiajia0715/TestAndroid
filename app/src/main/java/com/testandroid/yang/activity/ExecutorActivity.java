package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.testandroid.yang.R;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author yangjiajia
 * @date 2018/7/25
 */
public class ExecutorActivity extends BaseActivity {
    private static final String TAG = "ExecutorActivity";

    @BindView(R.id.btn_executor_1)
    Button mBtnExecutor1;
    @BindView(R.id.btn_executor_2)
    Button mBtnExecutor2;
    @BindView(R.id.btn_executor_3)
    Button mBtnExecutor3;
    @BindView(R.id.btn_executor_4)
    Button mBtnExecutor4;
    @BindView(R.id.btn_executor_5)
    Button mBtnExecutor5;
    private Timer mTimer;
    private ScheduledExecutorService mScheduledExecutorService;

    public static void start(Context context) {
        Intent starter = new Intent(context, ExecutorActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executor);
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

    @OnClick({R.id.btn_executor_1, R.id.btn_executor_2, R.id.btn_executor_3,
            R.id.btn_executor_4, R.id.btn_executor_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_executor_1:
                schedularExecutorServices();
                break;
            case R.id.btn_executor_2:
                timer();
                break;
            case R.id.btn_executor_3:
                break;
            case R.id.btn_executor_4:
                if (mTimer != null) {
                    mTimer.cancel();
                }
                break;
            case R.id.btn_executor_5:
                if (mScheduledExecutorService != null) {
                    mScheduledExecutorService.shutdown();
                }
                break;
            default:
                break;
        }
    }

    private void timer() {
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "timer run thread: " + Thread.currentThread().getName());
            }
        };

        timerTask.cancel();
        mTimer = new Timer();
//        mTimer.cancel();
        mTimer.scheduleAtFixedRate(timerTask, 1000, 3000);
    }

    private void schedularExecutorServices() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "schedular run thread: " + Thread.currentThread().getName());
            }
        };

        mScheduledExecutorService = new ScheduledThreadPoolExecutor(1, new DemoThreadFactory("ExecutorActivity"));
//                = new ScheduledExecutorService();
//        scheduledExecutorService.
//        scheduledExecutorService.shutdown();
//        List<Runnable> runnableList = scheduledExecutorService.shutdownNow();
//        scheduledExecutorService.
//        mScheduledExecutorService.schedule(runnable,100, TimeUnit.MILLISECONDS);
//        mScheduledExecutorService.scheduleAtFixedRate(runnable, 0, 3000, TimeUnit.MILLISECONDS);
        mScheduledExecutorService.scheduleAtFixedRate(runnable, 0, 1000, TimeUnit.MILLISECONDS);

//        Executors.newScheduledThreadPool(2);
    }

    class DemoThreadFactory implements ThreadFactory {
        String name;
        int threadNumber;

        public DemoThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(@NonNull Runnable r) {
            Thread thread = new Thread("demo-" + name + "-thread-" + threadNumber);
            threadNumber++;
            return thread;
        }
    }
}
