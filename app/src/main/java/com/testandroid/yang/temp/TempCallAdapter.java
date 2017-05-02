package com.testandroid.yang.temp;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;

/**
 * TempCallAdapter
 * Created by yangjiajia on 2017/5/2 0002.
 */

public class TempCallAdapter implements CallAdapter {

    @Override
    public Type responseType() {
        return null;
    }

    @Override
    public Object adapt(Call call) {
        return null;
    }
}
