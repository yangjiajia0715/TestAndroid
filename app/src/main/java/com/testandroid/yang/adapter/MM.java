package com.testandroid.yang.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.testandroid.yang.fragment.ListViewFragment;


/**
 * author: yangjiajia
 * create time: 2016/8/10 0010
 * desc:
 */
public class MM extends FragmentStatePagerAdapter {
    public MM(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        ListViewFragment fragment = new ListViewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ListViewFragment.KEY_POS,position);
        fragment.setArguments(bundle);
        return fragment;
//        return null;
    }

    @Override
    public int getCount() {
        return 9;
    }
}
