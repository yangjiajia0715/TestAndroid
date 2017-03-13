package com.testandroid.yang.common;

/**
 * Created by Administrator on 2017/3/13 0013.
 */

public class House {
    private String name;
    private String desc;

    public House() {
    }

    public House(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
