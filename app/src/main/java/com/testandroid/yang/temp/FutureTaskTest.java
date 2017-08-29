package com.testandroid.yang.temp;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 并发，AccountManage
 * Created by yangjiajia on 2017/8/29.
 */

public class FutureTaskTest extends FutureTask<Bundle> {

    public FutureTaskTest(@NonNull Callable<Bundle> callable) {
        super(callable);
    }

    @Override
    protected void set(Bundle bundle) {
        super.set(bundle);
    }

    @Override
    protected void done() {
        super.done();
    }
}
