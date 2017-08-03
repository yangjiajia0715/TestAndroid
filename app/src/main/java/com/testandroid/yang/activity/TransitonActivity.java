package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.testandroid.yang.R;

/**
 * TransitonActivity
 * Created by yangjiajia on 2017/8/1.
 */

public class TransitonActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, TransitonActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
//                Scene.getSceneForLayout()

//                TransitionManager.beginDelayedTransition();
//                setEnterSharedElementCallback();
//                TransitionManager.endTransitions();
        initView();
        initData();

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
