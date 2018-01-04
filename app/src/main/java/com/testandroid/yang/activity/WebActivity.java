package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 网页
 * Created by yangjiajia on 2018/1/3.
 */

public class WebActivity extends BaseActivity {
    private static final String TAG = "WebActivity";
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.web_view)
    WebView mWebView;

    public static void start(Context context,String url) {
        Intent starter = new Intent(context, WebActivity.class);
        starter.putExtra("url",url);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Log.d(TAG, "initData: url=" + url);
        if (URLUtil.isNetworkUrl(url)) {
            mWebView.loadUrl(url);
        } else {
            Toast.makeText(this, "无效的网址", Toast.LENGTH_SHORT).show();
        }
    }
}
