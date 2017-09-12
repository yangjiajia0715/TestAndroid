package com.testandroid.yang.activity;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * SearchViewActivity
 * Created by yangjiajia on 2017/9/8.
 */

public class SearchViewActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "SearchViewActivity";
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.back_arrow)
    View backArrow;
    @BindView(R.id.btn_search)
    View btnSearch;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.req_btn)
    TextView reqBtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

//        searchView.setSubmitButtonEnabled(true);
        EditText srcText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        srcText.setTextSize(13);
        srcText.setTextColor(Color.BLUE);
//        searchView.setSuggestionsAdapter();

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

//        searchView.setQueryRefinementEnabled(true);

        searchView.postDelayed(new Runnable() {
            @Override
            public void run() {
                CursorAdapter suggestionsAdapter = searchView.getSuggestionsAdapter();
                listview.setAdapter(suggestionsAdapter);
            }
        }, 1000);

        Log.d(TAG, "initView: getComponentName=" + getComponentName());

        searchManager.setOnDismissListener(new SearchManager.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.d(TAG, "onDismiss: 消失对话框");
            }
        });

        searchManager.setOnCancelListener(new SearchManager.OnCancelListener() {
            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel: 明确返回时调用");
            }
        });

        ImageView mag_icon = (ImageView) findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
        mag_icon.setPadding(0, 0, 0, 0);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams.set
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        mag_icon.setLayoutParams(layoutParams);
//        ViewGroup.LayoutParams layoutParams = mag_icon.getLayoutParams();

        ImageView closebtn = (ImageView) findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        closebtn.setPadding(0, 0, 0, 0);

        ImageView goBtn = (ImageView) findViewById(android.support.v7.appcompat.R.id.search_go_btn);
        goBtn.setPadding(0, 0, 0, 0);

        btnSearch.setOnClickListener(this);
    }

    @Override
    public void initData() {
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());

        Cursor cursor = getSearchManagerSuggestions(searchableInfo, "", 10);
        if (cursor != null && cursor.getCount() > 0) {
            Log.d(TAG, "initData: getCount" + cursor.getCount());
            while (cursor.moveToNext()) {

            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                CharSequence query = searchView.getQuery();
                Log.d(TAG, "onViewClicked: query=" + query);
                if (query == null || TextUtils.getTrimmedLength(query) == 0) {
                    Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

//                searchView.setAppSearchData();
                searchView.setQuery(query, true);

                Log.d(TAG, "onViewClicked: query 2=" + searchView.getQuery());
                break;
        }
    }

    @OnClick(R.id.req_btn)
    public void onViewClicked() {
        boolean requested = onSearchRequested();
        Log.d(TAG, "onViewClicked: requested=" + requested);
    }

    @Override
    public boolean onSearchRequested() {
        Log.d(TAG, "onSearchRequested: ");
        Bundle appData = new Bundle();
//        appData.putBoolean(SearchManager.APP_DATA,true);
        appData.putBoolean("nb", true);
        startSearch(null, false, appData, false);
        return true;
//        return super.onSearchRequested();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_vew, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search_view);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_search_view:
//
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    SuggestionsAdapter#getSearchManagerSuggestions(android.app.SearchableInfo, java.lang.String, int)

    /**
     * Import of hidden method: SearchManager.getSuggestions(SearchableInfo, String, int).
     */
    Cursor getSearchManagerSuggestions(SearchableInfo searchable, String query, int limit) {
        if (searchable == null) {
            return null;
        }

        String authority = searchable.getSuggestAuthority();
        if (authority == null) {
            return null;
        }

        Uri.Builder uriBuilder = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(authority)
                .query("")  // TODO: Remove, workaround for a bug in Uri.writeToParcel()
                .fragment("");  // TODO: Remove, workaround for a bug in Uri.writeToParcel()

        // if content path provided, insert it now
        final String contentPath = searchable.getSuggestPath();
        if (contentPath != null) {
            uriBuilder.appendEncodedPath(contentPath);
        }

        // append standard suggestion query path
        uriBuilder.appendPath(SearchManager.SUGGEST_URI_PATH_QUERY);

        // get the query selection, may be null
        String selection = searchable.getSuggestSelection();
        // inject query, either as selection args or inline
        String[] selArgs = null;
        if (selection != null) {    // use selection if provided
            selArgs = new String[]{query};
        } else {                    // no selection, use REST pattern
            uriBuilder.appendPath(query);
        }

        if (limit > 0) {
            uriBuilder.appendQueryParameter("limit", String.valueOf(limit));
        }

        Uri uri = uriBuilder.build();

        // finally, make the query
        return getContentResolver().query(uri, null, selection, selArgs, null);
    }

}
