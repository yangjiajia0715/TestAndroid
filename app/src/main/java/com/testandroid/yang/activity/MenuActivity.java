package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 三种菜单
 * Created by yangjiajia on 2017/2/19 0019.
 */

public class MenuActivity extends BaseActivity {
    private static final String TAG = "MenuActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_option_menu)
    TextView tvOptionMenu;
    @BindView(R.id.tv_context_menu)
    TextView tvContextMenu;
    @BindView(R.id.tv_pop_menu)
    TextView tvPopMenu;


    public static void start(Context context) {
        Intent starter = new Intent(context, MenuActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    @Override
    public void initView() {
        Log.d(TAG, "initView: toolBar=" + toolbar);

        if (toolbar == null)
            toolbar = (Toolbar) findViewById(R.id.toolbar);

        Log.d(TAG, "initView: toolBar=" + findViewById(R.id.toolbar));

        toolbar.setTitle(R.string.text_three_menu);

        setSupportActionBar(toolbar);

        if (tvContextMenu == null) {
            tvContextMenu = (TextView) findViewById(R.id.tv_context_menu);
        }
//        tvOptionMenu.setLongClickable(this);

        registerForContextMenu(tvContextMenu);
    }

    //optionmenu 每次show之前都会调用该方法，或者，invalidate
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        Log.d(TAG, "onCreateOptionsMenu: menu=" + menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.add("动态添加");
        Log.d(TAG, "onPrepareOptionsMenu: menu=" + menu);
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.d(TAG, "onCreateContextMenu: menu=" + menu + ",v=" + v + ",menuInfo=" + menuInfo );
        getMenuInflater().inflate(R.menu.context_ment,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.d(TAG, "onContextItemSelected: item=" + item);
        switch (item.getItemId()) {
            case R.id.drop_down_answer:
                Toast.makeText(this, "drop_down_answer上下文菜单", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.drop_down_setting:
                Toast.makeText(this, "drop_down_setting上下文菜单", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drop_down_answer:
                Toast.makeText(this, "drop_down_answer", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.drop_down_setting:
                Toast.makeText(this, "drop_down_setting", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void initData() {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_context_menu:
                break;
            case R.id.tv_option_menu:
//                invalidateOptionsMenu();
                break;
            case R.id.tv_pop_menu:
                break;
        }

    }

}
