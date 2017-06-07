package com.testandroid.yang.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * ApplicationContentProvider
 * Created by yangjiajia on 2017/5/18.
 */

public class ApplicationContentProvider extends ContentProvider {
    private static final String TAG = "ApplicationContentProvi";
    private static final int CODE_USER = 1000;
    private static final int CODE_USER_ID = 1001;
    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    static {
        sUriMatcher.addURI(UserContract.AUTHORITY, UserContract.Users.TABLE_NAME, CODE_USER);
        sUriMatcher.addURI(UserContract.AUTHORITY, UserContract.Users.TABLE_NAME + "/#", CODE_USER_ID);
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate: ");
        dbHelper = DBHelper.getInstance(getContext());
//        dbHelper.getWritableDatabase();//避免在该方面中调用，延时调用
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(TAG, "query: uri:" + uri);
//        Log.d(TAG, "query: ");
////        MatrixCursor matrixCursor;
//        uriMatcher = new UriMatcher(2);
//        uriMatcher.addURI("","",444);
////        ContactsContract.CommonDataKinds.Contactables;
//
//        uriMatcher.match(ContactsContract.Contacts.CONTENT_URI);
//        db.qu
//        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
//        queryBuilder.appendWhere("");
//        Cursor query = queryBuilder.query(db, null, null, null, null, null, null);

        db = dbHelper.getReadableDatabase();
        String lastPathSegment = uri.getLastPathSegment();
        List<String> pathSegments = uri.getPathSegments();
//        Log.d(TAG, "query: lastPathSegment=" + lastPathSegment);
//        Log.d(TAG, "query: pathSegments=" + pathSegments);

        // 实例化一个全局的ForceLoadContentObserver
        Cursor cursor = null;
        switch (sUriMatcher.match(uri)) {
            case CODE_USER:
                Log.d(TAG, "query: 用户表");
                cursor = db.query(UserContract.Users.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                //可行，
//                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                break;
            case CODE_USER_ID:
                Log.d(TAG, "query: 用户表 查询某行");
                Log.d(TAG, "query: 用户表 查询某行 selection " + selection);
                Log.d(TAG, "query: 用户表 查询某行 selectionArgs " + selectionArgs);

                selection = BaseColumns._ID + "=?";
                selectionArgs = new String[]{lastPathSegment};
                cursor = db.query(UserContract.Users.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("不支持的uri:" + uri);
        }
        return cursor;
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
        Log.d(TAG, "insert: uri=" + uri);
        db = dbHelper.getWritableDatabase();
        long row;
        Uri newUri = null;
        switch (sUriMatcher.match(uri)) {
            case CODE_USER:
                row = db.insert(UserContract.Users.TABLE_NAME, "干啥用nullColumnHack", values);
                Log.d(TAG, "insert: row=" + row);
                if (row > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                    newUri = ContentUris.withAppendedId(uri, row);
                }
                break;
            default:
                throw new IllegalArgumentException("不支持的uri=" + uri);
        }

        return newUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "delete: ");
//        getContext().getContentResolver().notifyChange(uri,null);
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "update: ");

        return 0;
    }


}
