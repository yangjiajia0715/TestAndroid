package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.sql.Timestamp;
import java.util.stream.Stream;

/**
 * 记录一个特殊类
 *
 * @author yangjiajia
 * @date 2018/5/21
 */
public class ClassNameActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, ClassNameActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        //2018-5-22 19:54:42 tag
    }

    @Override
    public void initView() {
        Timestamp timestamp;
        Stream stream;

//        Stream.Builder()
//        new Stream.Builder<>();
        String[] arr = {"a","c","d"};
//        Arrays.stream(arr);
    }

    @Override
    public void initData() {

        //2018年5月22日21:14:54 git co v0.2.2
    }
}
