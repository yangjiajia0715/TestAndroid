package com.testandroid.yang.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 数据库
 * Created by yangjiajia on 2017/5/18.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.d(TAG, "onOpen: 数据库打开了，做一些初始化操作.....");
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d(TAG, "DBHelper: ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: ");
//
        db.execSQL("create table " + UserContract.Users.TABLE_NAME + " ("
                + UserContract.Users._ID + " integer primary key autoincrement,"
                + UserContract.Users.USER_NAME + " text,"
                + UserContract.Users.AGE + " integer,"
                + UserContract.Users.PHONE_NUMBER + " text"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: ");

    }
}
