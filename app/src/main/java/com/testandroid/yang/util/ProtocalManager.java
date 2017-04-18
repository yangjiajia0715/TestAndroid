package com.testandroid.yang.util;

/**
 * Created by yangjiajia on 2017/4/1 0001.
 */

public class ProtocalManager {

    public static String addressPrefix = "http://101.200.163.38";//在build.gradle中配置

    /**
     * 获取答疑广场
     */
    public static final String GET_ANSWER_SQUARE_LIST = addressPrefix + "/LoginServer/aq/teacher/findAll.json";

    /**
     * 微课
     */
    public static final String GET_MICRO_COURSE = addressPrefix + "/LoginServer/aq/teacher/findAll.json/LoginServer/px.json";
}
