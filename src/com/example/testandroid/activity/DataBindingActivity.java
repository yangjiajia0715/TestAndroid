package com.example.testandroid.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.testandroid.CCCTest;
import com.example.testandroid.R;
import com.example.testandroid.common.MyCLickHandler;
import com.example.testandroid.common.Student2;
import com.example.testandroid.common.User;

/**
 * test MVVP
 * Created by yangjiajia on 2017/1/9 0009.
 */

public class DataBindingActivity extends Activity {

    private static final String TAG = "DataBindingActivity";
    private Student2 student;
    private CCCTest dataBinding;
    private User user;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_data_binding);

//        ActivityDataBindingBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

//        ActivityDataBindingBinding inflate = ActivityDataBindingBinding.inflate(getLayoutInflater());

        Log.d(TAG, "onCreate: dataBinding=" + dataBinding);
//        Log.d(TAG, "onCreate:     inflate=" + inflate);
        student = new Student2("张三", 18);
        student.lastName = "张三丰";

        Log.d(TAG, "onCreate: getFirtName=" + student.getFirtName());
        Log.d(TAG, "onCreate: student=" + student);

        dataBinding.setStu(student);
        dataBinding.setHandlers(new MyCLickHandler());
        findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.setAge(student.getAge() + 2);
                student.setName(student.getName() + student.getAge());
//                dataBinding.setStu(student);
                Log.d(TAG, "onClick: student=" + student);
            }
        });

        user = new User();
        user.firstName.set("firstname");
        dataBinding.setUser(user);

        index = 1;
        findViewById(R.id.change2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.firstName.set(user.firstName.get() + index++);
                Log.d(TAG, "onClick user: user=" + user);
            }
        });
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, DataBindingActivity.class);
        context.startActivity(intent);
    }

}
