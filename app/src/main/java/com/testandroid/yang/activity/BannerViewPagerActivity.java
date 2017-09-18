package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.testandroid.yang.R;
import com.testandroid.yang.banner.Banner;
import com.testandroid.yang.banner.listener.OnBannerListener;
import com.testandroid.yang.banner.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * BannerViewPagerActivity
 * Created by yangjiajia on 2017/9/18.
 */

public class BannerViewPagerActivity extends BaseActivity implements OnBannerListener {
//    @BindView(R.id.viewpager)
//    ViewPager viewpager;

    public static void start(Context context) {
        Intent starter = new Intent(context, BannerViewPagerActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_viewpager);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
//        viewpager.setAdapter(new BannerPagerAdapter());
//
//        viewpager.setPageMargin(Utility.dp2px(10));
//        Observable.interval(1, 3, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        viewpager.setCurrentItem(aLong.intValue() % viewpager.getAdapter().getCount());
//                    }
//                });

        Banner banner = (Banner) findViewById(R.id.banner);
        banner.setClipChildren(false);
        List<String> images = new ArrayList<>();
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg");
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        banner.setImages(images)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();
    }

    @Override
    public void initData() {

    }

    @Override
    public void OnBannerClick(int position) {

    }

    class BannerPagerAdapter extends PagerAdapter {
        private List<View> imageViews;

        public BannerPagerAdapter() {
            imageViews = new ArrayList<>();
            LayoutInflater inflater = LayoutInflater.from(BannerViewPagerActivity.this);
            ImageView view01 = (ImageView) inflater.inflate(R.layout.item_banner_viewpager, null, false);
            ImageView view02 = (ImageView) inflater.inflate(R.layout.item_banner_viewpager2, null, false);
            ImageView view03 = (ImageView) inflater.inflate(R.layout.item_banner_viewpager3, null, false);
            ImageView view04 = (ImageView) inflater.inflate(R.layout.item_banner_viewpager4, null, false);
            imageViews.add(view01);
            imageViews.add(view02);
            imageViews.add(view03);
            imageViews.add(view04);
        }

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            container.addView(imageViews.get(position));
            View view = imageViews.get(position);
//            if (bannerListener != null) {
//                view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        Log.e(tag, "你正在使用旧版点击事件接口，下标是从1开始，" +
////                                "为了体验请更换为setOnBannerListener，下标从0开始计算");
////                        bannerListener.OnBannerClick(position);
//                    }
//                });
//            }
//            if (listener != null) {
//                view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        listener.OnBannerClick(toRealPosition(position));
//                    }
//                });
//            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}
