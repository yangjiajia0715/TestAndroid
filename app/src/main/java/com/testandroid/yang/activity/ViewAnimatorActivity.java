package com.testandroid.yang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageSwitcher;
import android.widget.TextSwitcher;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;

import com.testandroid.yang.R;


/**
 * ViewAnimator
 * author: yangjiajia
 * create time: 2016-9-21
 * desc:
 */
public class ViewAnimatorActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "ViewAnimatorActivity";

    private ViewAnimator viewAnimator;

    private TextSwitcher textSwitcher;

    private ImageSwitcher imageSwitcher;

    private ViewFlipper viewFlipper;

    private boolean isShownSec = false;

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewanimator);
        initView();

    }

    private void initView() {

        viewAnimator = findViewById(R.id.viewanimator);
        viewAnimator.setInAnimation(this, R.anim.view_animator_in);
        viewAnimator.setOutAnimation(this, R.anim.view_animator_out);

        textSwitcher = findViewById(R.id.text_switcher_animator);
        textSwitcher.setInAnimation(this, R.anim.view_animator_in);
        textSwitcher.setOutAnimation(this, R.anim.view_animator_out);


        imageSwitcher = findViewById(R.id.image_switcher_animator);
        imageSwitcher.setInAnimation(this, R.anim.view_animator_in);
        imageSwitcher.setOutAnimation(this, R.anim.view_animator_out);

        viewFlipper = findViewById(R.id.view_flipper_container);
        viewFlipper.setInAnimation(this, R.anim.view_animator_in);
        viewFlipper.setOutAnimation(this, R.anim.view_animator_out);

        findViewById(R.id.btn_viewanimator).setOnClickListener(this);
        findViewById(R.id.btn_textswitcher).setOnClickListener(this);
        findViewById(R.id.btn_imageswitcher).setOnClickListener(this);
        findViewById(R.id.btn_viewFlipper).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_viewanimator:
                isShownSec = !isShownSec;
                if (isShownSec) {
                    viewAnimator.setDisplayedChild(1);
                } else {
                    viewAnimator.setDisplayedChild(0);
                }

                View currentView = viewAnimator.getCurrentView();

                Animation inAnimation = viewAnimator.getInAnimation();
                Animation outAnimation = viewAnimator.getOutAnimation();

//                viewAnimator.showNext();
//                viewAnimator.showPrevious();

                int baseline = viewAnimator.getBaseline();

                Log.d(TAG, "onClick: currentView=" + currentView + ",inAnimation=" + inAnimation
                        + ",outAnimation=" + outAnimation + ",baseline=" + baseline);

                break;

            case R.id.btn_textswitcher:
                textSwitcher.setText("张三" + i++);

                Log.d(TAG, "onClick: btn_textswitcher currentView=" + textSwitcher);
                break;

            case R.id.btn_imageswitcher:
                isShownSec = !isShownSec;
                if (isShownSec) {
                    imageSwitcher.setImageResource(R.drawable.liushishi);
                }else {
                    imageSwitcher.setImageResource(R.drawable.liutao_big_image);
                }
                break;
            case R.id.btn_viewFlipper:
                isShownSec = !isShownSec;

                viewFlipper.setFlipInterval(2000);
                if (isShownSec) {
                    viewFlipper.startFlipping();
                }else {
                    viewFlipper.stopFlipping();
                }
                break;
            default:
                break;
        }
    }
}
