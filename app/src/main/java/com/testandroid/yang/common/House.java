package com.testandroid.yang.common;

import android.util.Log;

import java.util.List;
import java.util.Map;

/**
 * House
 * Created by Administrator on 2017/3/13 0013.
 */

public class House {
    private static final String TAG = "House";
    private String name;
    private String desc;

    private List<? extends Runnable> runn(){
        return null;
    }

    private Map<String, ? super Runnable> mappp(){
        return null;
    }

    private void aa(String str) {
        System.out.print("----aa-----------------str=" + str);
        Log.d(TAG, "aa: str=" + str);
    }

    private void bb() {

    }

    protected void cc() {

    }

    void dd() {

    }

    public House() {
    }

    public House(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
