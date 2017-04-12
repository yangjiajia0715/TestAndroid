package com.testandroid.yang.adapter;

import android.support.v7.widget.RecyclerView;

import com.testandroid.yang.activity.RecycleViewActivity;

/**
 * {@link RecycleViewActivity}
 * Created by yangjiajia on 2017/4/11.
 */

public class AutoLineLayoutManage extends RecyclerView.LayoutManager {

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        super.onMeasure(recycler, state, widthSpec, heightSpec);

    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);

        if (state.isPreLayout()) {
            return;
        }


    }
}
