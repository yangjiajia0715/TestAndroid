package com.testandroid.yang.observer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yangjiajia on 2017/8/13.
 */

public class SchedulerTransform implements ObservableTransformer {

    @Override
    public ObservableSource apply(Observable upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
