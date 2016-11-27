package com.example.testandroid.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生信息
 * Created by yangjiajia on 2016/11/25 0025.
 */

public class Student implements BaseInfo {
    private String name;
    private int age;
    private List<Course> courses;

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

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        courses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
