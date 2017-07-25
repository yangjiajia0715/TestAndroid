package com.testandroid.yang.common;

import android.net.Uri;

import com.testandroid.yang.adapter.ImageAdapter;

/**
 * 配合{@link ImageAdapter}使用
 * Created by yangjiajia on 2017/7/24.
 */

public class ImageItem {
    private String name;
    private Uri uri;
    private long size;

    public ImageItem() {
    }

    public ImageItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
