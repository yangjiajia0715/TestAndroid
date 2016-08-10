package com.example.testandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * author: yangjiajia
 * create time: 2016/8/10 0010.
 * desc:
 */
public class FrgStatePagerAdaper extends FragmentStatePagerAdapter {

    public FrgStatePagerAdaper(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
