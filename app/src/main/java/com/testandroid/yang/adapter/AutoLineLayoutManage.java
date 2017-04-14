package com.testandroid.yang.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.testandroid.yang.activity.RecycleViewActivity;

import java.util.List;

/**
 * {@link RecycleViewActivity}
 * Created by yangjiajia on 2017/4/11.
 */

public class AutoLineLayoutManage extends RecyclerView.LayoutManager {

    @Override
    public void onAdapterChanged(RecyclerView.Adapter oldAdapter, RecyclerView.Adapter newAdapter) {
        super.onAdapterChanged(oldAdapter, newAdapter);
    }

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

        List<RecyclerView.ViewHolder> scrapList = recycler.getScrapList();

        View viewForPosition = recycler.getViewForPosition(0);

    }
}
