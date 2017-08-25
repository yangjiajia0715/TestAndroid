package com.testandroid.yang.activity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.testandroid.yang.R;
import com.testandroid.yang.fragment.Answer2Fragment;
import com.testandroid.yang.fragment.AnswerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment 相关
 * Created by yangjiajia on 2017/8/10.
 */

public class FragmentActivity extends BaseActivity {
    private static final String TAG = "FragmentActivity";

    @BindView(R.id.container)
    FrameLayout container;
    private boolean loader;

    public static void start(Context context) {
        Intent starter = new Intent(context, FragmentActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate: FragmentActivity---getTaskId=" + getTaskId());
        String simpleName = AnswerFragment.class.getSimpleName();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.findFragmentByTag(simpleName);
        Log.d(TAG, "onCreate: FragmentActivity---getBackStackEntryCount=" + fragmentManager.getBackStackEntryCount());
        Log.d(TAG, "onCreate: FragmentActivity---savedInstanceState=" + savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void initView() {
        if (!loader) {
            loader = true;
//        Fragment fragment = CheeseListFragment.instantiate(this, "ff");
//        Fragment fragment = CheeseListFragment.newInstance();
            AnswerFragment answerFragment = AnswerFragment.newInstance();
            Answer2Fragment answer2Fragment = Answer2Fragment.newInstance();
            getFragmentManager().beginTransaction()
                    .add(R.id.container, answer2Fragment, answer2Fragment.getClass().getSimpleName())
                    .hide(answer2Fragment)
                    .add(R.id.container, answerFragment, answerFragment.getClass().getSimpleName())
                    .commit();
        }
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        Log.d(TAG, "onResumeFragments: ");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "onPostResume: ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: FragmentActivity---getTaskId=" + getTaskId());
    }
}
