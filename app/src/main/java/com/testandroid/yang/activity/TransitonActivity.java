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
//                Scene.getSceneForLayout()

//                TransitionManager.beginDelayedTransition();
//                setEnterSharedElementCallback();
//                TransitionManager.endTransitions();
        initView();
        initData();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementEnterTransition(new ChangeBounds().setDuration(3000));
        }

        int centerX = liushishi.getWidth()/2;
        int centerY = liushishi.getHeight()/2;
        if (Build.VERSION.SDK_INT >= 21) {
            Animator animator = ViewAnimationUtils.createCircularReveal(liushishi, centerX, centerY, 30, centerX);
            animator.setStartDelay(1000);
            animator.setDuration(3000);
            animator.start();
        }

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
