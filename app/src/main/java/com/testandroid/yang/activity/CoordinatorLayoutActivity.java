package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.util.MyBehavior;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * CoordinatorLayoutActivity 原理：Behavior
 * {@link MyBehavior}
 * Created by yangjiajia on 2017/1/18 0018.
 */

public class CoordinatorLayoutActivity extends BaseActivity {
    private static final String TAG = "CoordinatorLayoutActivi";
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    public static void start(Context context) {
        Intent starter = new Intent(context, CoordinatorLayoutActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        ButterKnife.bind(this);
        initView();
        initData();

//        testReflect();
    }

    private void testReflect() {
        String packageName = getPackageName();
        Log.d(TAG, "onCreate: packageName=" + packageName);
        ClassLoader classLoader = getClassLoader();
        Log.d(TAG, "onCreate: classLoader=" + classLoader);

        try {
            Class<?> clazz = Class.forName("com.testandroid.yang.util.MyBehavior", true, getClassLoader());
            Log.d(TAG, "onCreate: clazz=" + clazz);
            Class[] attrs = {Context.class, AttributeSet.class};
            Constructor<?> constructor = clazz.getConstructor(attrs);
            Object instance = constructor.newInstance(this, null);
            Log.d(TAG, "onCreate: instance=" + instance);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        TextView swipeView = (TextView)findViewById(R.id.swip);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) swipeView.getLayoutParams();
        SwipeDismissBehavior<View> behavior = new SwipeDismissBehavior<>();
        behavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY);
        behavior.setListener(new SwipeDismissBehavior.OnDismissListener() {
            @Override
            public void onDismiss(View view) {
                Log.d(TAG, "onDismiss: view=" + view);
            }

            @Override
            public void onDragStateChanged(int state) {
                Log.d(TAG, "onDragStateChanged: state=" + state);
            }
        });
        params.setBehavior(behavior);
    }
}
