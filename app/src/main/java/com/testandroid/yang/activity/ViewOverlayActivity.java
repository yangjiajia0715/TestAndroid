package com.testandroid.yang.activity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroupOverlay;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ViewOverlayActivity
 * Created by yangjiajia on 2017/8/6.
 */

public class ViewOverlayActivity extends BaseActivity {
    private static final String TAG = "ViewOverlayActivity";
    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.root)
    LinearLayout root;

    public static void start(Context context) {
        Intent starter = new Intent(context, ViewOverlayActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlay);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= 18) {
            imageview.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ViewGroupOverlay groupOverlay = root.getOverlay();
                    groupOverlay.add(imageview);
                    Rect rect = new Rect();
                    root.getGlobalVisibleRect(rect);
                    String name = View.TRANSLATION_Y.getName();
                    Log.d(TAG, "run: name=" + name);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(imageview, "translationY", 0, rect.bottom);
                    animator.setDuration(6000);
                    animator.start();

                }
            }, 2000);

        }
    }

    @Override
    public void initData() {

    }
}
