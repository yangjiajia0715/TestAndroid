package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * BottomSheetActivity
 * Created by yangjiajia on 2017/4/8 0008.
 */

public class BottomSheetActivity extends BaseActivity {
    private static final String TAG = "BottomSheetActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottom_sheet_01)
    TextView bottomSheet01;
    @BindView(R.id.bottom_sheet_02)
    TextView bottomSheet02;
    @BindView(R.id.bottom_sheet_03)
    TextView bottomSheet03;
    @BindView(R.id.bottom_sheet_04)
    TextView bottomSheet04;
    @BindView(R.id.sheet_layout)
    View sheetLayout;
    private ActionMode.Callback callback;
    ActionMode actionMode;
    private BottomSheetBehavior<View> sheetBehavior;

    public static void start(Context context) {
        Intent starter = new Intent(context, BottomSheetActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {

//        setSupportActionBar(toolbar);

        sheetBehavior = BottomSheetBehavior.from(sheetLayout);

//        sheetBehavior.setPeekHeight(200);

        //显示全部：不是9:16
        sheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.d(TAG, "onStateChanged: newState=" + newState);
//                BottomSheetBehavior.STATE_COLLAPSED
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.d(TAG, "onSlide: slideOffset=" + slideOffset);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.startActionMode(callback);
            }
        });

        callback = new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.menu_action_mode, menu);
                actionMode = mode;
                actionMode.setTitle("标题稍微有点长标题稍微有点长");
                actionMode.setSubtitle("子标题稍微有点长子标题稍微有点长");
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                Log.d(TAG, "onActionItemClicked: item=" + item);
                switch (item.getItemId()) {
                    case R.id.menu_action_mode_1:
//                        actionMode.finish();
                        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        break;
                    case R.id.menu_action_mode_2:
                        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        break;
                    case R.id.menu_action_mode_3:
                        break;
                    case R.id.menu_action_mode_4:
                        sheetBehavior.setPeekHeight(50 * 3);
                        break;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        };


    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.bottom_sheet_01, R.id.bottom_sheet_02, R.id.bottom_sheet_03, R.id.bottom_sheet_04})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bottom_sheet_01:
                break;
            case R.id.bottom_sheet_02:
                break;
            case R.id.bottom_sheet_03:
                break;
            case R.id.bottom_sheet_04:
                break;
        }
    }
}
