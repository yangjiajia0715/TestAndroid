package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.testandroid.yang.R;
import com.testandroid.yang.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 配合javaee 下载
 * Created by yangjiajia on 2018/1/2.
 */

public class DownloadActivity extends BaseActivity {
    private static final String TAG = "DownloadActivity";

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.download_1)
    Button mDownload1;
    @BindView(R.id.download_2)
    Button mDownload2;
    @BindView(R.id.download_3)
    Button mDownload3;
    @BindView(R.id.download_4)
    Button mDownload4;
    @BindView(R.id.download_5)
    Button mDownload5;
    @BindView(R.id.download_6)
    Button mDownload6;
    private String mIpAddress;

    public static void start(Context context) {
        Intent starter = new Intent(context, DownloadActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);
        mIpAddress = getSharedPreferences(Constants.SHARE_PREFERENCE_NAME, MODE_PRIVATE)
                .getString(Constants.IP_ADDRESS, "");
        Log.d(TAG, "onCreate: mIpAddress=" + mIpAddress);
        if (TextUtils.isEmpty(mIpAddress)) {
            Toast.makeText(this, "请设置IP", Toast.LENGTH_SHORT).show();
        }

        initView();
        initData();
    }

    @Override
    public void initView() {
        mToolbarTitle.setText(R.string.download);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.download_1, R.id.download_2, R.id.download_3, R.id.download_4,
            R.id.download_5, R.id.download_6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.download_1:
                break;
            case R.id.download_2:
                break;
            case R.id.download_3:
                break;
            case R.id.download_4:
                break;
            case R.id.download_5:
                break;
            case R.id.download_6:
                break;
        }
    }
}
