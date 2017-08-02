package com.testandroid.yang.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.common.ATestReportItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 分析报告adapter
 * Created by yangjiajia on 2017/8/2.
 */

public class AnalysisReportListAdapter extends RecyclerView.Adapter<AnalysisReportListAdapter.ViewHolder> {
    private List<ATestReportItem> dates;

    public AnalysisReportListAdapter(List<ATestReportItem> dates) {
        this.dates = dates;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_report, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ATestReportItem item = dates.get(position);
        holder.title.setText(item.name);
        holder.subtitle.setText(item.category);
    }

    @Override
    public int getItemCount() {
        return dates == null ? 0 : dates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.subtitle)
        TextView subtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

