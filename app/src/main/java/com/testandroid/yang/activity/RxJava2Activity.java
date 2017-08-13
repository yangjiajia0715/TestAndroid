package com.testandroid.yang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.testandroid.yang.R;
import com.testandroid.yang.common.Course;
import com.testandroid.yang.common.HomeInfo;
import com.testandroid.yang.common.Student;
import com.testandroid.yang.common.User;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava
 * Created by yangjiajia on 2016/11/23 0023.
 * <p>
 * 2017-3-16 rxjava-->rxjava2
 */

public class RxJava2Activity extends Activity implements View.OnClickListener {

    private static final String TAG = "RxJava2Activity";

    public static void start(Context context) {
        Intent starter = new Intent(context, RxJava2Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        Log.d(TAG, "--RxJava2Activity--onCreate: ");

        findViewById(R.id.tv_test_RxJava).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava1).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava2).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava3).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava4).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava5).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava6).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava7).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava8).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava9).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava10).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava11).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava12).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_test_RxJava:
                Observable
                        .create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(ObservableEmitter<String> e) throws Exception {

                                for (int i = 0; i < 1000; i++) {
                                    e.onNext("错题会" + i);
                                }

                                Log.d(TAG, "RxJava2Activity--1--subscribe=" + Thread.currentThread().getName());
                                e.onComplete();
                            }
                        })
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                                Log.d(TAG, "RxJava2Activity--1--onSubscribe--d=" + d.toString());
                            }

                            @Override
                            public void onNext(String s) {
                                Log.d(TAG, "RxJava2Activity--1--onNext--s=" + s);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "RxJava2Activity--1--onError--e=" + e);
                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG, "RxJava2Activity--1--onComplete--");
                            }
                        });
                break;
            case R.id.tv_test_RxJava1:

                Observable.just(1, 2, 3, 4, 5, 6)
                        .map(new Function<Integer, String>() {
                            @Override
                            public String apply(@NonNull Integer integer) throws Exception {
//                                + ",currentThread=" + Thread.currentThread().getName()
                                Log.d(TAG, "RxJava2Activity--1--apply--currentThread=" + Thread.currentThread().getName());
                                return "错题会" + integer;
                            }
                        })
                        .observeOn(Schedulers.newThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String s) {
                                Log.d(TAG, "RxJava2Activity--1--onNext--s=" + s + ",currentThread=" + Thread.currentThread().getName());
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case R.id.tv_test_RxJava2:
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        Log.d(TAG, "emit 1");
                        emitter.onNext(1);
                        Log.d(TAG, "emit 2");
                        emitter.onNext(2);
                        Log.d(TAG, "emit 3");
                        emitter.onNext(3);
                        Log.d(TAG, "emit complete");
                        emitter.onComplete();
                        Log.d(TAG, "emit 4");
                        emitter.onNext(4);
                    }
                }).subscribe(new Observer<Integer>() {
                    private Disposable mDisposable;
                    private int i;

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "subscribe--d=" + d);
                        mDisposable = d;
                        Log.d(TAG, "subscribe-1-d=" + d);
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "onNext: " + value + "，mDisposable=" + mDisposable);
                        i++;
                        if (i == 2) {
                            Log.d(TAG, "dispose");
                            mDisposable.dispose();
                            Log.d(TAG, "isDisposed : " + mDisposable.isDisposed());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "error");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "complete");
                    }
                });
                break;
            case R.id.tv_test_RxJava3:
                Observable.create(new ObservableOnSubscribe<List<User>>() {

                    @Override
                    public void subscribe(ObservableEmitter<List<User>> e) throws Exception {

                    }
                });


                testAction1();
                break;
            case R.id.tv_test_RxJava4:
                List<String> strings = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    strings.add("错题会" + i);
                }
                Observable.fromIterable(strings)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String s) throws Exception {
                                //main each
                                Log.d(TAG, "RxJava2Activity--4--doOnNext--currentThread=" + Thread.currentThread().getName() + ",s=" + s);
                            }
                        })
                        .observeOn(Schedulers.io())
                        .map(new Function<String, Integer>() {
                            @Override
                            public Integer apply(@NonNull String s) throws Exception {
                                int anInt = Integer.parseInt(s.substring(s.length() - 1, s.length()));
                                //io each
                                Log.d(TAG, "RxJava2Activity--4--apply--currentThread=" + Thread.currentThread().getName() + ",anInt=" + anInt);
                                return anInt;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(@NonNull Integer integer) throws Exception {
                                //main io
                                Log.d(TAG, "RxJava2Activity--4--accept-main-currentThread=" + Thread.currentThread().getName() + ",integer=" + integer);
                            }
                        });
                break;
            case R.id.tv_test_RxJava5:
                List<String> arrayList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    arrayList.add("错题会" + i);
                }
                Observable<String> observable1 = Observable.fromIterable(arrayList);

                List<Integer> strings1 = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    strings1.add(i);
                }

