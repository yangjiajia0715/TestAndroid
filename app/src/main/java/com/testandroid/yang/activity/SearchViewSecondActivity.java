package com.testandroid.yang.activity;

import android.app.LoaderManager;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.v7.widget.SearchView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.testandroid.yang.R;
import com.testandroid.yang.provider.SearchRecentProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * SearchViewSecondActivity
 * Created by yangjiajia on 2017/9/13.
 */

public class SearchViewSecondActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {
    private static final String TAG = "SearchViewSecondActivit";

    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.add)
    Button add;
    private SimpleCursorAdapter adapter;
    private String query;

    public static void start(Context context) {
        Intent starter = new Intent(context, SearchViewSecondActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view_sec);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        SearchManager sm = (SearchManager) getSystemService(SEARCH_SERVICE);
        SearchableInfo searchableInfo = sm.getSearchableInfo(getComponentName());
        searchView.setSearchableInfo(searchableInfo);
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit: query=" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextChange: newText=" + newText);
                query = newText;
                adapter.getFilter().filter(newText);
                return false;
            }
        });

//        SearchRecentSuggestionsProvider
        String[] from = {SearchManager.SUGGEST_COLUMN_TEXT_1};
        int[] to = {android.R.id.text1};

        Cursor cursor = getSearchManagerSuggestions(searchableInfo, null, 100);
        Log.d(TAG, "initView: cursor=" + cursor);
        if (cursor != null) {
            Log.d(TAG, "initView: getCount=" + cursor.getCount());
            //不起作用
//            if (Build.VERSION.SDK_INT >= 19) {
//                Uri uri = cursor.getNotificationUri();
//                Log.d(TAG, "initView: uri=" + uri);
//
//                getContentResolver().registerContentObserver(uri, true, new ContentObserver(new Handler()) {
//                    @Override
//                    public boolean deliverSelfNotifications() {
//                        return super.deliverSelfNotifications();
//                    }
//
//                    @Override
//                    public void onChange(boolean selfChange) {
//                        super.onChange(selfChange);
//                        onChange(selfChange, null);
//                    }
//
//                    @Override
//                    public void onChange(boolean selfChange, Uri uri) {
//                        super.onChange(selfChange, uri);
//                        Log.d(TAG, "onChange: selfChange=" + selfChange + ",uri=" + uri);
//
//                    }
//                });
//            }
        }

//        cursor
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listview.setAdapter(adapter);

        //没调用
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                Log.d(TAG, "onChanged: ");
                super.onChanged();
            }

            @Override
            public void onInvalidated() {
                Log.d(TAG, "onInvalidated: ");
                super.onInvalidated();
            }
        });

        listview.setOnItemClickListener(this);
        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initData() {

    }

    /**
     * Import of hidden method: SearchManager.getSuggestions(SearchableInfo, String, int).
     */
    Cursor getSearchManagerSuggestions(SearchableInfo searchable, String query, int limit) {
        if (searchable == null) {
            return null;
        }

        String authority = SearchRecentProvider.AUTHORITY;////////////
//        String authority = searchable.getSuggestAuthority();
//        if (authority == null) {
//            return null;
//        }

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
//        uriBuilder.appendPath(SearchManager.SUGGEST_URI_PATH_QUERY);
        uriBuilder.appendPath(SearchManager.SUGGEST_URI_PATH_QUERY);

        // get the query selection, may be null
//        String selection = searchable.getSuggestSelection();
        String selection = " ?";
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

//        content://com.yang.SearchRecentProvider/search_suggest_query?limit=10
        Log.d(TAG, "getSearchManagerSuggestions: uri " + uri);
        // finally, make the query
        return getContentResolver().query(uri, null, selection, selArgs, null);
    }

    @OnClick(R.id.add)
    public void onViewClicked() {
        String dateTime = DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME);
        Log.d(TAG, "onViewClicked: dateTime=" + dateTime);
        SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this, SearchRecentProvider.AUTHORITY, SearchRecentProvider.MODE);
        suggestions.saveRecentQuery(dateTime, null);
//        SearchRecentSuggestionsProvider

//        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderID, Bundle bundle) {
        Log.d(TAG, "onCreateLoader: loaderID=" + loaderID);
        Uri.Builder uriBuilder = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(SearchRecentProvider.AUTHORITY)
                .appendPath(SearchManager.SUGGEST_URI_PATH_QUERY)
                .appendQueryParameter("limit", String.valueOf(100))
                .query("")  // TODO: Remove, workaround for a bug in Uri.writeToParcel()
                .fragment("");  // TODO: Remove, workaround for a bug in Uri.writeToParcel()

        Uri mDataUrl = uriBuilder.build();
        String selection = " ?";
        // inject query, either as selection args or inline
        String[] selArgs = null;
        if (selection != null) {    // use selection if provided
            selArgs = new String[]{query};
        } else {                    // no selection, use REST pattern
            uriBuilder.appendPath(query);
        }

        Log.d(TAG, "onCreateLoader: mDataUrl=" + mDataUrl);
        switch (loaderID) {
            case 0:
                // Returns a new CursorLoader
                return new CursorLoader(
                        this,   // Parent activity context
                        mDataUrl,        // Table to query
                        null,     // Projection to return
                        selection,            // No selection clause
                        selArgs,            // No selection arguments
                        null             // Default sort order
                );
            default:
                // An invalid id was passed in
                return null;
        }

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
        Log.d(TAG, "onLoadFinished: data=" + data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
        Log.d(TAG, "onLoaderReset: getId=" + loader.getId());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object item = adapter.getItem(position);
        if (item instanceof Cursor) {
            Cursor cursor = (Cursor) item;
            String string = null;
            try {
                string = cursor.getString(SearchRecentSuggestions.QUERIES_PROJECTION_DISPLAY1_INDEX);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d(TAG, "onItemClick: string=" + string);
            searchView.setQuery(string, true);
        }
    }
}
