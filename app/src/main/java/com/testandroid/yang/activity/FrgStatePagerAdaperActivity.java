package com.testandroid.yang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.testandroid.yang.R;
import com.testandroid.yang.adapter.FrgStatePagerAdaper;
import com.testandroid.yang.common.ZoomOutPageTransformer;

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
        mViewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        FrgStatePagerAdaper adaper = new FrgStatePagerAdaper(getFragmentManager());

        mViewPager.setAdapter(adaper);

    }

}
