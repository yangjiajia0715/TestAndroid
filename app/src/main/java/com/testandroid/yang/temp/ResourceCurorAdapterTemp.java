package com.testandroid.yang.temp;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.View;

/**
 * ResourceCurorAdapterTemp
 * Created by yangjiajia on 2017/9/11.
 * @see ResourceCursorAdapter
 */

public class ResourceCurorAdapterTemp extends ResourceCursorAdapter {

    public ResourceCurorAdapterTemp(Context context, int layout, Cursor c, int flags) {
        super(context, layout, c, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }

    @Override
    public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
        return super.runQueryOnBackgroundThread(constraint);
    }
}
