package com.testandroid.yang.activity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.testandroid.yang.R;
import com.testandroid.yang.db.UserContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 数据展示页--内部&外部
 * Created by yangjiajia on 2017/6/6 0006.
 */

public class DisplayUserInfoActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "DisplayUserInfoActivity";
    private static final int LOADER_USER = 1;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listview)
    ListView listview;
    private SimpleCursorAdapter adapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, DisplayUserInfoActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_data);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    @Override
    public void initView() {
        AdapterViewFlipper adapterViewFlipper;
//        new CursorLoader(this).re

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoaderManager().restartLoader(LOADER_USER, null, DisplayUserInfoActivity.this);
            }
        });

        String[] from = {UserContract.Users.AGE, UserContract.Users.USER_NAME};
        int[] to = {R.id.age, R.id.name};
        adapter = new SimpleCursorAdapter(this, R.layout.item_dispaly_user, null, from, to, 0);
        listview.setAdapter(adapter);
    }

    @Override
    public void initData() {
//        getContentResolver().notifyChange(UserContract.Users.CONTENT_URI, new ContentObserver() {
//            @Override
//            public void onChange(boolean selfChange) {
//                super.onChange(selfChange);
//            }
//        });
        getLoaderManager().initLoader(LOADER_USER, null, this);
    }

    //都是在主线程
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader: id " + id + "  getName=" + Thread.currentThread().getName());
        switch (id) {
            case LOADER_USER:
                CursorLoader cursorLoader = new CursorLoader(this, UserContract.Users.CONTENT_URI, null, null, null, null);
//                cursorLoader.setUpdateThrottle(100);
                cursorLoader.loadInBackground();
                return cursorLoader;
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "onLoadFinished: " + Thread.currentThread().getName());
        adapter.changeCursor(data);

        data.registerContentObserver(new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                Log.d(TAG, "onChange: selfChange=" + selfChange);
            }

            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                Log.d(TAG, "onChange: uri=" + uri);
            }
        });

        data.setNotificationUri(getContentResolver(), UserContract.Users.CONTENT_URI);
//        data.setNotificationUri(getContentResolver(), UserContract.Users.CONTENT_URI);
//        adapter.swapCursor(data);
    }

    //This method is invoked when CursorLoader detects that data associated with the Cursor has changed.
    // When the data changes, the framework also re-runs the current query.
    //主线程 main
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d(TAG, "onLoaderReset:  getId=" + loader.getId());
        adapter.changeCursor(null);
    }

}
