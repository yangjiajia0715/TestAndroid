package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * fit new
 * Created by yangjiajia on 2017/8/7.
 * v21:
 * ViewGroup:
 *
 * @Override public WindowInsets dispatchApplyWindowInsets(WindowInsets insets) {
 * insets = super.dispatchApplyWindowInsets(insets);
 * if (!insets.isConsumed()) {
 * final int count = getChildCount();
 * for (int i = 0; i < count; i++) {
 *      insets = getChildAt(i).dispatchApplyWindowInsets(insets);
 *      if (insets.isConsumed()) {
 *          break;
 *      }
 * }
 * }
 * return insets;
 * }
 * ========================
 * View:
 * public WindowInsets dispatchApplyWindowInsets(WindowInsets insets) {
 * try {
 *      mPrivateFlags3 |= PFLAG3_APPLYING_INSETS;
 * if (mListenerInfo != null && mListenerInfo.mOnApplyWindowInsetsListener != null) {
 *      return mListenerInfo.mOnApplyWindowInsetsListener.onApplyWindowInsets(this, insets);
 * } else {
 *      return onApplyWindowInsets(insets);//
 * }
 * } finally {
 *      mPrivateFlags3 &= ~PFLAG3_APPLYING_INSETS;
 * }
 * }
 */

public class FitSystemwindowActivity2 extends BaseActivity {
    private static final String TAG = "FitSystemwindowActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.appbarLayout)
    AppBarLayout appbarLayout;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    public static void start(Context context) {
        Intent starter = new Intent(context, FitSystemwindowActivity2.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit_system_window_2);
        ButterKnife.bind(this);
        boolean fitsSystemWindows = coordinatorLayout.getFitsSystemWindows();
        Log.d(TAG, "onCreate: fitsSystemWindows=" + fitsSystemWindows);
        Log.d(TAG, "onCreate: appbarLayout=" + appbarLayout.getFitsSystemWindows());

        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
