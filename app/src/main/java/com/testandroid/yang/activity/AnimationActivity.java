package com.testandroid.yang.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.testandroid.yang.R;
import com.testandroid.yang.banner.transform.ZoomOutPageTransformer;
import com.testandroid.yang.util.Utility;


/**
 * author: yangjiajia
 * create time: 2016/8/2 0002
 * desc:
 */
public class AnimationActivity extends Activity {
    private static final String TAG = "ViewPagerAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initView();
    }

    private void initView() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.animtion_viewpager);
        viewPager.setAdapter(new MyPagerAdapter(this));
//        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
//        viewPager.setPageTransformer(true, new ZoomOutSlideTransformer());
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

//        viewPager.setPageMargin(Utility.dp2px(10));
        viewPager.setOffscreenPageLimit(3);

        View anitrionTran = findViewById(R.id.animation_tran);
        anitrionTran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setTranslationX(Utility.dp2px(-20));
            }
        });

    }

    class MyPagerAdapter extends PagerAdapter {
        //        int[] ids = new int[]{R.drawable.img01, R.drawable.liutao_big_image, R.drawable.img02};
        int[] ids = new int[]{R.drawable.banner01, R.drawable.banner02, R.drawable.banner03, R.drawable.banner04};
        View[] views = new View[ids.length];
        private Context mContext;

        public MyPagerAdapter(Context context) {
            mContext = context;
            for (int i = 0; i < ids.length; i++) {
                ImageView imageView = new ImageView(mContext);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setImageResource(ids[i]);
                views[i] = imageView;
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.d(TAG, "instantiateItem: position=" + position);
//            ZoomImage zoomImage = new ZoomImage(mContext);
//            zoomImage.setImageResource(ids[position]);

            container.addView(views[position]);
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
