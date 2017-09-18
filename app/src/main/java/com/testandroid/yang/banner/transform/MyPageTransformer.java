package com.testandroid.yang.banner.transform;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by yangjiajia on 2017/9/18.
 */

public class MyPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View view, float position) {
//        View cardView = view.findViewById(R.id.img_box);
////        View img = view.findViewById(R.id.img);
//
//        int diffWidth = (cardView.getWidth() - img.getWidth()) / 2;
//
//        if (position < -1) { // [-Infinity,-1)
//            cardView.setScaleX(defaultScale);
//            cardView.setScaleY(defaultScale);
//            img.setTranslationX(diffWidth);
//
//        } else if (position <= 0) { // [-1,0]
//            cardView.setScaleX((float) 1 + position / (float) 15);
//            cardView.setScaleY((float) 1 + position / (float) 15);
//            img.setTranslationX((0 - position) * diffWidth);
//
//        } else if (position <= 1) { // (0,1]
//            cardView.setScaleX((float) 1 - position / (float) 15);
//            cardView.setScaleY((float) 1 - position / (float) 15);
//            img.setTranslationX((0 - position) * diffWidth);
//
//        } else { // (1,+Infinity]
//            cardView.setScaleX(defaultScale);
//            cardView.setScaleY(defaultScale);
//            img.setTranslationX(-diffWidth);
//        }
    }
}
