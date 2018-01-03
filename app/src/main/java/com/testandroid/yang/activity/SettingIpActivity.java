package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.testandroid.yang.R;
import com.testandroid.yang.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置IP
 * Created by yangjiajia on 2018/1/2.
 */

public class SettingIpActivity extends BaseActivity {

    @BindView(R.id.ip_address)
    EditText mIpAddress;
    @BindView(R.id.save)
    Button mSave;

    public static void start(Context context) {
        Intent starter = new Intent(context, SettingIpActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_ip);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.save)
    public void onViewClicked() {
        String ip = mIpAddress.getText().toString().trim();
        if (TextUtils.isEmpty(ip)) {
            Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        getSharedPreferences(Constants.SHARE_PREFERENCE_NAME, MODE_PRIVATE)
                .edit()
                .putString(Constants.IP_ADDRESS, ip)
                .apply();

        DownloadActivity.start(this);
    }
}
