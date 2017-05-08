package com.testandroid.yang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter.Adapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.testandroid.yang.R;

/**
 * Created by yangjiajia on 2017/5/8.
 */

public class HomePage1Adapter extends Adapter<HomePage1Adapter.Holder1> {

    private Context context;

    public HomePage1Adapter(Context context) {
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        LinearLayoutHelper helper = new LinearLayoutHelper();
        helper.setMargin(10,10,10,10);
        helper.setBgColor(Color.parseColor("#fea73d"));
        helper.setDividerHeight(10);
        helper.setAspectRatio(8);
        return helper;
    }

    @Override
    public Holder1 onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_home_page1,null);
        return new Holder1(view);
    }

    @Override
    public void onBindViewHolder(Holder1 holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class Holder1 extends RecyclerView.ViewHolder {
        public Holder1(View itemView) {
            super(itemView);
        }
    }
}
