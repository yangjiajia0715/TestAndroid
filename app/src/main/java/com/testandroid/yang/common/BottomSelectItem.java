package com.testandroid.yang.common;

/**
 * 底部选择item
 * Created by yangjiajia on 2016/11/14 0014.
 */

public class BottomSelectItem implements IBottomSelectItem {
    private String name;
    private int itemId;

    public BottomSelectItem(String name, int itemId) {
        this.name = name;
        this.itemId = itemId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getItemId() {
        return itemId;
    }

    @Override
    public String getExt() {
        return null;
    }
}
