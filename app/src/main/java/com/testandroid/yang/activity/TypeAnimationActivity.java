package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SearchView;

import com.testandroid.yang.R;
import com.testandroid.yang.adapter.HomeRecyleViewAdapter;
import com.testandroid.yang.common.HomeInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 动画相关
 * Created by yangjiajia on 2017/3/1 0001.
 */
@Deprecated
public class TypeAnimationActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TypeAnimation";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.temp_trans_animation)
    View tempTransAnimation;

    private List<HomeInfo> infos = new ArrayList<>();
    private List<HomeInfo> items;
    private HomeRecyleViewAdapter adapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, TypeAnimationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
        SearchView searchView;
    }

    @Override
    public void initView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        toolbar.setTitle(R.string.title_animtion);

        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void initData() {
        items = new ArrayList<>();

        infos.add(new HomeInfo("tv_animation", R.id.tv_animation, HomeInfo.HomeGroup.Animator));
        infos.add(new HomeInfo("tv_ViewAnimator", R.id.tv_ViewAnimator, HomeInfo.HomeGroup.Animator));
        infos.add(new HomeInfo("MD Animation", R.id.tv_animation_materail_design, HomeInfo.HomeGroup.Animator));
        infos.add(new HomeInfo("Trans Animation", R.id.tv_animation_trans, HomeInfo.HomeGroup.Animator));

        items.addAll(infos);
        adapter = new HomeRecyleViewAdapter(this, items);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getTag(R.id.tag_first) instanceof HomeInfo) {
            HomeInfo info = (HomeInfo) v.getTag(R.id.tag_first);
            switch (info.id) {
                case R.id.tv_animation:
                    intent = new Intent(this, AnimationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_ViewAnimator:
                    intent = new Intent(this, ViewAnimatorActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_animation_materail_design:
                    ActionBarActivity.start(this);
                    break;
                case R.id.tv_animation_trans:
                    trans();
                    break;
            }
        }
    }

    private void trans() {
//        tempTransAnimation.setTranslationY(150);//px 正数 向下

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_fade_in);
        tempTransAnimation.startAnimation(animation);
    }
}
