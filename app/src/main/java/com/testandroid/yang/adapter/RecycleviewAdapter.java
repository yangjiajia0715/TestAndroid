package com.testandroid.yang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testandroid.yang.R;
import com.testandroid.yang.activity.RecycleViewActivity;
import com.testandroid.yang.common.User;

import java.util.List;

/**
 * {@link RecycleViewActivity}
 * Created by yangjiajia on 2017/4/12.
 */

public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.Holder> {

    private List<User> users;
    private Context context;



    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.item_view_type_first, null);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.getAdapterPosition();
//        holder.getLayoutPosition();

//        RecyclerView.NO_POSITION



    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class Holder extends RecyclerView.ViewHolder{

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
