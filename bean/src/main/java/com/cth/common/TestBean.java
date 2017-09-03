package com.cth.common;

import java.io.Serializable;

/**
 * first class
 * Created by yangjiajia on 2017/9/3.
 */

public class TestBean implements Serializable {
    private String userName;
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
