package com.testandroid.yang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.activity.RecycleViewActivity;
import com.testandroid.yang.common.Student;

import java.util.List;

/**
 * {@link RecycleViewActivity}
 * Created by yangjiajia on 2017/4/12.
 */

public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.Holder> {

    private static final String TAG = "RecycleviewAdapter";
    private List<Student> students;
    private Context context;

    public RecycleviewAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @Override
    public void onViewRecycled(Holder holder) {
        super.onViewRecycled(holder);
        int adapterPosition = holder.getAdapterPosition();
        Log.d(TAG, "onViewRecycled: adapterPosition=" + adapterPosition);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        long start = System.currentTimeMillis();
//        Log.d(TAG, "onCreateViewHolder: " + );
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.item_view_type_first, null);
        Holder holder = new Holder(inflate);
        Log.d(TAG, "onCreateViewHolder: viewType=" + viewType + " 用时：" + (System.currentTimeMillis() - start) + "毫秒");

        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Log.d(TAG, "onBindViewHolder: getAdapterPosition=" + holder.getAdapterPosition());

        Student student2 = students.get(position);
//        holder.getLayoutPosition();

//        RecyclerView.NO_POSITION

        holder.tvContent.setText(student2.name());

    }

    @Override
    public int getItemCount() {
        return students.size();
    }


    class Holder extends RecyclerView.ViewHolder {

        TextView tvContent;

        public Holder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.item_view_type_content);
        }
    }
}
