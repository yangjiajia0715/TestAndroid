package com.testandroid.yang.common;

import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * User
 * Created by yangjiajia on 2017/1/10 0010.
 */
@Entity
public class User implements Serializable{
    @Id
    public long id;

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
