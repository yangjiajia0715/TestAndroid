package com.testandroid.yang.extenddd;

import com.testandroid.yang.common.User;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * TestType
 * Created by yangjiajia on 2017/5/4 0004.
 */

public class TestType<K extends Comparable & Serializable, V> {
    Map<String,User> userMap;
    K key;
    V value;

    public void show(List<String>[] pTypeArray, V[] vTypeArray, List<String> list, String[] strings, int[] ints) {

    }

    //继教网老账号ID
//#define DWACCOUNT_USERID @"435D54363B37C983"
//咱们自己的新账号ID
//#define DWACCOUNT_USERID @"2BDF604C7486AE0F"
//继教网老账号apiKey
//#define DWACCOUNT_APIKEY @"iSgdPc2uGMhiml0UrdXh4AgS3AayonAv"
//咱们自己新账号的apiKey
//#define DWACCOUNT_APIKEY @"YUsABCcw7U4j0PfLpCEubg5tsq74ubzd"


}
