package com.testandroid.yang.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

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
            Log.d(TAG, "onCreate: db" + db);
            db.execSQL("CREATE TABLE " + TopicContract.TABLE_NAME + "("
                    + TopicContract.Topic._ID + " INTEGER AUTO_INCREMENT,"
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
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
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
