package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * GlideActivity
 * Created by yangjiajia on 2017/11/24.
 */

public class GlideActivity extends BaseActivity {

    @BindView(R.id.imageview1)
    ImageView imageview1;
    @BindView(R.id.imageview2)
    ImageView imageview2;
    @BindView(R.id.imageview3)
    ImageView imageview3;
    @BindView(R.id.imageview4)
    ImageView imageview4;
    @BindView(R.id.imageview5)
    ImageView imageview5;

    public static void start(Context context) {
        Intent starter = new Intent(context, GlideActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {

        String url = "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=2146927009,3405641870&fm=173&s=8E734D805C1202D6C6F52815030050C2&w=640&h=360&img.JPG";

        Glide.with(this).load(url).into(imageview1);
        Glide.with(this).load(url).placeholder(R.drawable.banner01).into(imageview2);
        Glide.with(this).load(url).placeholder(R.drawable.banner01).error(R.drawable.ic_done).into(imageview3);
        Glide.with(this).load(url).asGif().into(imageview4);
        Glide.with(this).load(url).asGif().placeholder(R.drawable.banner01).into(imageview5);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.imageview1, R.id.imageview2, R.id.imageview3, R.id.imageview4, R.id.imageview5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageview1:
                break;
            case R.id.imageview2:
                break;
            case R.id.imageview3:
                break;
            case R.id.imageview4:
                break;
            case R.id.imageview5:
                break;
        }
    }
}
