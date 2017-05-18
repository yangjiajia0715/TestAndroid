package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 数据库交互
 * Created by yangjiajia on 2017/5/18.
 */

public class SaveToDataBaseActivity extends BaseActivity {

    @BindView(R.id.save_to_db0)
    TextView saveToDb0;
    @BindView(R.id.save_to_db1)
    TextView saveToDb1;
    @BindView(R.id.save_to_db2)
    TextView saveToDb2;
    @BindView(R.id.save_to_db3)
    TextView saveToDb3;
    @BindView(R.id.save_to_db4)
    TextView saveToDb4;
    @BindView(R.id.save_to_db5)
    TextView saveToDb5;
    @BindView(R.id.save_to_db6)
    TextView saveToDb6;
    @BindView(R.id.save_to_db7)
    TextView saveToDb7;

    public static void start(Context context) {
        Intent starter = new Intent(context, SaveToDataBaseActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_to_db);
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

    @OnClick({R.id.save_to_db0, R.id.save_to_db1, R.id.save_to_db2, R.id.save_to_db3, R.id.save_to_db4, R.id.save_to_db5, R.id.save_to_db6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.save_to_db0:

                break;
            case R.id.save_to_db1:
                break;
            case R.id.save_to_db2:
                break;
            case R.id.save_to_db3:
                break;
            case R.id.save_to_db4:
                break;
            case R.id.save_to_db5:
                break;
            case R.id.save_to_db6:
                break;
        }
    }
}
