package com.example.testandroid.activity;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import com.example.testandroid.R;

/**
 * Loader
 * Created by yangjiajia on 2016/12/2 0002.
 */

public class CursorLoadActivity extends Activity implements LoaderManager.LoaderCallbacks<Object> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor_load);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(0, null, this);
    }

    @Override
    public Loader<Object> onCreateLoader(int id, Bundle bundle) {

        Loader<Object> loader = new Loader<Object>(this) {

        };


        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object data) {

    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {
    }
}
