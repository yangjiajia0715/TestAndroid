package com.testandroid.yang.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.common.ImageItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ImageAdapter
 * Created by yangjiajia on 2017/7/24.
 */

public class ImageAdapter extends BaseAdapter {
    private List<ImageItem> dates;

    public ImageAdapter(List<ImageItem> dates) {
        this.dates = dates;
    }

    @Override
    public int getCount() {
        return dates == null ? 0 : dates.size();
    }

    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageItem imageItem = dates.get(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, null, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (imageItem.getUri() == null) {
            holder.image.setVisibility(View.GONE);
        } else {
            holder.image.setVisibility(View.VISIBLE);
        }

        holder.text.setText(imageItem.getName());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.text)
        TextView text;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
