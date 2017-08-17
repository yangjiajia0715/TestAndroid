package com.testandroid.yang.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.observer.SchedulerTransform;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableOperator;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.GroupedObservable;
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
    private Handler mHandler = new Handler();

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
                testNew6();
                break;
            case R.id.rxjava2_new_7:
                testNew7();
                break;
            case R.id.rxjava2_new_8:
                break;
            case R.id.rxjava2_new_9:
                AccountManager accountManager = AccountManager.get(this);
                Account[] accounts = accountManager.getAccounts();
//                accountManager.invalidateAuthToken("","");
//                accountManager.getAuthToken(accounts[0], "", null, this, new AccountManagerCallback<Bundle>() {
//                    @Override
//                    public void run(AccountManagerFuture<Bundle> future) {
//
//                    }
//                },mHandler);
                for (Account account : accounts) {
                    Log.d(TAG, "onViewClicked: account=" + account);
                    Log.d(TAG, "onViewClicked: name account=" + account.name);
                    Log.d(TAG, "onViewClicked: type account=" + account.type);
                }
                break;
        }
    }

    private void testNew7() {
        List<String> dates = new ArrayList<>();
        dates.add("aaa");
        dates.add("bbb");
        dates.add("ccc");
        dates.add("a");
        dates.add("b");
        dates.add("c");
        dates.add("aaaaaa");
        Observable<GroupedObservable<Integer, String>> groupBy = Observable.fromIterable(dates)
                .groupBy(new Function<String, Integer>() {
                    @Override
                    public Integer apply(@NonNull String s) throws Exception {
                        Log.d(TAG, "length=" + s.length());
                        return s.length();
                    }
                });

        groupBy.flatMap(new Function<GroupedObservable<Integer, String>, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull GroupedObservable<Integer, String> groupedObservable) throws Exception {
                Integer key = groupedObservable.getKey();
                return groupedObservable;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });
        Observable.concat(groupBy)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "accept: s=" + s);
                    }
                });

//        Observable.just(1, 2, 3, 4)
//                .scan(new BiFunction<Integer, Integer, Integer>() {
//                    @Override
//                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
//                        Log.d(TAG, "apply: integer=" + integer);
//                        Log.d(TAG, "apply: integer2=" + integer2);
//                        return integer + integer2;
//                    }
//                });

        Observable<String> observable = Observable.fromIterable(dates);
        observable.switchMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(@NonNull String s) throws Exception {
                return null;
            }
        });
        observable.concatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(@NonNull String s) throws Exception {
                return null;
            }
        });

        observable.flatMapIterable(new Function<String, Iterable<Integer>>() {
            @Override
            public Iterable<Integer> apply(@NonNull String s) throws Exception {
                return null;
            }
        });


    }

    private void testNew6() {
        List<String> dates = new ArrayList<>();
        dates.add("aaa");
        dates.add("bbb");
        dates.add("ccc");
        Observable.fromIterable(dates)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        return false;
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Flowable.interval(1, TimeUnit.MILLISECONDS);

        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> e) throws Exception {
                long requested = e.requested();

            }
        }, BackpressureStrategy.BUFFER)
                .subscribe(new FlowableSubscriber<String>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription s) {
                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void testNew5() {
        List<String> dates = new ArrayList<>();
        dates.add("aaa");
        dates.add("bbb");
        dates.add("ccc");

        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                Log.d(TAG, "observable1----subscribe:emitter: aa" + "currentThread=" + Thread.currentThread().getName());
                e.onNext("a");
                Thread.sleep(1000);

                Log.d(TAG, "observable1----subscribe:emitter: bb");
                Thread.sleep(1000);

                e.onNext("bb");
                Thread.sleep(1000);

                Log.d(TAG, "observable1----subscribe:emitter: cc");
                Thread.sleep(1000);
                e.onNext("ccc");

                Thread.sleep(1000);
                Log.d(TAG, "observable1----subscribe:emitter: dddd");
                e.onNext("dddd");
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                Log.d(TAG, "observable2----subscribe:emitter: 1 currentThread=" + Thread.currentThread().getName());
                e.onNext("1");
                Log.d(TAG, "observable2----subscribe:emitter: 22");
                e.onNext("22");
                Log.d(TAG, "observable2----subscribe:emitter: 333");
                e.onNext("333");
            }
        }).subscribeOn(Schedulers.io());

        Observable
                .zip(observable1, observable2, new BiFunction<String, String, Long>() {
                    @Override
                    public Long apply(@NonNull String s, @NonNull String s2) throws Exception {
                        long length = s.length();
                        long length2 = s2.length();
                        return length + length2;
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "accept---aLong=" + aLong + "currentThread=" + Thread.currentThread().getName());
                    }
                });
//        Observable.fromIterable(dates)
//                .flatMap(new Function<String, ObservableSource<Integer>>() {
//                    @Override
//                    public ObservableSource<Integer> apply(@NonNull String s) throws Exception {
//
//                        return null;
//                    }
//                })
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//
//                    }
//                });

//        new Observable<String>() {
//            @Override
//            protected void subscribeActual(Observer<? super String> observer) {
//                Log.d(TAG, "ggggg-subscribe:currentThread= " + Thread.currentThread().getName());
//                observer.onNext("1111");
//                observer.onComplete();
//
//            }
//        }.subscribeOn(Schedulers.io())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        Log.d(TAG, "gggggg-accept:currentThread= " + Thread.currentThread().getName());
//                    }
//                });
//
//        Observable
//                .create(new ObservableOnSubscribe<String>() {
//                    @Override
//                    public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
//                        Log.d(TAG, "subscribe:currentThread= " + Thread.currentThread().getName());
//                        e.onNext("1111");
//                        e.isDisposed();
//                        e.onComplete();
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        Log.d(TAG, "accept:currentThread= " + Thread.currentThread().getName());
//                    }
//                });

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
