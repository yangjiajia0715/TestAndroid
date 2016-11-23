package com.example.testandroid.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.example.testandroid.R;

import java.io.File;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * RxJava
 * Created by yangjiajia on 2016/11/23 0023.
 */

public class RxJavaActivity extends Activity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_rx_java);
        findViewById(R.id.tv_test_RxJava).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava1).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava2).setOnClickListener(this);
        findViewById(R.id.tv_test_RxJava3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_test_RxJava:
                String[] folders = {};
                Observable.from(folders)
                        .flatMap(new Func1<String, Observable<File>>() {
                            @Override
                            public Observable<File> call(String s) {
                                return null;
                            }
                        })
                        .filter(new Func1<File, Boolean>() {
                            @Override
                            public Boolean call(File file) {
                                return file.getName().endsWith(".png");
                            }
                        })
                        .map(new Func1<File, Bitmap>() {
                            @Override
                            public Bitmap call(File file) {
                                return null;
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Bitmap>() {
                            @Override
                            public void call(Bitmap bitmap) {

                            }
                        });

                break;
            case R.id.tv_test_RxJava1:

                Observer<String> observer = new Observer<String>() {
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

                break;
            case R.id.tv_test_RxJava2:

                break;
            case R.id.tv_test_RxJava3:

                break;
        }
    }
}
