package com.testandroid.yang.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * BroadcastReceiverActivity
 * Created by yangjiajia on 2017/4/7 0007.
 */

public class BroadcastReceiverActivity extends BaseActivity {

    @BindView(R.id.broad_01)
    TextView broad01;
    @BindView(R.id.broad_02)
    TextView broad02;
    @BindView(R.id.broad_03)
    TextView broad03;
    @BindView(R.id.broad_04)
    TextView broad04;

    class Receiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //1,不要进行异步操作
            //2,最好别显示对话框，绑定服务

            //In particular, you may not show a dialog or bind to a service from within a BroadcastReceiver.
            // For the former, you should instead use the NotificationManager API.
            // For the latter, you can use Context.startService() to send a command to the service.
        }
    };

    public static void start(Context context) {
        Intent starter = new Intent(context, BroadcastReceiverActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad);
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

    @OnClick({R.id.broad_01, R.id.broad_02, R.id.broad_03, R.id.broad_04})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.broad_01:
                testBroad01();
                break;
            case R.id.broad_02:
                testBroad02();
                break;
            case R.id.broad_03:
                testBroad03();
                break;
            case R.id.broad_04:
                testBroad04();
                break;
        }
    }

    private void testBroad04() {
        sendBroadcast(new Intent("testBroad04"));
    }

    private void testBroad03() {
        Intent intent = new Intent(getString(R.string.action_test_broadcastreceiver));
        sendOrderedBroadcast(intent, null);
    }

    private void testBroad02() {

        Intent intent = new Intent("");

        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
//        broadcastManager.
        broadcastManager.registerReceiver(receiver, new IntentFilter(""));

//        broadcastManager.sendBroadcast(intent);

//        broadcastManager.sendBroadcastSync(intent);

        broadcastManager.unregisterReceiver(receiver);
    }

    private void testBroad01() {
        Intent intent = new Intent("asdfghjkl");
        sendBroadcast(intent);
    }


}
