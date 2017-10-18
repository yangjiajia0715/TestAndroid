package com.testandroid.yang.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * 添加错题
 * 考虑是否需要provider
 * 可能给其他应用使用 需要：
 * 1，authority
 * 2, 表结构 合约类 Contract
 * 3, SqliteOpenHelper 创建数据库
 * 4，重写contentProvider
 * <p>
 * 数据更新 notify
 * Created by yangjiajia on 2017/10/10.
 */

public class TopicContentProvider extends ContentProvider {
    private static final String TAG = "TopicContentProvider";
    public final static String AUTHORITY = "com.yang.TopicContentProvider";
    private final static String DB_NAME = "topic.db";
    private final static int DB_VERSION = 1;
    private TopicSQLiteOpenHelper sqLiteOpenHelper;

    public class TopicSQLiteOpenHelper extends SQLiteOpenHelper {
        public TopicSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(TAG, "onCreate: db=" + db);
            db.execSQL("CREATE TABLE " + TopicContract.TABLE_NAME + "("
                    + TopicContract.Topic._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + TopicContract.Topic.NAME + " TEXT,"
                    + TopicContract.Topic.TOPIC_ID + " INTEGER"
                    + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate: " + getClass().getSimpleName());
        sqLiteOpenHelper = new TopicSQLiteOpenHelper(getContext(), DB_NAME, null, DB_VERSION);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(TopicContract.TABLE_NAME, projection, selection, selectionArgs,
                null, null, sortOrder);

        if (getContext() != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG, "insert: uri=" + uri);//content://com.yang.TopicContentProvider/topic
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        long rowID = -1;
        rowID = db.insert(TopicContract.TABLE_NAME, null, values);
        Uri newUri = null;
        Log.d(TAG, "insert: rowID=" + rowID);
        if (rowID > 0) {
//            newUri =  ContentUris.withAppendedId(uri, rowID);
            //content://com.yang.TopicContentProvider/topic/4
            Log.d(TAG, "insert: withAppendedId=" + ContentUris.withAppendedId(uri, rowID));
            newUri = Uri.withAppendedPath(uri, String.valueOf(rowID));
            //content://com.yang.TopicContentProvider/topic/4
            Log.d(TAG, "insert: withAppendedPath=" + newUri);
        }
        if (rowID < 0) {
            throw new IllegalArgumentException("Unknown Uri");
        }
//        getContext().getContentResolver().notifyChange(newUri, null);
        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(newUri, null);
        }
        return newUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        int count = 0;
        List<String> pathSegments = uri.getPathSegments();
        String encodedPath = uri.getEncodedPath();
        Log.d(TAG, "delete: pathSegments=" + pathSegments);
        Log.d(TAG, "delete: encodedPath=" + encodedPath);
        Log.d(TAG, "delete: selection=" + selection);
        Log.d(TAG, "delete: selectionArgs=" + selectionArgs);
        count = db.delete(TopicContract.TABLE_NAME, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(TopicContract.CONTENT_URI, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
