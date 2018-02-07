package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.testandroid.yang.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * okio
 * Created by yangjiajia on 2018/2/6.
 */

public class OkIOActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.listview)
    ListView mListview;

    public static void start(Context context) {
        Intent starter = new Intent(context, OkIOActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_listview);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        mToolbarTitle.setText("OKIO");
    }

    @Override
    public void initData() {
        List<String> datas = new ArrayList<>();
        datas.add(0,"复制文件");
        datas.add(1,"");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas);
        mListview.setAdapter(adapter);
        mListview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
//                Buffer.class;
//                Okio.sink(null).close();
                break;
        }
    }
}
