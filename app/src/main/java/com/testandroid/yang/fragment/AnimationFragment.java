package com.testandroid.yang.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.testandroid.yang.R;
import com.testandroid.yang.activity.ActionBarActivity;
import com.testandroid.yang.activity.AnimationActivity;
import com.testandroid.yang.activity.ViewAnimatorActivity;
import com.testandroid.yang.common.HomeInfo;

/**
 * Created by yangjiajia on 2017/9/13.
 */

public class AnimationFragment extends HomeBaseFragment {
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public static AnimationFragment newInstance() {
        Bundle bundle = new Bundle();
        AnimationFragment fragment = new AnimationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initView() {
        items.add(new HomeInfo("tv_animation", R.id.tv_animation, HomeInfo.HomeGroup.Animator));
        items.add(new HomeInfo("tv_ViewAnimator", R.id.tv_ViewAnimator, HomeInfo.HomeGroup.Animator));
        items.add(new HomeInfo("MD Animation", R.id.tv_animation_materail_design, HomeInfo.HomeGroup.Animator));
        items.add(new HomeInfo("Trans Animation", R.id.tv_animation_trans, HomeInfo.HomeGroup.Animator));
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent;
        if (v.getTag(R.id.tag_first) instanceof HomeInfo) {
            HomeInfo info = (HomeInfo) v.getTag(R.id.tag_first);
            switch (info.id) {
                case R.id.tv_animation:
                    intent = new Intent(context, AnimationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_ViewAnimator:
                    intent = new Intent(context, ViewAnimatorActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_animation_materail_design:
                    ActionBarActivity.start(context);
                    break;
                case R.id.tv_animation_trans:
                    trans();
                    break;
            }
        }
    }

    private void trans() {

    }
}
