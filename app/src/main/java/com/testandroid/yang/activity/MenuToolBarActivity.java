package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.fragment.AnswerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 默认主页
 * Created by yangjiajia on 2017/8/25.
 */

public class MenuToolBarActivity extends BaseActivity {
    private static final String TAG = "MenuToolBarActivity";
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_mmmm)
    Toolbar toolbar;
    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.root_menu)
    LinearLayout rootMenu;

    public static void start(Context context) {
        Intent starter = new Intent(context, MenuToolBarActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_toolbar);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        AnswerFragment answerFragment = AnswerFragment.newInstance();
        getFragmentManager().beginTransaction()
                .add(R.id.fragment, answerFragment, answerFragment.getClass().getSimpleName())
                .commit();

//        Toolbar toolbar = (Toolbar) inflater.inflate(R.layout.include_toolbar_only_menu, rootMenu);
//        setSupportActionBar(toolbar);
//        Drawable navigationIcon = toolbar.getNavigationIcon();

//        RotateDrawable navigationIcon = (RotateDrawable) toolbar.getNavigationIcon();
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        Log.d(TAG, "initView: supportActionBar=" + supportActionBar);
    }

    @Override
    public void initData() {

    }
}
