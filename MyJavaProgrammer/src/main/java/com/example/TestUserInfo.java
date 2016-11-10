package com.example;

/**
 * Created by yangjiajia on 2016/11/10 0010.
 */

public class TestUserInfo {
    public String name;
    public int age;

    public TestUserInfo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestUserInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
