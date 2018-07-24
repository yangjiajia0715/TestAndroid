package com.testandroid.yang.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.ViewFlipper;

import com.testandroid.yang.R;

/**
 * @author yangjiajia
 * @date 2018/7/20
 */
public class NumberFlyDialog extends Dialog {
    private static final String TAG = "NumberFlyDialog";

    private final ViewFlipper mViewFlipper;

    public NumberFlyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.dialog_number_fly);
        mViewFlipper = findViewById(R.id.text_switcher_number_fly);
        mViewFlipper.setInAnimation(context, R.anim.view_animator_in);
        mViewFlipper.setOutAnimation(context, R.anim.view_animator_out);

//        mViewFlipper.getAnimation().setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                Log.d(TAG, "onAnimationStart: ");
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                Log.d(TAG, "onAnimationEnd: ");
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//                Log.d(TAG, "onAnimationRepeat: ");
//            }
//        });
    }

    @Override
    public void show() {
        super.show();
        mViewFlipper.setFlipInterval(2000);
        mViewFlipper.startFlipping();

        Animation animation = mViewFlipper.getAnimation();
        if (animation != null) {
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    Log.d(TAG, "onAnimationStart: ");
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Log.d(TAG, "onAnimationEnd: ");
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    Log.d(TAG, "onAnimationRepeat: ");
                }
            });
        }
    }
}
