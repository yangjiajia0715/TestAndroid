package com.testandroid.yang.activity;

import android.animation.Animator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * TransitonActivity
 * Created by yangjiajia on 2017/8/1.
 */

public class TransitonActivity extends BaseActivity {
    private static final String TAG = "TransitonActivity";

    @BindView(R.id.liushishi)
    ImageView liushishi;
    @BindView(R.id.root)
    LinearLayout root;

    public static void start(Activity context, ActivityOptions activityOptions) {
        Intent starter = new Intent(context, TransitonActivity.class);
        context.startActivity(starter, activityOptions.toBundle());
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, TransitonActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        ButterKnife.bind(this);
        initView();
        initData();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setExitTransition(TransitionInflater.from(this).inflateTransition(R.transition.slide));
//            getWindow().setEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.slide_exclude));

//            getWindow().setEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.slide));

            getWindow().setSharedElementEnterTransition(new ChangeBounds().setDuration(3000));
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.BOTTOM);
            slide.setDuration(3000);

            Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.slide_exclude);
            getWindow().setEnterTransition(transition);

//            Slide slideExit = new Slide();
//            slideExit.setSlideEdge(Gravity.RIGHT);
//            slideExit.setDuration(3000);
//            Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.slide_exclude);
//            getWindow().setExitTransition(transition);
        }

        liushishi.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= 21) {
                    int centerX = liushishi.getWidth() / 2;
                    int centerY = liushishi.getHeight() / 2;
//                    Animal animator = ViewAnimationUtils.createCircularReveal(liushishi, centerX, centerY, 30, centerX);
                    Log.d(TAG, "run: " + Math.sqrt(16));
                    Log.d(TAG, "run: " + Math.hypot(3, 4));
                    Animator animator = ViewAnimationUtils.createCircularReveal(liushishi, 0, 0, 100 * 3, (float) Math.hypot(liushishi.getWidth(), liushishi.getHeight()));
                    animator.setDuration(5000);
//                    animator.setDuration(300);
                    animator.start();
                }
            }
        }, 1000);


//        ViewAnimationUtils.createCircularReveal()
    }

    @Override
    public void initView() {
//        Scene.getSceneForLayout(r)
//        TransitionManager.go();
    }

    @Override
    public void initData() {

    }
}
