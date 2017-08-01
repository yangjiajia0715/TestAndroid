package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * LiushishiActivity 共享元素
 * Created by yangjiajia on 2017/7/31.
 */

public class LiushishiActivity extends BaseActivity {

    @BindView(R.id.liu)
    ImageView liu;
    @BindView(R.id.root)
    LinearLayout root;

    public static void start(Context context) {
        Intent starter = new Intent(context, LiushishiActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liushishi);
        ButterKnife.bind(this);
        initView();
        initData();

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
//            TransitionManager.beginDelayedTransition(,new Fade());
//            TransitionManager transitionManager = getContentTransitionManager();

            TransitionManager.beginDelayedTransition(root, new Fade());
//            Transition
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.liu)
    public void onViewClicked() {
    }
}
