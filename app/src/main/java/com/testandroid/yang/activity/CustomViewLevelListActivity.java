package com.testandroid.yang.activity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * LevelList
 * Created by yangjiajia on 2017/6/13 0013.
 */

public class CustomViewLevelListActivity extends BaseActivity {
    private static final String TAG = "LevelListActivity";
    @BindView(R.id.ivLevelList)
    ImageView ivLevelList;
    @BindView(R.id.btn_level_list)
    TextView btn;
    int levelIndex = 0;
    @BindView(R.id.transtionDrawable)
    ImageView transtionDrawable;
    @BindView(R.id.btn_transtion_previours)
    TextView btnTranstionPreviours;
    @BindView(R.id.btn_transtion_next)
    TextView btnTranstionNext;
    @BindView(R.id.levellist_layerlayter_clip)
    ImageView levellistLayerlayterClip;
    @BindView(R.id.levellist_layerlayter_click)
    TextView levellistLayerlayterClick;

    public static void start(Context context) {
        Intent starter = new Intent(context, CustomViewLevelListActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_level_list);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        levellistLayerlayterClip.setImageLevel(40);//init
    }

    @OnClick({R.id.btn_level_list, R.id.btn_transtion_previours,
            R.id.levellist_layerlayter_click,
            R.id.btn_transtion_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_level_list:
                Log.d(TAG, "onViewClicked: levelIndex=" + levelIndex);
                ivLevelList.setImageLevel(levelIndex++);
                break;
            case R.id.btn_transtion_previours:
                TransitionDrawable transitionDrawable = (TransitionDrawable) transtionDrawable.getDrawable();
                transitionDrawable.startTransition(10000);
                break;
            case R.id.btn_transtion_next:
                TransitionDrawable transitionDrawable2 = (TransitionDrawable) transtionDrawable.getDrawable();
                transitionDrawable2.resetTransition();
                break;
            case R.id.levellist_layerlayter_click://简化版，实际应用中 需要用instance 端盘
                levellistLayerlayterClip.setImageLevel(80);
                LevelListDrawable levelListDrawable = (LevelListDrawable) levellistLayerlayterClip.getDrawable();
                LayerDrawable layerDrawable = (LayerDrawable) levelListDrawable.getCurrent();
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                if (numberOfLayers > 1) {
                    final ClipDrawable clipDrawable = (ClipDrawable) layerDrawable.getDrawable(numberOfLayers - 1);
                    ValueAnimator valueAnimator = ValueAnimator.ofInt(0, levelListDrawable.getLevel());
                    valueAnimator.setDuration(2000);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Integer integer = (Integer) animation.getAnimatedValue();
                            clipDrawable.setLevel(100 * integer);//0--10000
                        }
                    });
                    valueAnimator.start();
                }
                break;
        }
    }

}
