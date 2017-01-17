package com.testandroid.yang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.testandroid.yang.R;
import com.testandroid.yang.common.Fields;
import com.testandroid.yang.common.MyCLickHandler;
import com.testandroid.yang.common.Student2;
import com.testandroid.yang.common.User;
import com.testandroid.yang.databinding.ActivityDataBindingBinding;

/**
 * test MVVP
 * Created by yangjiajia on 2017/1/9 0009.
 */

public class DataBindingActivity extends Activity {

    private static final String TAG = "DataBindingActivity";
    private Student2 student;
    private User user;
    private int index;
    private ActivityDataBindingBinding  dataBinding;

    public static void start(Context context) {
        Intent intent = new Intent(context, DataBindingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
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
                dataBinding.setStu(student);
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

        final ObservableArrayMap<String, Object> teacher = new ObservableArrayMap<>();
        teacher.put("firstName", "Google");
        teacher.put("lastName", "Inc.");
        teacher.put("age", 17);

        findViewById(R.id.change3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacher.put("firstName", "ObservableArrayMap" + System.currentTimeMillis());
            }
        });

        dataBinding.setTeacher(teacher);


        final ObservableArrayList<String> master = new ObservableArrayList();
        master.add("张三");
        master.add("李四");
        master.add("王五");

        dataBinding.setMaster(master);

        findViewById(R.id.change4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                master.add(Fields.FIRST, "赵六" + System.currentTimeMillis());
            }
        });

        dataBinding.firstName.setText("呵呵哈哈哈");
        dataBinding.firstName2.setText("哈哈哈哈军军军军");

    }

}
