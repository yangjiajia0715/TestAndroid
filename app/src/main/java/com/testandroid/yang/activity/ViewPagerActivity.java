package com.testandroid.yang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.testandroid.yang.R;
import com.testandroid.yang.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * author: yangjiajia
 * create time: 2016/8/2 0002
 * desc:
 */
public class ViewPagerActivity extends Activity {
    private static final String TAG = "ViewPagerAct";
    @BindView(R.id.pager_title_strip)
    PagerTitleStrip pagerTitleStrip;
    @BindView(R.id.viewpager_all_viewpager)
    ViewPager viewpagerAllViewpager;

    public static void start(Context context) {
        Intent starter = new Intent(context, ViewPagerActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
//        viewPager.setAdapter();
        viewpagerAllViewpager.setPageMargin(Utility.dp2px(10));
        MyPagerAdapter mAdapter = new MyPagerAdapter(this);
        viewpagerAllViewpager.setAdapter(mAdapter);
    }

    private class MyPagerAdapter extends PagerAdapter {
        View[] views = new View[3];
        int[] ids = new int[]{R.drawable.img01, R.drawable.liutao_big_image, R.drawable.img02};
        private Context mContext;

        @Override
        public CharSequence getPageTitle(int position) {
            return "标题" + position;
        }

        public MyPagerAdapter(Context context) {
            mContext = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.d(TAG, "instantiateItem: position=" + position);
            ZoomImage zoomImage = new ZoomImage(mContext);
            zoomImage.setImageResource(ids[position]);
            views[position] = zoomImage;
            container.addView(zoomImage);
            return views[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.d(TAG, "destroyItem: position=" + position);
            container.removeView(views[position]);
        }

        @Override
        public int getCount() {
            Log.d(TAG, "getCount: views.length=" + views.length);
            return views.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            Log.d(TAG, "isViewFromObject: view=" + view + ",o=" + o);
            return view == o;
        }
    }

}
