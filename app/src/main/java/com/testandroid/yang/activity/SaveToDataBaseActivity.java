package com.testandroid.yang.activity;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.UserDictionary;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.common.User;
import com.testandroid.yang.db.UserContract;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 数据库交互
 * Created by yangjiajia on 2017/5/18.
 */

public class SaveToDataBaseActivity extends BaseActivity {

    private static final String TAG = "SaveToDataBaseActivity";
    @BindView(R.id.save_to_db0)
    TextView saveToDb0;
    @BindView(R.id.save_to_db1)
    TextView saveToDb1;
    @BindView(R.id.save_to_db2)
    TextView saveToDb2;
    @BindView(R.id.save_to_db3)
    TextView saveToDb3;
    @BindView(R.id.save_to_db4)
    TextView saveToDb4;
    @BindView(R.id.save_to_db5)
    TextView saveToDb5;
    @BindView(R.id.save_to_db6)
    TextView saveToDb6;
    @BindView(R.id.save_to_db7)
    TextView saveToDb7;
    private int appId = 1;
    private Random random;

    public static void start(Context context) {
        Intent starter = new Intent(context, SaveToDataBaseActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_to_db);
        ButterKnife.bind(this);
        random = new Random();
        initView();
        initData();

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.save_to_db0, R.id.save_to_db1, R.id.save_to_db2, R.id.save_to_db3, R.id.save_to_db4, R.id.save_to_db5, R.id.save_to_db6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.save_to_db0:

                break;
            case R.id.save_to_db1:
                ContentResolver contentResolver = getContentResolver();
                Log.d(TAG, "onViewClicked: contentResolver=" + contentResolver);

//                contentResolver.fi
//                contentResolver.insert()
                Cursor cursor = contentResolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);
                int count = cursor.getCount();
                Log.d(TAG, "onViewClicked: count=" + count);
                Uri uri = UserDictionary.Words.CONTENT_URI;

                Log.d(TAG, "onViewClicked: uri=" + uri);
                Uri withAppendedId = ContentUris.withAppendedId(uri, 2);
                Log.d(TAG, "onViewClicked: withAppendedId=" + withAppendedId);
                long parseId = ContentUris.parseId(withAppendedId);
                Log.d(TAG, "onViewClicked: parseId=" + parseId);

                int columnIndex = cursor.getColumnIndex(UserDictionary.Words.WORD);
                String string = cursor.getString(columnIndex);

                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        String string1 = cursor.getString(columnIndex);

                        int type = cursor.getType(columnIndex);
                        if (Cursor.FIELD_TYPE_BLOB == type) {

                        }

                    }
                }
//                UserDictionary.Words.addWord();
                break;
            case R.id.save_to_db2:
//                new SimpleCursorAdapter(getApplication(),R.layout.item_school_list,)
                break;
            case R.id.save_to_db3:
                saveWord();
                break;
            case R.id.save_to_db4:
                ContentProviderOperation contentProviderOperation;
                ArrayList<ContentProviderOperation> list = new ArrayList<>();
                try {
                    getContentResolver().applyBatch("", list);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (OperationApplicationException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.save_to_db5:
//                random.nextInt(10000);
//                10000
                int nextInt = random.nextInt(100);
                Log.d(TAG, "onViewClicked: nextint=" + nextInt);
                final User user = new User();
                user.name = "用户" + nextInt;
                user.age = nextInt;
                user.phoneNumber = String.valueOf(nextInt);
                Log.d(TAG, "onViewClicked: user=" + user);
                UserContract.Users.addUser(getApplicationContext(), user);
                break;
            case R.id.save_to_db6:

                break;
        }
    }

    private void saveWord() {
        appId++;

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserDictionary.Words.APP_ID, appId);
        contentValues.put(UserDictionary.Words.WORD, "张三" + appId);

        contentValues.putNull(UserDictionary.Words.CONTENT_TYPE);
//        contentValues.putNull("");
        Uri uri = UserDictionary.Words.CONTENT_URI;

        Uri insert = getContentResolver().insert(uri, contentValues);

//        getContentResolver().update()
        ContactsContract.Data data;
        Log.d(TAG, "saveWord: insert=" + insert);


    }
}
