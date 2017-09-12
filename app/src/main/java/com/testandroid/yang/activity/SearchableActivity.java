package com.testandroid.yang.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.util.Log;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.provider.SearchRecentProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * SearchableActivity
 * Created by yangjiajia on 2017/9/10.
 */

public class SearchableActivity extends BaseActivity {
    private static final String TAG = "SearchableActivity";
    @BindView(R.id.content)
    TextView content;
//    public static void start(Context context) {
//        Intent starter = new Intent(context, SearchableActivity.class);
//        context.startActivity(starter);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d(TAG, "onCreate: query=" + query);

            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    SearchRecentProvider.AUTHORITY, SearchRecentProvider.MODE);
            suggestions.saveRecentQuery(query, null);

            content.append("query=" + query);
            Bundle bundle = intent.getBundleExtra(SearchManager.APP_DATA);
            if (bundle != null) {
                content.append("\nbundle=" + bundle.getBoolean("nb",false));
            }

        }

        content.append("\ngetTaskId=" + getTaskId());
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
