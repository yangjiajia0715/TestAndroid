package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.testandroid.yang.R;
import com.testandroid.yang.fragment.CheeseListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment 相关
 * Created by yangjiajia on 2017/8/10.
 */

public class FragmentActivity extends BaseActivity {

    @BindView(R.id.container)
    FrameLayout container;

    public static void start(Context context) {
        Intent starter = new Intent(context, FragmentActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        Fragment fragment = CheeseListFragment.instantiate(this, "ff");
        getSupportFragmentManager().beginTransaction()
                .add(fragment,"")
                .commit();
    }

    @Override
    public void initData() {

    }
}
