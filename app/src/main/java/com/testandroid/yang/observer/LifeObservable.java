package com.testandroid.yang.observer;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by yangjiajia on 2017/5/15.
 */

public class LifeObservable<T> extends Observable<T> {

    @Override
    protected void subscribeActual(Observer<? super T> observer) {

    }
}
