package com.testandroid.yang.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.testandroid.yang.R;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 进程和线程
 * file:///E:/sdk/docs/guide/components/processes-and-threads.html#Processes
 * Created by yangjiajia on 2018/6/12.
 */
public class ProcessedAndThreadsActivity extends BaseActivity {

    @BindView(R.id.btn_0)
    Button mBtn0;
    @BindView(R.id.btn_1)
    Button mBtn1;
    @BindView(R.id.btn_2)
    Button mBtn2;

    public static void start(Context context) {
        Intent starter = new Intent(context, ProcessedAndThreadsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processed_thread);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
//        new Intent(this, Service);
//        Service service = new TestServicer();
//        service.startForeground(0,new Notification());
        //1.forground process

        //2.visible process

        //3,Service process
//        startService(new Intent());

        //4.Background process

        //5.Empty process

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });


        mBtn0.post(new Runnable() {
            @Override
            public void run() {

            }
        });

        mBtn0.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 1);

        MyAysncTask myAysncTask = new MyAysncTask();
        try {
            Bitmap bitmap = myAysncTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        AsyncTask.Status status = myAysncTask.getStatus();
//        myAysncTask.
        AsyncTask<String, Integer, Bitmap> execute = myAysncTask.execute("");
//        execute.cancel(true);
//        myAysncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");

        //protect default public private


        ContentResolver contentResolver = getContentResolver();
//        contentResolver.query()
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_0, R.id.btn_1, R.id.btn_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
                break;
            case R.id.btn_1:
                break;
            case R.id.btn_2:
                break;
        }
    }

    /**
     * configrature change 会导致  unexpected restarts
     */
    static class MyAysncTask extends AsyncTask<String, Integer, Bitmap> {
        private AtomicInteger mAtomicInteger;
        private AtomicBoolean mAtomicBoolean;

        public MyAysncTask() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mAtomicInteger = new AtomicInteger(1);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            int status = mAtomicInteger.get();

            mAtomicBoolean = new AtomicBoolean(true);

            publishProgress(1);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            boolean b = mAtomicBoolean.get();
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            boolean b = mAtomicBoolean.get();
            super.onPostExecute(bitmap);
        }

        @Override
        protected void onCancelled(Bitmap bitmap) {
            super.onCancelled(bitmap);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}
