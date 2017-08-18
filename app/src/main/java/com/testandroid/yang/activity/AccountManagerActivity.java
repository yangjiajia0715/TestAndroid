package com.testandroid.yang.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.testandroid.yang.R;
import com.testandroid.yang.account.OnTokenAcquired;
import com.testandroid.yang.util.Constants;

/**
 * AccountManagerActivity
 * Created by yangjiajia on 2017/8/18.
 */

public class AccountManagerActivity extends BaseActivity {
    private static final String TAG = "AccountManagerActivity";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handleMessage: ");
        }
    };

    public static void start(Context context) {
        Intent starter = new Intent(context, AccountManagerActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manage);
        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        AccountManager accountManager = AccountManager.get(this);
        Account[] accounts = accountManager.getAccounts();
        Account[] accountsByType = accountManager.getAccountsByType(Constants.ACCOUNT_TYPE);
        Log.d(TAG, "initData: accountsByType=" + accountsByType.length);


        Bundle options = new Bundle();
        Account account1 = new Account("", "");
        accountManager.getAuthToken(
                account1,                     // Account retrieved using getAccountsByType()
                "Manage your tasks",            // Auth scope
                options,                        // Authenticator-specific options
                this,                           // Your activity
                new OnTokenAcquired(),          // Callback called when a token is successfully acquired
                handler);    // Callback called if an error occurs
        Log.d(TAG, "initData: ==========================================");

        for (Account account : accounts) {
            Log.d(TAG, "onViewClicked: account=" + account);
            Log.d(TAG, "onViewClicked: name account=" + account.name);
            Log.d(TAG, "onViewClicked: type account=" + account.type);
        }
    }
}
