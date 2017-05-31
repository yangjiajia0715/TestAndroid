package com.testandroid.yang.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * ApplicationContentProvider
 * Created by yangjiajia on 2017/5/18.
 */

public class ApplicationContentProvider extends ContentProvider {
    private static final String TAG = "ApplicationContentProvi";
    private static final int User = 1000;
    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    static {
        sUriMatcher.addURI(UserContract.AUTHORITY, "user", User);
    }


    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate: ");
        dbHelper = new DBHelper(getContext(), "TestAndroid.db", null, 1);
//        dbHelper.getWritableDatabase();//避免在该方面中调用，延时调用
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

//        Log.d(TAG, "query: ");
////        MatrixCursor matrixCursor;
//        uriMatcher = new UriMatcher(2);
//        uriMatcher.addURI("","",444);
////        ContactsContract.CommonDataKinds.Contactables;
//
//        uriMatcher.match(ContactsContract.Contacts.CONTENT_URI);
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d(TAG, "getType: ");
        return null;
    }

    //非抽象，A method that you're expected to implement if your provider offers files.
    @Nullable
    @Override
    public String[] getStreamTypes(@NonNull Uri uri, @NonNull String mimeTypeFilter) {
        return super.getStreamTypes(uri, mimeTypeFilter);
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (sUriMatcher.match(uri)) {
            case User:

                break;
            default:
                throw new IllegalArgumentException("不支持的uri=" + uri);
        }
        Log.d(TAG, "insert: uri=" + uri);
        db = dbHelper.getWritableDatabase();
        long row = db.insert("user", "干啥用nullColumnHack", values);
        Log.d(TAG, "insert: row=" + row);
        if (row > 0) {
            return ContentUris.withAppendedId(uri, row);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "delete: ");
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "update: ");
        return 0;
    }


}
