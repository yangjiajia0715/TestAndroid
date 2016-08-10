package com.example.testandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.testandroid.R;
import com.example.testandroid.adapter.FrgStatePagerAdaper;

/**
 * fragmentstatePagerAdapter
 * author: yangjiajia
 * create time: 2016/8/10
 * desc:
 */
public class FrgStatePagerAdaperActivity extends Activity {
    private static final String TAG = "StatePagerAdaper";
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frg_state_pager_adapter);
        initView();
//        new MM()
        new FrgStatePagerAdaper(getFragmentManager());
//        getFr
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        FrgStatePagerAdaper adaper = new FrgStatePagerAdaper(getFragmentManager());
        mViewPager.setAdapter(adaper);

    }

}
