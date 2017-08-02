package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.testandroid.yang.R;
import com.testandroid.yang.adapter.AnalysisReportListAdapter;
import com.testandroid.yang.adapter.decoration.MyItemDecoration;
import com.testandroid.yang.common.ATestReportItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义 吸顶效果 eg.联系人
 * Created by yangjiajia on 2017/8/2.
 *
 * @see AnalysisReportListAdapter
 */

public class RecycleViewCeilingActivity extends BaseActivity {


    @BindView(R.id.recycleview)
    RecyclerView recycleview;

    public static void start(Context context) {
        Intent starter = new Intent(context, RecycleViewCeilingActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview_ceiling);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        recycleview.setLayoutManager(new LinearLayoutManager(this));

        List<ATestReportItem> dates = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dates.add(new ATestReportItem("张三" + i, "" + (i % 3)));
        }
        AnalysisReportListAdapter adapter = new AnalysisReportListAdapter(dates);
        recycleview.setAdapter(adapter);
        MyItemDecoration decoration = new MyItemDecoration();
//        SectionIndexer
        recycleview.addItemDecoration(decoration);
    }

    @Override
    public void initData() {

    }
}
