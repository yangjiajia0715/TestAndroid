package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.observer.SchedulerTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 再次学习Rxjava
 * Created by yangjiajia on 2017/8/13.
 * last time:{@link RxJava2Activity}
 *
 * @since 2.0
 */

public class Rxjava2NewActivity extends BaseActivity {
    private static final String TAG = "Rxjava2NewActivity";

    @BindView(R.id.rxjava2_new_0)
    TextView rxjava2New0;
    @BindView(R.id.rxjava2_new_1)
    TextView rxjava2New1;
    @BindView(R.id.rxjava2_new_2)
    TextView rxjava2New2;
    @BindView(R.id.rxjava2_new_3)
    TextView rxjava2New3;
    @BindView(R.id.rxjava2_new_4)
    TextView rxjava2New4;
    @BindView(R.id.rxjava2_new_5)
    TextView rxjava2New5;
    @BindView(R.id.rxjava2_new_6)
    TextView rxjava2New6;
    @BindView(R.id.rxjava2_new_7)
    TextView rxjava2New7;
    @BindView(R.id.rxjava2_new_8)
    TextView rxjava2New8;
    @BindView(R.id.rxjava2_new_9)
    TextView rxjava2New9;

    public static void start(Context context) {
        Intent starter = new Intent(context, Rxjava2NewActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2_new);
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

    @OnClick({R.id.rxjava2_new_0, R.id.rxjava2_new_1, R.id.rxjava2_new_2, R.id.rxjava2_new_3,
            R.id.rxjava2_new_4, R.id.rxjava2_new_5, R.id.rxjava2_new_6, R.id.rxjava2_new_7,
            R.id.rxjava2_new_8, R.id.rxjava2_new_9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rxjava2_new_0:
                testNew0();
                break;
            case R.id.rxjava2_new_1:
                testNew1();
                break;
            case R.id.rxjava2_new_2:
                testNew2();
                break;
            case R.id.rxjava2_new_3:
                testNew3();
                break;
            case R.id.rxjava2_new_4:
                testNew4();
                break;
            case R.id.rxjava2_new_5:
                testNew5();
                break;
            case R.id.rxjava2_new_6:
                break;
            case R.id.rxjava2_new_7:
                break;
            case R.id.rxjava2_new_8:
                break;
            case R.id.rxjava2_new_9:
                break;
        }
    }

    private void testNew5() {
        List<String> dates = new ArrayList<>();
        dates.add("aaa");
        dates.add("bbb");
        dates.add("ccc");
        Observable.fromIterable(dates)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.d(TAG, "accept: s=" + s + ",currentThread=" + Thread.currentThread().getName());
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        Log.d(TAG, "accept: disposable " + disposable + ",currentThread=" + Thread.currentThread().getName());
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.d(TAG, "accept:subscribe---s=" + s + ",currentThread=" + Thread.currentThread().getName());
                    }
                });
    }

    private void testNew4() {
        List<String> dates = new ArrayList<>();
        dates.add("aaa");
        dates.add("bbb");
        dates.add("ccc");

//        new ObservableTransformer<String, String>() {
//            @Override
//            public ObservableSource<String> apply(Observable<String> upstream) {
//                return null;
//            }
//        };
        Observable.fromIterable(dates)
                .compose(new ObservableTransformer<String, String>() {
                    @Override
                    public ObservableSource<String> apply(Observable<String> upstream) {
                        return null;
                    }
                });

        Observable.fromIterable(dates)
                .compose(new SchedulerTransform());
    }


    //lift 有点像代理，知道它的原理就行，rxjava不建议自定操作符来使用list，
    // 而是建议使用它的包装方法map,flatmap进行组合实现需求，
//    因为直接用lift容易出现难以发现的错误
    private void testNew3() {
        List<String> dates = new ArrayList<>();
        dates.add("aaa");
        dates.add("bbb");
        dates.add("ccc");
        Observable.fromIterable(dates)
                .lift(new ObservableOperator<Integer, String>() {

                    @Override
                    public Observer<? super String> apply(final Observer<? super Integer> observer) throws Exception {
                        return new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String s) {
                                Log.d(TAG, "lift-----onNext: sss" + s);
                                observer.onNext(s.length());
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "lift-----onError: e" + e);
                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG, "lift-----onComplete");
                            }
                        };
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "----onNext---integer=" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "----onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "----onComplete");
                    }
                });
    }

    private void testNew2() {
        List<String> dates = new ArrayList<>();
        dates.add("aaa");
        dates.add("bbb");
        dates.add("ccc");
        Observable.fromIterable(dates)
                .map(new Function<String, Integer>() {

                    @Override
                    public Integer apply(@NonNull String s) throws Exception {
                        return (int) s.charAt(0);
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<String>>() {//处理回调，uri图片上传
                    @Override
                    public ObservableSource<String> apply(@NonNull final Integer integer) throws Exception {
                        Observable<String> observable = new Observable<String>() {

                            @Override
                            protected void subscribeActual(Observer<? super String> observer) {
                                observer.onNext("hhh" + integer);
                                observer.onComplete();
                            }
                        };
                        return observable;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.d(TAG, "accept: ssss=" + s);
                    }
                });

    }

    private void testNew1() {
        List<String> dates = new ArrayList<>();
        dates.add("aaa");
        dates.add("bbb");
        dates.add("ccc");
        //只指定：subscribeOn:observeOn 在同一个线程中
        //只指定：observeOn:Obserer在指定的线程中执行

//         .subscribeOn(Schedulers.newThread())
        Observable.fromIterable(dates)
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: " + ",getName=" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: s=" + s + ",getName=" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    private void testNew0() {
        Observable<String> observable = new Observable<String>() {

            @Override
            protected void subscribeActual(Observer<? super String> observer) {
                Log.d(TAG, "subscribeActual:发射： aaa  getName=" + Thread.currentThread().getName());
                observer.onNext("aaa");
                Log.d(TAG, "subscribeActual: 发射： bbb");
                observer.onNext("bbb");
                Log.d(TAG, "subscribeActual: 发射： ccc");
                observer.onNext("ccc");
            }
        };

        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: d=" + d + ",getName=" + Thread.currentThread().getName());
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: s=" + s + ",getName=" + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: e=" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };

        observable.subscribeOn(Schedulers.io());
        observable.subscribe(observer);
    }
}
