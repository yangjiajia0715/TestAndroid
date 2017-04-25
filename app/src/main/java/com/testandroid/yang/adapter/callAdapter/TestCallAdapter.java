package com.testandroid.yang.adapter.callAdapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * TestCallAdapter
 * Created by yangjiajia on 2017/4/25 0025.
 */

public class TestCallAdapter extends CallAdapter.Factory {

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return null;
    }
}
