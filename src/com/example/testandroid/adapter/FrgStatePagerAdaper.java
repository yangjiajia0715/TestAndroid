package com.example.testandroid.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.testandroid.fragment.ListViewFragment;

/**
 * author: yangjiajia
 * create time: 2016/8/10 0010.
 * desc:
 */
public class FrgStatePagerAdaper extends FragmentStatePagerAdapter {
    private static final String TAG = "FrgStatePagerAdaper";

    public FrgStatePagerAdaper(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        ListViewFragment fragment = new ListViewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ListViewFragment.KEY_POS,position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 9;
    }

    @Override
    public Parcelable saveState() {
        Log.d(TAG, "--FrgStatePagerAdaper--saveState: ");
        return super.saveState();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        Log.d(TAG, "--FrgStatePagerAdaper--restoreState: state=" + state + ",loader=" +loader );
        super.restoreState(state, loader);
    }
}
