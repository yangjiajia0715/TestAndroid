package com.testandroid.yang.common;

/**
 * Created by yangjiajia on 2017/1/17 0017.
 */

public class HomeInfo implements BaseInfo {
    public String content;
    public int id;
    public HomeGroup group;

    public HomeInfo(String content, int id) {
        this.content = content;
        this.id = id;
    }

    public HomeInfo(String content, int id, HomeGroup group) {
        this.content = content;
        this.id = id;
        this.group = group;
    }

    public enum HomeGroup {
        View, Animator, NewTech, DataBase, Other
    }
}
