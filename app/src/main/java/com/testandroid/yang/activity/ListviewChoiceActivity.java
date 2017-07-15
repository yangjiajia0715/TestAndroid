package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.testandroid.yang.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * listview 选择问题 综合
 * Created by yangjiajia on 2017/7/14.
 * 需求：点击跳转，可以多选/单选，选中的高亮
 */

public class ListviewChoiceActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "ListviewChoiceActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listview)
    ListView listview;

    public static void start(Context context) {
        Intent starter = new Intent(context, ListviewChoiceActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_choice);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_choice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_print:
                Log.d(TAG, "onOptionsItemSelected: item=" + item.getTitle());
                listview.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
                listview.setItemChecked(0, true);
                listview.clearChoices();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Log.d(TAG, "initView: density=" + metrics.density);
        Log.d(TAG, "initView: densityDpi=" + metrics.densityDpi);
        Log.d(TAG, "initView: scaledDensity=" + metrics.scaledDensity);
        Log.d(TAG, "initView: widthPixels=" + metrics.widthPixels);
        Log.d(TAG, "initView: heightPixels=" + metrics.heightPixels);

        List<String> dates = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dates.add("数据" + i);
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_listview_choice, android.R.id.text1, dates);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);

        listview.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                Log.d(TAG, "onItemCheckedStateChanged: position=" + position + " checked=" + checked);
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.menu_list_choice_action_mode, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_print_ppppppp:
                        listview.clearChoices();
                        adapter.notifyDataSetChanged();
                        return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LaunchModeA_Activity.start(this);
    }
}
