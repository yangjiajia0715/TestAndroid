package com.testandroid.yang.extenddd;

/**
 * Created by yangjiajia on 2017/5/3 0003.
 */

public class Plate<T> {
    private T item;

    public Plate(T t) {
        item = t;
    }

    public void set(T t) {
        item = t;
    }

    public T get() {
        return item;
    }
}
