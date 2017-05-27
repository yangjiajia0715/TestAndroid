package com.testandroid.yang.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * ApplicationContentProvider
 * Created by yangjiajia on 2017/5/18.
 */

public class ApplicationContentProvider extends ContentProvider {
    private static final String TAG = "ApplicationContentProvi";
    private static UriMatcher uriMatcher;

    @Override
    public boolean onCreate() {

        Log.d(TAG, "onCreate: ");
        DBHelper dbHelper = new DBHelper(getContext(), "TestAndroid.db", null, 1);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        uriMatcher = new UriMatcher(2);
        uriMatcher.addURI("","",444);
//        ContactsContract.CommonDataKinds.Contactables;

        uriMatcher.match(ContactsContract.Contacts.CONTENT_URI);
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }


}
