package com.testandroid.yang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter.Adapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.testandroid.yang.R;

/**
 * Created by yangjiajia on 2017/5/8.
 */

public class HomePagew2Adapter extends Adapter<HomePagew2Adapter.Holder1> {

    private Context context;

    public HomePagew2Adapter(Context context) {
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        GridLayoutHelper helper = new GridLayoutHelper(2);
        helper.setBgColor(Color.BLUE);
        helper.setHGap(10*3);
        helper.setPadding(0,30,0,30);
//        helper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return 0;
//            }
//        });
        return helper;
    }

    @Override
    public Holder1 onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_home_page2,null);
        return new Holder1(view);
    }

    @Override
    public void onBindViewHolder(Holder1 holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class Holder1 extends RecyclerView.ViewHolder {
        public Holder1(View itemView) {
            super(itemView);
        }
    }
}
