package com.testandroid.yang.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * SearchViewActivity
 * Created by yangjiajia on 2017/9/8.
 */

public class SearchViewActivity extends BaseActivity {
    private static final String TAG = "SearchViewActivity";
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.back_arrow)
    ImageButton backArrow;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.req_btn)
    TextView reqBtn;

    public static void start(Context context) {
        Intent starter = new Intent(context, SearchViewActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
//        ImageView searchButton = (ImageView) findViewById(android.support.v7.appcompat.R.id.search_button);
//        searchButton.setImageResource(R.drawable.iv_search);

        searchView.setSubmitButtonEnabled(true);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        Log.d(TAG, "initView: getComponentName=" + getComponentName());


    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.req_btn)
    public void onViewClicked() {
        boolean requested = onSearchRequested();
        Log.d(TAG, "onViewClicked: requested=" + requested);

    }
}
