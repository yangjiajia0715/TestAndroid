package com.example.testandroid.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.testandroid.R;
import com.example.testandroid.fragment.ListViewFragment;
import com.example.testandroid.fragment.ScrollViewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author: yangjiajia
 * create time: 2016/8/7
 * desc:
 */
public class ViewPagerAllActivity extends Activity {
    private static final String TAG = "ViewPagerAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_all);
        initView();
    }

    private void initView() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_viewpager);
        MyPagerAdapter mAdapter = new MyPagerAdapter(getFragmentManager());
        viewPager.setAdapter(mAdapter);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public MyPagerAdapter(android.app.FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<>();

            ListViewFragment listViewFragment = new ListViewFragment();
            fragments.add(listViewFragment);

            ScrollViewFragment scrollViewFragment = new ScrollViewFragment();
            fragments.add(scrollViewFragment);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }
    }

}
