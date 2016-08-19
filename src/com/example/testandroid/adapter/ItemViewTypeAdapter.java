package com.example.testandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.testandroid.R;

/**
 * author: yangjiajia
 * create time: 2016/8/17 0017
 * desc:
 */
public class ItemViewTypeAdapter extends BaseAdapter {
    LayoutInflater inflater;
    public ItemViewTypeAdapter(Context context) {
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 1) {
            return 1;
        }else {
            return 2;
        }
//        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        int type = getItemViewType(position);
        if (type == 1) {
            View inflate = inflater.inflate(R.layout.item_view_type_first, null, false);
            TextView tvContext = (TextView) inflate.findViewById(R.id.item_view_type_content);
            tvContext.setText("奇数"+ position);
            return inflate;
        }else {
            View inflate = inflater.inflate(R.layout.item_view_type_two, null, false);
            TextView tvContext = (TextView) inflate.findViewById(R.id.item_view_type_content);
            tvContext.setText("偶数"+ position);
            return inflate;
        }

//        return null;
    }
}
