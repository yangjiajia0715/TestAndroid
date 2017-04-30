package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.common.IServer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 反射
 * Created by yangjiajia on 2017/4/28 0028.
 */

public class ReflectActivity extends BaseActivity {
    private static final String TAG = "ReflectActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.reflect_01)
    TextView reflect01;
    @BindView(R.id.reflect_02)
    TextView reflect02;
    @BindView(R.id.reflect_03)
    TextView reflect03;
    @BindView(R.id.reflect_04)
    TextView reflect04;
    @BindView(R.id.reflect_05)
    TextView reflect05;

    public static void start(Context context) {
        Intent starter = new Intent(context, ReflectActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        setTitle("Reflect");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.reflect_01, R.id.reflect_02, R.id.reflect_03, R.id.reflect_04, R.id.reflect_05})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reflect_01:
                test01();
                break;
            case R.id.reflect_02:
                testProxy();
                break;
            case R.id.reflect_03:

                break;
            case R.id.reflect_04:

                break;
            case R.id.reflect_05:

                break;
        }
    }

    private void testProxy() {
        try {
            Object instance = Proxy.newProxyInstance(getClassLoader(), new Class[]{IServer.class}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    String name = method.getName();
                    Log.d(TAG, "invoke: proxy=" + proxy);
                    Log.d(TAG, "invoke: method=" + method);
                    Log.d(TAG, "invoke: name=" + name);
                    Log.d(TAG, "invoke: args=" + args[0]);
                    args[0] = 111;
                    Object invoke = method.invoke(proxy, args);
                    return invoke;
                }
            });

//            IServer server = (IServer) instance;

//            int age = server.getAge(1);

            Log.d(TAG, "testProxy: instance=" + instance);
//            Log.d(TAG, "testProxy: server=" + server);
//            Log.d(TAG, "testProxy: age=" + age);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Log.d(TAG, "testProxy: e=" + e);
        }

    }

    private void test01() {

    }
}
