package com.testandroid.yang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import com.testandroid.yang.R;
import com.testandroid.yang.common.Course;
import com.testandroid.yang.common.Student2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static android.R.attr.name;

/**
 * RxJavaDatabaseActivity,模拟网络本地数据库
 * Created by yangjiajia on 2016/11/23 0023.
 */

public class RxJavaDatabaseActivity extends Activity implements View.OnClickListener {

    private Observable.OnSubscribe<String> onSubscribe;
    private Subscriber<String> subscriber;
    private static final String TAG = "RxJavaActivity";
    private Subscription subscribe;

    public static void start(Context context) {
        Intent starter = new Intent(context, RxJavaDatabaseActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_database);

        Log.d(TAG, "--RxJavaActivity--onCreate: ");

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

                break;
            case R.id.tv_test_RxJava1:
                Observable<String> stringObservable = Observable.create(onSubscribe);
                stringObservable.subscribe(subscriber);
                break;
            case R.id.tv_test_RxJava2:
                testOnSubcribe();
                break;
            case R.id.tv_test_RxJava3:
                testAction1();
                break;
            case R.id.tv_test_RxJava4:
                testSchedulers();
                break;
            case R.id.tv_test_RxJava5:
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
        }
    }

    private void testSingle() {
        Single<Integer> just = Single.just(100);
        just.timeout(100, TimeUnit.MILLISECONDS);
        just.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "testSingle--call: integer=" + integer);
            }
        });

        //test subcribe again
        just.subscribe(new SingleSubscriber<Integer>() {
            @Override
            public void onSuccess(Integer value) {
                Log.d(TAG, "testSingle--call: value=" + value);

            }

            @Override
            public void onError(Throwable error) {
                Log.d(TAG, "testSingle--call: error=" + error);

            }
        });


    }

    private void testRx8() {
//        Observable.from();

        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {

            }
        };

        subscribe = Observable.just(1, 2)
                .observeOn(Schedulers.newThread())
                .subscribe(subscriber);

//        Observable.from(new String[]{})
//        subscribe.unsubscribe();

//        Future
//        Subject.

//        Single<Integer> just = Single.just(100);
//        just.
    }

    private void testFlatMap() {
        Student2[] students = getStudents(10);
        Subscriber<Course> subscriber = new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                String name = Thread.currentThread().getName();
                Log.d(TAG, "--testFlatMap--onNext: course=" + course.getName() + ",currentThread=" + name);
            }
        };

        Observable.from(students).flatMap(new Func1<Student2, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student2 student) {
                List<Course> courses = student.getCourses();
                Log.d(TAG, "testFlatMap--call: currentThread=" + Thread.currentThread().getName());
                return Observable.from(courses);
            }
        }).subscribeOn(Schedulers.newThread()).subscribe(subscriber);
    }

    private void testMap() {
        Student2[] students = getStudents(10);

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "--testMap--onError: onCompleted =");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "--testMap--onError: stu e =" + e);
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "--testMap--onNext: stu name =" + name);
            }
        };

        Observable.from(students).map(new Func1<Student2, String>() {
            @Override
            public String call(Student2 student) {
                return student.getName();
            }
        }).subscribe(subscriber);

    }

    private Student2[] getStudents(int count) {
        Student2[] students = new Student2[count];
        Log.d(TAG, "getStudents: stu cnt =" + students.length);
        for (int i = 0; i < students.length; i++) {
            Log.d(TAG, "getStudents: stu  " + i + " =" + students[0]);
            students[i] = new Student2("张三", 10 + i);

            students[i].addCourses(new Course("语文 " + students[i].getName()));
            students[i].addCourses(new Course("数学 " + students[i].getName()));
            students[i].addCourses(new Course("英语 " + students[i].getName()));
        }
        return students;

    }

    private void testOnSubcribe() {
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
    }

    private void testAction1() {
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
    }

    private void testSchedulers() {
//        Observable<Integer> just = Observable.just(1, 2);
        Observable.OnSubscribe<Integer> integerOnSubscribe = new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                String name = Thread.currentThread().getName();
                Log.d(TAG, "--计划表-1-当前线程的名字 call: name = " + name);
                subscriber.onNext(1);
                SystemClock.sleep(1000);

                Log.d(TAG, "--计划表-2-当前线程的名字 call: name = " + name);
                subscriber.onNext(2);
                SystemClock.sleep(2000);
                Log.d(TAG, "--计划表-3-当前线程的名字 call: name = " + name);
                subscriber.onNext(3);
                SystemClock.sleep(3000);
                Log.d(TAG, "--计划表-4-当前线程的名字 call: name = " + name);
                subscriber.onNext(4);
                SystemClock.sleep(4000);
                Log.d(TAG, "--计划表-5-当前线程的名字 call: name = " + name);
                subscriber.onCompleted();
            }
        };

        Observable<Integer> just = Observable.create(integerOnSubscribe);
        just.subscribeOn(Schedulers.io());
        just.observeOn(AndroidSchedulers.mainThread());
        just.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "--观察者-当前线程的名字 call: name = " + name);
                Log.d(TAG, "--观察者-----Action1--call: integer=" + integer);
            }
        });
    }
}