//                .delay(2, TimeUnit.SECONDS)
                Observable<Integer> observable2 = Observable.fromIterable(strings1)
//                        .subscribeOn(AndroidSchedulers.mainThread())
//                        .observeOn(AndroidSchedulers.mainThread())
                        ;//注意：只要有延迟，就会放在子线程中 .delay(2, TimeUnit.SECONDS)

                observable2.subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "accept: zip---apply-666--integer=" + integer + ",integer=" + integer + ",currentThread=" + Thread.currentThread().getName());
                    }
                });

                Observable
                        .zip(observable1, observable2, new BiFunction<String, Integer, HomeInfo>() {
                            @Override
                            public HomeInfo apply(@NonNull String s, @NonNull Integer integer) throws Exception {
                                HomeInfo info = new HomeInfo(s, integer);
                                Log.d(TAG, "accept: zip---apply---s=" + s + ",integer=" + integer + ",currentThread=" + Thread.currentThread().getName());
                                return info;
                            }
                        })
                        .subscribe(new Consumer<HomeInfo>() {
                            @Override
                            public void accept(@NonNull HomeInfo homeInfo) throws Exception {
                                Log.d(TAG, "accept: zip---result---homeInfo=" + homeInfo + ",currentThread=" + Thread.currentThread().getName());
                            }
                        });


//                Observable.zip()
                break;
            case R.id.tv_test_RxJava6:
                testMap();
            case R.id.tv_test_RxJava7:
                testFlatMap();
                break;
            case R.id.tv_test_RxJava8:
                testRx8();
                break;
            case R.id.tv_test_RxJava9:
                testSingle();
                break;
            case R.id.tv_test_RxJava10:
                testRxLifeCircle();
                break;
            case R.id.tv_test_RxJava11:
                testOprerate();
                break;
            case R.id.tv_test_RxJava12:
                break;
        }
    }

    private void testOprerate() {
        //operate1 :takeUtil
        Observable<Long> observable = Observable.interval(3, TimeUnit.SECONDS);

        Observable.interval(1, TimeUnit.SECONDS)
                .takeUntil(observable)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "testOprerate--takeUtil--onNext--aLong: " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "testOprerate--takeUtil--onComplete--");


                    }
                });


    }

    private void testRxLifeCircle() {

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "testRxLifeCircle-1-subscribe: ");
                e.onNext(111);
                e.onNext(222);
                e.onNext(333);
                e.onNext(4);
                e.onNext(5);
                e.onComplete();
                Log.d(TAG, "testRxLifeCircle-1-subscribe: end");
            }
        });

        LifecycleTransformer<Object> transformer = RxLifecycle.bind(observable);
//        LifecycleTransformer<Object> bindUntilEvent = RxLifecycle.bindUntilEvent(observable, ActivityEvent.DESTROY);

//        RxLifecycleAndroid.bindActivity()


        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        Log.d(TAG, "testRxLifeCircle-2-subscribe: ");
                        e.onNext("数据1");
                        e.onNext("数据2");
                        e.onNext("数据3");
                        e.onNext("数据4");
                        e.onComplete();
                        Log.d(TAG, "testRxLifeCircle-2-subscribe: end");

                    }
                })
                .compose(transformer)
                .subscribe();
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        Log.d(TAG, "onNext: s=" + s);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    private void testSingle() {


    }

    private void testRx8() {
    }

    private void testFlatMap() {

    }

    private void testMap() {

    }

    private Student[] getStudents(int count) {
        Student[] students = new Student[count];
        Log.d(TAG, "getStudents: stu cnt =" + students.length);
        for (int i = 0; i < students.length; i++) {
            Log.d(TAG, "getStudents: stu  " + i + " =" + students[0]);
            students[i] = new Student("张三", 10 + i);

            students[i].addCourses(new Course("语文 " + students[i].getName()));
            students[i].addCourses(new Course("数学 " + students[i].getName()));
            students[i].addCourses(new Course("英语 " + students[i].getName()));
        }
        return students;

    }

    private void testOnSubcribe() {
    }

    private void testAction1() {
    }

    private void testSchedulers() {
    }
}
