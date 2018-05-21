package com.testandroid.yang.fragment;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.testandroid.yang.R;
import com.testandroid.yang.activity.AJavaActivity;
import com.testandroid.yang.activity.AccountManagerActivity;
import com.testandroid.yang.activity.BroadcastReceiverActivity;
import com.testandroid.yang.activity.BuildConfigFieldActivity;
import com.testandroid.yang.activity.ClassNameActivity;
import com.testandroid.yang.activity.DateUtilActivity;
import com.testandroid.yang.activity.FileEncryptActivity;
import com.testandroid.yang.activity.GraphicsAndAnimationActivity;
import com.testandroid.yang.activity.MenuActivity;
import com.testandroid.yang.activity.MenuToolBarActivity;
import com.testandroid.yang.activity.MutilMediaActivity;
import com.testandroid.yang.activity.NotificationActivity;
import com.testandroid.yang.activity.OverviewScreen01Activity;
import com.testandroid.yang.activity.PDFCreateActivity;
import com.testandroid.yang.activity.ReflectActivity;
import com.testandroid.yang.activity.SavingFileActivity;
import com.testandroid.yang.activity.SelectFileActivity;
import com.testandroid.yang.activity.ShareSimpleDateActivity;
import com.testandroid.yang.activity.TempActivity;
import com.testandroid.yang.common.HomeInfo;

import java.io.File;

import static com.netease.nimlib.sdk.media.player.AudioPlayer.TAG;

/**
 * 其他
 * Created by yangjiajia on 2017/9/13.
 */

public class OtherFragment extends HomeBaseFragment {
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public static OtherFragment newInstance() {
        Bundle bundle = new Bundle();
        OtherFragment fragment = new OtherFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initView() {
        items.add(new HomeInfo("tv_buildconfig_field", R.id.tv_buildconfig_field, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("tv_temp", R.id.tv_temp, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("三种菜单", R.id.tv_menu, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("菜单&ToolBar", R.id.tv_menu_toolbar, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("pdf相关", R.id.tv_pdf, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("Overview Screen", R.id.tv_overview_screen, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("Notification", R.id.tv_notification, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("BroadcastReceiverActivity", R.id.tv_broadcastreceived, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("反射相关", R.id.tv_Reflect, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("分享数据", R.id.tv_share_date, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("分享文件列表", R.id.tv_share_select_file, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("保存文件相关", R.id.tv_save_file, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("文件加密", R.id.tv_file_jiami, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("多媒体相关", R.id.tv_multimedia, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("图形和动画", R.id.tv_graphics_and_animation, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("account_manage", R.id.tv_account_manage, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("DateUtil", R.id.tv_date_util, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("Java", R.id.tv_java, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("Handler", R.id.tv_handler, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("安装应用", R.id.tv_install_apk, HomeInfo.HomeGroup.Other));
        items.add(new HomeInfo("特殊类", R.id.tv_class_name, HomeInfo.HomeGroup.Other));
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent;
        if (v.getTag(R.id.tag_first) instanceof HomeInfo) {
            HomeInfo info = (HomeInfo) v.getTag(R.id.tag_first);
            switch (info.id) {
                case R.id.tv_buildconfig_field:
                    intent = new Intent(context, BuildConfigFieldActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_temp:
                    TempActivity.start(context);
                    break;
                case R.id.tv_menu:
                    MenuActivity.start(context);
                    break;
                case R.id.tv_menu_toolbar:
                    MenuToolBarActivity.start(context);
                    break;
                case R.id.tv_pdf:
                    PDFCreateActivity.start(context);
//                    OverviewScreen01Activity.start(context);
                    break;
                case R.id.tv_overview_screen:
                    OverviewScreen01Activity.start(context);
                    break;
                case R.id.tv_notification:
                    NotificationActivity.start(context);
                    break;
                case R.id.tv_broadcastreceived:
                    BroadcastReceiverActivity.start(context);
                    break;
                case R.id.tv_Reflect:
                    ReflectActivity.start(context);
                    break;
                case R.id.tv_share_date:
                    ShareSimpleDateActivity.start(context);
                    break;
                case R.id.tv_share_select_file:
                    SelectFileActivity.start(context);
                    break;
                case R.id.tv_save_file:
                    SavingFileActivity.start(context);
                    break;
                case R.id.tv_multimedia:
                    MutilMediaActivity.start(context);
                    break;
                case R.id.tv_graphics_and_animation:
                    GraphicsAndAnimationActivity.start(context);
//                    showDialog();
                    break;
                case R.id.tv_account_manage:
                    AccountManager manager = AccountManager.get(context);
                    Account[] accounts = manager.getAccountsByType(getString(R.string.account_type));
                    if (accounts.length == 0) {
                        String accountType = getString(R.string.account_type);
                        String authTokenType = getString(R.string.auth_token_type);
                        Log.d(TAG, "run: accountType=" + accountType);

//                        manager.addAccount(accountType, authTokenType, null, null, context, new AccountManagerCallback<Bundle>() {
//                            @Override
//                            public void run(AccountManagerFuture<Bundle> future) {
//                                try {
//                                    Log.d(TAG, "run: currentThread=" + Thread.currentThread().getName());
//                                    Log.d(TAG, "run: getResult=" + future.getResult());
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                    Log.d(TAG, "run: e=" + e.getMessage());
//                                }
//                            }
//                        }, new Handler());
                    } else {
                        Account curaccount = accounts[0];
                        for (Account account : accounts) {
                            Log.d(TAG, "onClick: account=" + account);
                            Toast.makeText(context, "onClick: account=" + account, Toast.LENGTH_SHORT).show();
                        }
                        AccountManagerActivity.start(context);
                    }
                    break;
                case R.id.tv_date_util:
                    DateUtilActivity.start(getActivity());
                    break;
                case R.id.tv_java:
                    AJavaActivity.start(getActivity());
                    break;
                case R.id.tv_file_jiami:
                    FileEncryptActivity.start(getActivity());
                    break;
                case R.id.tv_install_apk:

//                    File fileSource = new File(Environment.getExternalStorageDirectory(), "ws.apk");
                    File fileOut = new File(getActivity().getExternalFilesDir("apk_donwn"), "wsa.apk");

                    try {
//                        FileUtils.copyFile(fileSource, fileOut);
//
//                        if (!fileOut.exists()) {
//                            Toast.makeText(getActivity(), "文件不存在", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getActivity(), "存在", Toast.LENGTH_SHORT).show();
//
//                        }
                        Uri uriForFile = FileProvider.getUriForFile(getActivity(), "com.testandroid.yang.fileprovider", fileOut);

                        Log.d("OtherFragment", "OtherFragment----" + fileOut.exists());
                        Log.d("OtherFragment", "OtherFragment----uriForFile=" + uriForFile);
//                        File apk = new File(Environment.getExternalStorageDirectory(), "ttt.apk");

                        Intent intentApk = new Intent(Intent.ACTION_VIEW);
//                        intentApk.setDataAndType(Uri.fromFile(fileOut), "application/vnd.android.package-archive");
//                        intentApk.setDataAndType(Uri.fromFile(fileSource), "application/vnd.android.package-archive");
                        intentApk.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intentApk.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intentApk.setDataAndType(uriForFile, "application/vnd.android.package-archive");
                        startActivity(intentApk);

                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "复制出错", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    break;
                case R.id.tv_class_name:
                    ClassNameActivity.start(getActivity());
                    break;
            }
        }
    }
}
