package com.testandroid.yang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.common.HomeInfo;

import java.util.List;

/**
 * Created by yangjiajia on 2017/1/17 0017.
 */

public class HomeRecyleViewAdapter extends RecyclerView.Adapter<HomeRecyleViewAdapter.HomeHolder> {
    public List<HomeInfo> infos;
    public Context context;
    private LayoutInflater inflater;

    private View.OnClickListener clickListener;

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public HomeRecyleViewAdapter( Context context, List<HomeInfo> infos) {
        this.infos = infos;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public HomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeHolder(inflater.inflate(R.layout.item_home_recycle_view, null, false));
    }

    @Override
    public void onBindViewHolder(HomeHolder holder, int position) {
        HomeInfo info = infos.get(position);
        holder.tvContent.setText(info.content);

        if (clickListener != null) {
            holder.tvContent.setTag(R.id.tag_first, info);
            holder.tvContent.setOnClickListener(clickListener);
        }
    }

    @Override
    public int getItemCount() {
        return infos == null ? 0 : infos.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class HomeHolder extends RecyclerView.ViewHolder {

        public final TextView tvContent;

        public HomeHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}

