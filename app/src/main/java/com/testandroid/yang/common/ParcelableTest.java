package com.testandroid.yang.common;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangjiajia on 2017/7/21.
 */

public class ParcelableTest implements Parcelable {
    protected ParcelableTest(Parcel in) {
    }

    public static final Creator<ParcelableTest> CREATOR = new Creator<ParcelableTest>() {
        @Override
        public ParcelableTest createFromParcel(Parcel in) {
            return new ParcelableTest(in);
        }

        @Override
        public ParcelableTest[] newArray(int size) {
            return new ParcelableTest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
