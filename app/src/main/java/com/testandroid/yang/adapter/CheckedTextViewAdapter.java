package com.testandroid.yang.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.testandroid.yang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author: yangjiajia
 * create time: 2016/9/23 0023.
 * desc:
 */

public class CheckedTextViewAdapter extends BaseAdapter {
    private static final String TAG = "CheckedTextViewAdapter";

    private Context context;

    private List<String> datas;

    private ListView listView;

    public CheckedTextViewAdapter(Context context, ListView listView) {
        this.context = context;

        this.listView = listView;

        datas = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            datas.add("错题会 i=" + i);
        }

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public String getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        String content = datas.get(position);

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_checked_textview, null, false);
            holder.textView = (TextView) convertView.findViewById(R.id.item_checked_textview_one);
            holder.checkedTextView = (CheckedTextView) convertView.findViewById(R.id.item_checked_textview_two);
            holder.checkedTextView.setCheckMarkDrawable(R.drawable.nim_contact_checkbox_checked_grey);
            holder.checkedTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.checkedTextView.toggle();

                    SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
                    if (checkedItemPositions != null) {

                        checkedItemPositions.put(position, holder.checkedTextView.isChecked());

                        boolean isChecked = checkedItemPositions.valueAt(position);
                        Log.d(TAG, " getView onClick: valueAt pos=" + position + " ,isChecked=" + isChecked + ",checkedTextView=" + holder.checkedTextView.isChecked());
                    }

                    Log.d(TAG, " getView onClick:isChecked=" + holder.checkedTextView.isChecked());

                    notifyDataSetChanged();
                }
            });

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(content);

        holder.checkedTextView.setText(content);

        SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
        if (checkedItemPositions != null) {
            if (checkedItemPositions.get(position)) {
                holder.checkedTextView.setCheckMarkDrawable(R.drawable.nim_contact_checkbox_checked_green);
            } else {
                holder.checkedTextView.setCheckMarkDrawable(R.drawable.nim_contact_checkbox_unchecked);
            }
        }

//        if (holder.checkedTextView.isChecked()) {
//            holder.checkedTextView.setCheckMarkDrawable(R.drawable.nim_contact_checkbox_checked_green);
//        } else {
//            holder.checkedTextView.setCheckMarkDrawable(R.drawable.nim_contact_checkbox_unchecked);
//        }

        return convertView;
    }

    static class ViewHolder {
        TextView textView;
        CheckedTextView checkedTextView;
    }

}
