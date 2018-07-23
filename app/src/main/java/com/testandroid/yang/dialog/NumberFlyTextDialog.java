package com.testandroid.yang.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextSwitcher;

import com.testandroid.yang.R;

/**
 * @author yangjiajia
 * @date 2018/7/20
 */
public class NumberFlyTextDialog extends Dialog {
    private static final String TAG = "NumberFlyDialog";

    private final TextSwitcher mTextSwitcher;
    private int mSecond;

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mSecond > 1) {
                mSecond--;
                mTextSwitcher.setText(String.valueOf(mSecond));

                mTextSwitcher.postDelayed(mRunnable, 1000);
            } else {
                mTextSwitcher.removeCallbacks(mRunnable);
                dismiss();
            }
        }
    };

    public NumberFlyTextDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.dialog_number_fly_text);
        mTextSwitcher = findViewById(R.id.text_switcher_number_fly);
//        mTextSwitcher.setInAnimation(context, R.anim.view_animator_in);
//        mTextSwitcher.setOutAnimation(context, R.anim.view_animator_out);
//        mTextSwitcher.setAnimateFirstView(false);
        mTextSwitcher.setInAnimation(context, R.anim.number_scale_in);
        mTextSwitcher.setOutAnimation(context, R.anim.number_scale_out);
    }

    public void show(int second) {
        if (isShowing()) {
            return;
        }

        if (second < 3) {
            return;
        }

        mSecond = second;

        show();

//        TextView textViewOne = (TextView) mTextSwitcher.getChildAt(0);
//        textViewOne.setText(String.valueOf(mSecond));

//        TextView textViewTwo = (TextView) mTextSwitcher.getChildAt(1);
//        textViewTwo.setText(String.valueOf(mSecond - 1));

        mTextSwitcher.postDelayed(mRunnable, 100);
//        Animation animation = mTextSwitcher.getAnimation();
//        if (animation != null) {
//            animation.setAnimationListener(new Animation.AnimationListener() {
//                @Override
//                public void onAnimationStart(Animation animation) {
//                    Log.d(TAG, "onAnimationStart: ");
//                }
//
//                @Override
//                public void onAnimationEnd(Animation animation) {
//                    Log.d(TAG, "onAnimationEnd: ");
//                }
//
//                @Override
//                public void onAnimationRepeat(Animation animation) {
//                    Log.d(TAG, "onAnimationRepeat: ");
//                }
//            });
//        }
    }

}
