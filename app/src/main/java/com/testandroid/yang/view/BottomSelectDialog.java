package com.testandroid.yang.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.common.IBottomSelectItem;

import java.util.List;

/**
 * 底部选择菜单
 * author: yangjiajia
 * create time: 2016/7/5.
 * description:
 * modify time: 2016/7/5 11:01
 * mofify time:2016-11-14 设置item,抽象
 */
public class BottomSelectDialog extends Dialog implements AdapterView.OnItemClickListener, View.OnClickListener {
    private Context mContext;
    private ListView mListView;
    private View tvCancel;
    private List<IBottomSelectItem> mList;

    private AdapterView.OnItemClickListener itemClickListener;

    public void setBottomItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public BottomSelectDialog(Context context, List<IBottomSelectItem> list) {
        this(context, R.style.bottom_selcect_dialog_theme, list);
    }

    public BottomSelectDialog(Context context, int theme, List<IBottomSelectItem> list) {
        super(context, theme);
        mContext = context;
        mList = list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.dialog_bottom_select_dialog);

        mListView = (ListView) findViewById(R.id.bottom_select_dialog_listview);
//        mListView.setOnItemClickListener(itemClickListener == null ? this : itemClickListener);
        mListView.setOnItemClickListener(this);
        tvCancel = findViewById(R.id.cancel_btn);
        tvCancel.setOnClickListener(this);

        if (getWindow() != null) {
            getWindow().setWindowAnimations(R.style.dialogWindowAnim);
            getWindow().setGravity(Gravity.BOTTOM);// 底部显示
        }

        android.view.WindowManager.LayoutParams attributes = getWindow().getAttributes();
//        attributes.width = Utility.getScreenWidth(mContext);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        attributes.width = metrics.widthPixels;
        getWindow().setAttributes(attributes);
    }

    private void initData() {
        BottomSelectAdapter selectAdapter = new BottomSelectAdapter(mList);
        mListView.setAdapter(selectAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        dismiss();
        if (itemClickListener != null) {
            itemClickListener.onItemClick(parent, view, position, id);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_btn:
                dismiss();
                break;
        }
    }

    private class BottomSelectAdapter extends BaseAdapter {

        private List<IBottomSelectItem> items;
        private LayoutInflater inflater;

        private BottomSelectAdapter(List<IBottomSelectItem> items) {
            this.items = items;
            inflater = LayoutInflater.from(getContext());
        }

        @Override
        public int getCount() {
            return items == null ? 0 : items.size();
        }

        @Override
        public IBottomSelectItem getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            IBottomSelectItem item = items.get(position);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_bottom_select, null, false);
            }

            TextView text = (TextView) convertView;
            text.setText(item.getName());

            if (item.getItemId() > 0) {
                convertView.setId(item.getItemId());
            }
            return convertView;
        }
    }

}
