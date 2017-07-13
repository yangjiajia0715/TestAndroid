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
    private SparseBooleanArray sparseBooleanArray;
    private Context context;
    private List<String> datas;
    private ListView listView;

    public CheckedTextViewAdapter(Context context, ListView listView) {
        this.context = context;

        this.listView = listView;

        datas = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            datas.add("错题会内容有点常常长加接口斤斤计 i=" + i);
        }
    }

    public SparseBooleanArray getSparseBooleanArray() {
        return sparseBooleanArray;
    }

    public void setSparseBooleanArray(SparseBooleanArray sparseBooleanArray) {
        this.sparseBooleanArray = sparseBooleanArray;
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
    public View getView( int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        Log.d(TAG, "getView: position=" + position + "  sparseBooleanArray=" + sparseBooleanArray );
        String content = datas.get(position);

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_checked_textview, null, false);
            holder.textView = (TextView) convertView.findViewById(R.id.item_checked_textview_one);
            holder.checkedTextView = (CheckedTextView) convertView.findViewById(R.id.item_checked_textview_two);
            holder.checkedTextView.setCheckMarkDrawable(R.drawable.selector_nim);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(content);
        holder.checkedTextView.setText(content);

        if (sparseBooleanArray != null) {
            boolean checked = sparseBooleanArray.get(position);
            holder.checkedTextView.setChecked(checked);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView textView;
        CheckedTextView checkedTextView;
    }

}
