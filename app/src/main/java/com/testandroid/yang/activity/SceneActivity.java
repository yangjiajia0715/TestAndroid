package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.widget.RelativeLayout;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * SceneActivity
 * Created by yangjiajia on 2018/4/20.
 */

public class SceneActivity extends BaseActivity {

    @BindView(R.id.root_scene)
    RelativeLayout mRootScene;

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
            Scene scene = new Scene(mRootScene, mRootScene);
//            scene.setEnterAction();//Runable

            Scene sceneForLayout = Scene.getSceneForLayout(mRootScene, R.layout.activity_scene, this);
            TransitionManager.go(sceneForLayout);

            AutoTransition autoTransition;
            Transition transition;

//            TransitionManager.go(sceneForLayout,transition);

            Fade fade;
            Explode explode;
            Slide slide;

        }
    }

    @Override
    public void initData() {

    }
}
