package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import com.testandroid.yang.R;
import com.testandroid.yang.fragment.CheeseListFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * FitSystemWindowActivity
 * Created by yangjiajia on 2017/1/22 0022.
 */

public class FitSystemWindowActivity extends BaseActivity {
    private static final String TAG = "FitSystem";
    private AppBarLayout appBarLayout;

    public static void start(Context context) {
        Intent starter = new Intent(context, FitSystemWindowActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit_system_window);
        Window window = getWindow();
//        window.set
        initView();
        initData();
        testRxLifeCircle();
    }

    private void testRxLifeCircle() {
//        Observable.just(1,2)
//                .compose(this.<Integer>bindToLifecycle())
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(@NonNull Integer integer) throws Exception {
//
//                    }
//                });

        Observable.just(3, 4)
                .compose(this.<Integer>bindToLifecycle())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "accept: in=" + integer);
                    }
                });


    }

    @Override
    public void initView() {

        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        ViewCompat.setOnApplyWindowInsetsListener(toolbar, new OnApplyWindowInsetsListener() {
//            @Override
//            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
//                Log.d(TAG, "onApplyWindowInsets toolbar:isConsumed= " + insets.isConsumed());
//                return insets;
//            }
//        });

//        ViewCompat.offsetTopAndBottom(toolbar, 166);//没作用

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowTitleEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingtoolbarlayout);
        collapsingToolbarLayout.setTitle("代码标题");
        collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLUE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.RED);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

//        appBarLayout.setFitsSystemWindows(true);
        boolean fitsSystemWindows = ViewCompat.getFitsSystemWindows(appBarLayout);
//        ViewCompat.setOnApplyWindowInsetsListener(appBarLayout, new OnApplyWindowInsetsListener() {
//            @Override
//            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
//                Log.d(TAG, "onApplyWindowInsets: top=" + insets.getSystemWindowInsetTop());
//                Log.d(TAG, "onApplyWindowInsets: insets=" + insets);
//                Log.d(TAG, "onApplyWindowInsets: isConsumed=" + insets.isConsumed());
//                return insets;
//            }
//        });

        Log.d(TAG, "initView: fitsSystemWindows=" + fitsSystemWindows);
        Log.d(TAG, "initView: getFitsSystemWindows=" + appBarLayout.getFitsSystemWindows());



    }


    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new CheeseListFragment(), "Category 1");
        adapter.addFragment(new CheeseListFragment(), "Category 2");
        adapter.addFragment(new CheeseListFragment(), "Category 3");
//        adapter.addFragment(new CheeseListFragment(), "Category 4");
//        adapter.addFragment(new CheeseListFragment(), "Category 5");
//        adapter.addFragment(new CheeseListFragment(), "Category 6");
//        adapter.addFragment(new CheeseListFragment(), "Category 7");
        viewPager.setAdapter(adapter);
    }


    @Override
    public void initData() {

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
