package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.log.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * SceneActivity
 * Created by yangjiajia on 2018/4/20.
 */

public class SceneActivity extends BaseActivity {

    @BindView(R.id.tv_scene_1)
    TextView mTvScene1;
    @BindView(R.id.tv_scene_2)
    TextView mTvScene2;
    @BindView(R.id.tv_scene_3)
    TextView mTvScene3;
    @BindView(R.id.tv_scene_4)
    TextView mTvScene4;
    @BindView(R.id.tv_scene_5)
    TextView mTvScene5;
    @BindView(R.id.scene_container)
    RelativeLayout mSceneContainer;
    @BindView(R.id.scene_image_liutao)
    ImageView mSceneImageLiutao;
    @BindView(R.id.scene_root)
    FrameLayout mSceneRoot;
    private Scene mSceneA;
    private Scene mSceneB;

    public static void start(Context context) {
        Intent starter = new Intent(context, SceneActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT > 19) {
            mSceneA = Scene.getSceneForLayout(mSceneRoot, R.layout.include_scene_image_1, this);
            mSceneB = Scene.getSceneForLayout(mSceneRoot, R.layout.include_scene_image_2, this);
            AutoTransition autoTransition;
//            Transition transition = new Slide();
//            Transition transitionE = new Explode();
//            Transition transition = new Slide();
            Transition transitionF = new Fade();
//            transitionF.removeTarget(3);

//            TransitionManager.beginDelayedTransition();
//            TransitionManager.go(sceneForLayout,transition);

            Fade fade;
            Explode explode;
            Slide slide;

        }
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_scene_1, R.id.tv_scene_2, R.id.tv_scene_3, R.id.tv_scene_4, R.id.tv_scene_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_scene_1:
                if (Build.VERSION.SDK_INT >= 19) {
                    TransitionManager.go(mSceneA);
                }
                break;
            case R.id.tv_scene_2:
                if (Build.VERSION.SDK_INT >= 19) {
                    TransitionManager.go(mSceneB, new ChangeBounds());
                }
                break;
            case R.id.tv_scene_3:
                if (Build.VERSION.SDK_INT >= 19) {
                    Transition transition = new Fade().setDuration(3000);
                    transition.addListener(new Transition.TransitionListener() {
                        @Override
                        public void onTransitionStart(Transition transition) {
                            Log.d("TransitionListener--onTransitionStart");
                        }

                        @Override
                        public void onTransitionEnd(Transition transition) {
                            Log.d("TransitionListener--onTransitionEnd");
                        }

                        @Override
                        public void onTransitionCancel(Transition transition) {
                            Log.d("TransitionListener--onTransitionCancel");
                        }

                        @Override
                        public void onTransitionPause(Transition transition) {
                            Log.d("TransitionListener--onTransitionPause");
                        }

                        @Override
                        public void onTransitionResume(Transition transition) {

                        }
                    });


                    TransitionManager.beginDelayedTransition(mSceneRoot, transition);
                    mSceneRoot.findViewById(R.id.scene_image_liu).setVisibility(View.GONE);
                }
                break;
            case R.id.tv_scene_4:
                break;
            case R.id.tv_scene_5:
                break;
        }
    }
}
