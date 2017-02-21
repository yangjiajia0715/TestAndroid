package com.testandroid.yang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.testandroid.yang.R;

import java.util.List;

/**
 * Created by yangjiajia on 2017/2/21 0021.
 */

public class MultiChoiceAdapter extends BaseAdapter {
    private Context context;
    private List<String> datas;
    private LayoutInflater inflater;

    ListView listviewRight;

    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;

    public void setOnCheckedChangeListenertt(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    public MultiChoiceAdapter(Context context, List<String> datas, ListView listviewRight) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
        this.listviewRight = listviewRight;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_multi_choice, null);
        }

        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.item_multi_choice_check_box);
        TextView textView = (TextView) convertView.findViewById(R.id.item_multi_choice_content);

        String s = datas.get(position);
        textView.setText(s);
//        if (onCheckedChangeListener != null) {
////            checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
//        }
        checkBox.setTag(position);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               int pos = (int) buttonView.getTag();
                listviewRight.setItemChecked(pos, isChecked);
            }
        });
        return convertView;
    }
}
