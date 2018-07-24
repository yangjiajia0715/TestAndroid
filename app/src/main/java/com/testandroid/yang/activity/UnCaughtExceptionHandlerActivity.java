package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author yangjiajia
 * @date 2018/7/24
 */
public class UnCaughtExceptionHandlerActivity extends BaseActivity {
    private static final String TAG = "UnCaughtExceptionHandle";
    @BindView(R.id.btn_uncaught_1)
    Button mBtnUncaught1;
    @BindView(R.id.btn_uncaught_2)
    Button mBtnUncaught2;
    @BindView(R.id.btn_uncaught_3)
    Button mBtnUncaught3;
    @BindView(R.id.btn_uncaught_4)
    Button mBtnUncaught4;
    @BindView(R.id.btn_uncaught_5)
    Button mBtnUncaught5;

    public static void start(Context context) {
        Intent starter = new Intent(context, UnCaughtExceptionHandlerActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uncaught_exception_handler);
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

    private void unCaughtException() {

        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                Log.d(TAG, "线程对象的uncaughtExceptionHandler: e=" + e.getMessage());
            }
        };

        Thread.UncaughtExceptionHandler sUncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                Log.d(TAG, "全局的uncaughtExceptionHandler: e=" + e.getMessage());
            }
        };

        ThreadGroup threadGroup = new ThreadGroup("thread-group-name");

        Thread currentThread = Thread.currentThread();

        //number: Thread-7
//        Thread thread1 = new Thread();
//
//        Thread thread2 = new Thread(threadGroup, "threadName-qibu");

        currentThread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
//
        Thread.setDefaultUncaughtExceptionHandler(sUncaughtExceptionHandler);

    }

    @OnClick({R.id.btn_uncaught_1, R.id.btn_uncaught_2, R.id.btn_uncaught_3,
            R.id.btn_uncaught_4, R.id.btn_uncaught_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_uncaught_1:
//                unCaughtException();
                break;
            case R.id.btn_uncaught_2:
//                int i = 0;
//                int j = 9;
//                int k = j / i;
                break;
            case R.id.btn_uncaught_3:
                Log.d(TAG, "onViewClicked name=" + Thread.currentThread().getName());
                break;
            case R.id.btn_uncaught_4:
                break;
            case R.id.btn_uncaught_5:
                break;
            default:
                break;
        }
    }
}
