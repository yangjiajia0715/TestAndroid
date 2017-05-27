package com.testandroid.yang.common;

/**
 * User
 * Created by yangjiajia on 2017/1/10 0010.
 */

public class User {

    public String name;
    public int age;
    public int sex;
    public String phoneNumber;
    public String address;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
