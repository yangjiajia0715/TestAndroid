package com.testandroid.yang.common;

import java.io.Serializable;

/**
 * Created by yangjiajia on 2017/8/2.
 */

public class ATestReportItem implements Serializable{
    public String name;
    public String category;

    public ATestReportItem(String name, String category) {
        this.name = name;
        this.category = category;
    }
}
