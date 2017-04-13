package com.testandroid.yang.common;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * User
 * Created by yangjiajia on 2017/1/10 0010.
 */

public class User {

    public String name;

    public final ObservableField<String> firstName =
            new ObservableField<>();
    public final ObservableField<String> lastName =
            new ObservableField<>();
    public final ObservableInt age = new ObservableInt();

}
