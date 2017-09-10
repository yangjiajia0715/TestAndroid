package com.testandroid.yang.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * SearchableActivity
 * Created by yangjiajia on 2017/9/10.
 */

public class SearchableActivity extends BaseActivity {
    private static final String TAG = "SearchableActivity";
//    public static void start(Context context) {
//        Intent starter = new Intent(context, SearchableActivity.class);
//        context.startActivity(starter);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String extra = intent.getStringExtra(SearchManager.QUERY);
            Log.d(TAG, "onCreate: extra=" + extra);

        }

        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
