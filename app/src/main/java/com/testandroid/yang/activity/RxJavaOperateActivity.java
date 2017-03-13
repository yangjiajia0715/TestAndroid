package com.testandroid.yang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.common.House;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;

/**
 * RxJavaOperateActivity,模拟网络本地数据库
 * Created by yangjiajia on 2016/11/23 0023.
 */

public class RxJavaOperateActivity extends Activity {

    private static final String TAG = "RxJavaD";
    @BindView(R.id.rx_content)
    TextView rxContent;
    @BindView(R.id.tv_operate_rx_)
    TextView tvOperateRx;
    @BindView(R.id.tv_operate_rx_1)
    TextView tvOperateRx1;
    @BindView(R.id.tv_operate_rx_2)
    TextView tvOperateRx2;
    @BindView(R.id.tv_operate_rx_3)
    TextView tvOperateRx3;
    @BindView(R.id.tv_operate_rx_4)
    TextView tvOperateRx4;
    @BindView(R.id.tv_operate_rx_5)
    TextView tvOperateRx5;
    @BindView(R.id.tv_operate_rx_6)
    TextView tvOperateRx6;
    @BindView(R.id.tv_operate_rx_7)
    TextView tvOperateRx7;
    @BindView(R.id.tv_operate_rx_8)
    TextView tvOperateRx8;
    @BindView(R.id.tv_operate_rx_9)
    TextView tvOperateRx9;
    private String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};;

    public static void start(Context context) {
        Intent starter = new Intent(context, RxJavaOperateActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_database);
        ButterKnife.bind(this);
        Log.d(TAG, "--RxJavaOperateActivity--onCreate: ");
    }


    @OnClick({R.id.rx_content, R.id.tv_operate_rx_, R.id.tv_operate_rx_1, R.id.tv_operate_rx_2, R.id.tv_operate_rx_3, R.id.tv_operate_rx_4, R.id.tv_operate_rx_5, R.id.tv_operate_rx_6, R.id.tv_operate_rx_7, R.id.tv_operate_rx_8, R.id.tv_operate_rx_9})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_operate_rx_:
                test();
                break;
            case R.id.tv_operate_rx_1:
                testMap();
                break;
            case R.id.tv_operate_rx_2:
                testGroupBy();
                break;
            case R.id.tv_operate_rx_3:
                testMerge();
                break;
            case R.id.tv_operate_rx_4:
                testinterval();
                break;
            case R.id.tv_operate_rx_5:
                testZip();
                break;
            case R.id.tv_operate_rx_6:
                break;
            case R.id.tv_operate_rx_7:
                break;
            case R.id.tv_operate_rx_8:
                break;
            case R.id.tv_operate_rx_9:
                break;
        }
    }

    private void test() {
        rxContent.setText("");
//        Observable.from()

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.d(TAG, "test--call: subscriber=" + subscriber);
                Log.d(TAG, "test--call: currentThread getName=" + Thread.currentThread().getName());
                subscriber.onStart();

                subscriber.onNext("张三");
                subscriber.onNext("张三1");
                subscriber.onNext("张三2");

                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "test--onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "test--onError: e=" + e);
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "test--onNext: s=" + s);
                rxContent.setText(rxContent.getText() + " " + s);
            }
        };

        observable.subscribe(observer);


    }

    private void testMap() {
        rxContent.setText("");

        Observable.just(1,2,3).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "我是" + integer;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "testMap---call: s=" + s);
                rxContent.setText(rxContent.getText() + " " +  s);
            }
        });

//        Observable.just(1,2,3).scan(new Func2<Integer, Integer, Integer>() {
//            @Override
//            public Integer call(Integer integer, Integer integer2) {
//                return integer + integer2;
//            }
//        });
    }

    private void testGroupBy() {
        rxContent.setText("");
        List<House> houses = new ArrayList<>();
        houses.add(new House("中粮·海景壹号", "中粮海景壹号新出大平层！总价4500W起"));
        houses.add(new House("竹园新村", "满五唯一，黄金地段"));
        houses.add(new House("中粮·海景壹号", "毗邻汤臣一品"));
        houses.add(new House("竹园新村", "顶层户型，两室一厅"));
        houses.add(new House("中粮·海景壹号", "南北通透，豪华五房"));

        Observable<GroupedObservable<String, House>> groupByCommunityNameObservable = Observable.from(houses)
                .groupBy(new Func1<House, String>() {

                    @Override
                    public String call(House house) {
                        return house.getName();
                    }
                });

        groupByCommunityNameObservable.subscribe(new Action1<GroupedObservable<String, House>>() {
            @Override
            public void call(GroupedObservable<String, House> stringHouseGroupedObservable) {
                String key = stringHouseGroupedObservable.getKey();
//                stringHouseGroupedObservable.
            }
        });

        Observable.concat(groupByCommunityNameObservable)
                .subscribe(new Action1<House>() {
                    @Override
                    public void call(House house) {
                       String desc =  "小区:"+house.getName()+"; 房源描述:"+house.toString() + "\\n";//no use?
                        rxContent.setText(rxContent.getText() + desc);
                    }
                });
//        Observable.from(houses).dis
    }

    private void testMerge(){


        Observable<String> letterSequence = Observable.interval(2000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, String>() {
                    @Override
                    public String call(Long position) {
                        return letters[position.intValue()];
                    }
                }).take(letters.length);

        Observable<Long> numberSequence = Observable.interval(3000, TimeUnit.MILLISECONDS).take(5);

        Observable.merge(letterSequence, numberSequence)
                .subscribe(new Observer<Serializable>() {
                    @Override
                    public void onCompleted() {
//                        System.exit(0);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Error:" + e.getMessage());
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(Serializable serializable) {
//                        System.out.print(serializable.toString()+" ");
                        Log.d(TAG, "testMerge---onNext: serializable=" + serializable.toString());
                    }
                });
    }

    private void testinterval() {
        rxContent.setText("");

        letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};

        Observable.interval(2000,TimeUnit.MILLISECONDS).take(10).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "testinterval--onNext: onCompleted----");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "testinterval--onError: ----e=" + e);
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "testinterval--onNext: aLong=" + aLong + "  letters=" + letters[(int) (aLong % letters.length)]);
            }
        });

    }

    private void testZip() {
        Observable<String> letterSequence = Observable.interval(2000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, String>() {
                    @Override
                    public String call(Long position) {
                        return letters[position.intValue()];
                    }
                }).take(letters.length);

        Observable<Long> numberSequence = Observable.interval(3000, TimeUnit.MILLISECONDS).take(6);

        Observable.zip(letterSequence, numberSequence, new Func2<String, Long, String>() {
            @Override
            public String call(String letter, Long number) {
                return letter + number;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "testZip----onNext: e=" + e);
            }

            @Override
            public void onNext(String result) {
                Log.d(TAG, "testZip----onNext: result=" + result);
            }
        });
    }


}
