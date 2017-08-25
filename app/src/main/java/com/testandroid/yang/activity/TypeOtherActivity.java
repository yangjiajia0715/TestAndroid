package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.testandroid.yang.R;
import com.testandroid.yang.adapter.HomeRecyleViewAdapter;
import com.testandroid.yang.common.HomeInfo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 数据库相关
 * Created by yangjiajia on 2017/3/1 0001.
 */

public class TypeOtherActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TypeOther";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;

    private List<HomeInfo> infos = new ArrayList<>();
    private List<HomeInfo> items;
    private HomeRecyleViewAdapter adapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, TypeOtherActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
        Log.d(TAG, "TypeOtherActivity--onCreate: getTaskId=" + getTaskId());
        cal();
    }

    private void cal() {
        float f = 100.26f;
        float f1 = 60f;
        float f2 = 60.1f;
        float f3 = 5.234f;
        float f4 = 150f;
        float f5 = 99f;
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        Log.d(TAG, "cal: " + decimalFormat.format(f));
        Log.d(TAG, "cal: " + decimalFormat.format(f1));
        Log.d(TAG, "cal: " + decimalFormat.format(f2));
        Log.d(TAG, "cal: " + decimalFormat.format(f3));
        Log.d(TAG, "cal: " + decimalFormat.format(f4));
        Log.d(TAG, "cal: " + decimalFormat.format(f5));

// java.lang.IllegalArgumentException: Bad class: class java.lang.String
//        Log.d(TAG, "cal 6: " + decimalFormat.format("88.82"));
    }

    @Override
    public void initView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        toolbar.setTitle(R.string.title_other);

        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void initData() {
        items = new ArrayList<>();

        infos.add(new HomeInfo("tv_buildconfig_field", R.id.tv_buildconfig_field, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("tv_temp", R.id.tv_temp, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("三种菜单", R.id.tv_menu, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("菜单&ToolBar", R.id.tv_menu_toolbar, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("pdf相关", R.id.tv_pdf, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("Overview Screen", R.id.tv_overview_screen, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("Notification", R.id.tv_notification, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("BroadcastReceiverActivity", R.id.tv_broadcastreceived, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("反射相关", R.id.tv_Reflect, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("分享数据", R.id.tv_share_date, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("分享文件列表", R.id.tv_share_select_file, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("保存文件相关", R.id.tv_save_file, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("多媒体相关", R.id.tv_multimedia, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("图形和动画", R.id.tv_graphics_and_animation, HomeInfo.HomeGroup.Other));
        infos.add(new HomeInfo("account_manage", R.id.tv_account_manage, HomeInfo.HomeGroup.Other));

        items.addAll(infos);
        adapter = new HomeRecyleViewAdapter(this, items);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getTag(R.id.tag_first) instanceof HomeInfo) {
            HomeInfo info = (HomeInfo) v.getTag(R.id.tag_first);
            switch (info.id) {
                case R.id.tv_buildconfig_field:
                    intent = new Intent(this, BuildConfigFieldActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_temp:
                    TempActivity.start(this);
                    break;
                case R.id.tv_menu:
                    MenuActivity.start(this);
                    break;
                case R.id.tv_menu_toolbar:
                    MenuToolBarActivity.start(this);
                    break;
                case R.id.tv_pdf:
                    PDFCreateActivity.start(this);
//                    OverviewScreen01Activity.start(this);
                    break;
                case R.id.tv_overview_screen:
                    OverviewScreen01Activity.start(this);
                    break;
                case R.id.tv_notification:
                    NotificationActivity.start(this);
                    break;
                case R.id.tv_broadcastreceived:
                    BroadcastReceiverActivity.start(this);
                    break;
                case R.id.tv_Reflect:
                    ReflectActivity.start(this);
                    break;
                case R.id.tv_share_date:
                    ShareSimpleDateActivity.start(this);
                    break;
                case R.id.tv_share_select_file:
                    SelectFileActivity.start(this);
                    break;
                case R.id.tv_save_file:
                    SavingFileActivity.start(this);
                    break;
                case R.id.tv_multimedia:
                    MutilMediaActivity.start(this);
                    break;
                case R.id.tv_graphics_and_animation:
                    GraphicsAndAnimationActivity.start(this);
//                    showDialog();
                    break;
                case R.id.tv_account_manage:
                    AccountManagerActivity.start(this);
                    break;
            }
        }
    }

    private void showDialog() {
//        AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.AnalysisReport)
//                .setSingleChoiceItems(R.array.sex, -1, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
//                .create();
//
//        Window window = alertDialog.getWindow();
//        if (window != null) {
//            window.setGravity(Gravity.BOTTOM);
//            WindowManager.LayoutParams params = window.getAttributes();
////            params.width = LinearLayout.LayoutParams.MATCH_PARENT;
//            window.setAttributes(params);
//        }
//
//        alertDialog.show();

        ListPopupWindow popupWindow = new ListPopupWindow(this);
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            dates.add("数据" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dates);
        popupWindow.setAdapter(adapter);
        popupWindow.setModal(true);
        popupWindow.setAnchorView(recyclerView);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.show();
    }
}
