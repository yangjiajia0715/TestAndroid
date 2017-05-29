package com.testandroid.yang.common;

import java.io.Serializable;

/**
 * User
 * Created by yangjiajia on 2017/1/10 0010.
 */

public class User implements Serializable{

    public String name;
    public int age;
    public String phoneNumber;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
