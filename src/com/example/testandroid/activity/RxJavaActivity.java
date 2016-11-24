package com.example.testandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.testandroid.R;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Action2;

/**
 * RxJava
 * Created by yangjiajia on 2016/11/23 0023.
 */

public class RxJavaActivity extends Activity implements View.OnClickListener {

    private Observable.OnSubscribe<String> onSubscribe;
    private Subscriber<String> subscriber;
    private static final String TAG = "RxJavaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        Log.d(TAG, "--RxJavaActivity--onCreate: ");

        findViewById(R.id.tv_test_RxJava).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava1).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava2).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava3).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava4).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava5).setOnClickListener(this);

        onSubscribe = new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.d(TAG, "call: ");
                subscriber.onNext("oncreate--a");
                subscriber.onNext("oncreate--b");
                subscriber.onNext("oncreate--c");
                subscriber.onCompleted();
            }
        };

        subscriber = new Subscriber<String>() {

            @Override
            public void onStart() {
                super.onStart();
                Log.d(TAG, "onStart: ");
            }

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: e=" + e);
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: s=" + s);
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_test_RxJava:
//                String[] folders = {};
//                Observable.from(folders)
//                        .flatMap(new Func1<String, Observable<File>>() {
//                            @Override
//                            public Observable<File> call(String s) {
//                                return null;
//                            }
//                        })
//                        .filter(new Func1<File, Boolean>() {
//                            @Override
//                            public Boolean call(File file) {
//                                return file.getName().endsWith(".png");
//                            }
//                        })
//                        .map(new Func1<File, Bitmap>() {
//                            @Override
//                            public Bitmap call(File file) {
//                                return null;
//                            }
//                        })
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Action1<Bitmap>() {
//                            @Override
//                            public void call(Bitmap bitmap) {
//
//                            }
//                        });

                break;
            case R.id.tv_test_RxJava1:
                Observable<String> stringObservable = Observable.create(onSubscribe);
                stringObservable.subscribe(subscriber);
                break;
            case R.id.tv_test_RxJava2:
                onSubscribe = new Observable.OnSubscribe<String>() {

                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        Log.d(TAG, "call: ");
                        subscriber.onNext("111");
                        subscriber.onNext("222");
                        subscriber.onNext("333");
                        try {
                            int result = 9 / 0;
                        } catch (Exception e) {
                            e.printStackTrace();
                            subscriber.onError(e);
                        }
                    }
                };

                Observable<String> stringObservable1 = Observable.create(onSubscribe);
                stringObservable1.subscribe(subscriber);
                subscriber.unsubscribe();
                break;
            case R.id.tv_test_RxJava3:
                Action1<String> action1 = new Action1<String>() {

                    @Override
                    public void call(String s) {
                        Log.d(TAG, "Action1---call: s=" + s);
                    }
                };

                Action2<String, String> action2 = new Action2<String, String>() {

                    @Override
                    public void call(String s, String s2) {
                        Log.d(TAG, "Action2---call: s=" + s + ",s2=" + s2);
                    }
                };

                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("action1--onNext--arg");
                    }
                }).subscribe(action1);

                break;
            case R.id.tv_test_RxJava4:

                break;
            case R.id.tv_test_RxJava5:

                Subscriber<String> stringSubscriber = new Subscriber<String>() {
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

                stringSubscriber.onStart();
                stringSubscriber.isUnsubscribed();
                stringSubscriber.unsubscribe();
                break;
        }
    }
}
