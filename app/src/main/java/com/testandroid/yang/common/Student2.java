package com.testandroid.yang.common;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;


/**
 * 学生信息
 * Created by yangjiajia on 2016/11/25 0025.
 */

public class Student2 extends BaseObservable implements BaseInfo {
    private String name;

    public int age;

    public String firtName;

    public String lastName;

    private List<Course> courses;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirtName() {
        return firtName;
    }

    public void setFirtName(String firtName) {
        this.firtName = firtName;
    }

    public boolean getFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }

    private boolean friend;


    public List<Course> getCourses() {
        return courses;
    }

    public void addCourses(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

    public void delCourses(Course course) {
        courses.remove(course);
    }

    public Student2(String name, int age) {
        this.name = name;
        this.age = age;
        courses = new ArrayList<>();
    }

    @Bindable
    public String getName() {
        return "getName=" + name;
    }

    public String name() {
        return "name=" + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
//        com.example.testandroid.BR
//        notifyPropertyChanged(BR.age);
    }

    @Override
    public String toString() {
        return "Student2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", firtName='" + firtName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", courses=" + courses +
                ", friend=" + friend +
                '}';
    }
}
