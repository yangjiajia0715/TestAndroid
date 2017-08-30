package com.testandroid.yang.common;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Keep;

/**
 * 课程
 * Created by yangjiajia on 2016/11/25 0025.
 */

@Entity
@Keep
public class Course {

    public String name;

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
