package com.testandroid.yang.temp;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * TmpCallAdapterFactory
 * Created by yangjiajia on 2017/5/2 0002.
 */

public class TmpCallAdapterFactory extends CallAdapter.Factory {

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {

        return null;
    }
}
