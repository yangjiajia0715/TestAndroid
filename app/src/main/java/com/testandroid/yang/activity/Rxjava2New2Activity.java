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
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by yangjiajia on 2018/6/19.
 * @author yangj
 */
public class Rxjava2New2Activity extends BaseActivity {
    private static final String TAG = "Rxjava2New2Activity";
    private static final String ffff = "test";
    Thread mThread = new Thread();

    @BindView(R.id.btn_0)
    Button mBtn0;
    @BindView(R.id.btn_1)
    Button mBtn1;
    @BindView(R.id.btn_2)
    Button mBtn2;
    @BindView(R.id.btn_3)
    Button mBtn3;
    @BindView(R.id.btn_4)
    Button mBtn4;
    @BindView(R.id.btn_5)
    Button mBtn5;
    @BindView(R.id.btn_6)
    Button mBtn6;

    public static void start(Context context) {
        Intent starter = new Intent(context, Rxjava2New2Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_new_2);
        ButterKnife.bind(this);
        initView();
        initData();

        Integer integer1 = 1;
        Integer integer2 = 2;
        //-128 128 从缓存中取

//        if (integer1 == integer2) {
//
//        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
                test0();
                break;
            case R.id.btn_1:
                break;
            case R.id.btn_2:
                break;
            case R.id.btn_3:
                break;
            case R.id.btn_4:
                break;
            case R.id.btn_5:
                break;
            case R.id.btn_6:
                break;
            default:
                break;
        }
    }

    private void test0() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("aaaa");
            }
        });


    }

    private void testFlatMap() {
    }
}
