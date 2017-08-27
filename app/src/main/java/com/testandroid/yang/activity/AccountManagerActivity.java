package com.testandroid.yang.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * AccountManagerActivity
 * Created by yangjiajia on 2017/8/18.
 */

public class AccountManagerActivity extends BaseActivity {
    private static final String TAG = "AccountManagerActivity";
    @BindView(R.id.toolbar_left)
    TextView toolbarLeft;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.add_account_0)
    TextView addAccount0;
    @BindView(R.id.add_account_1)
    TextView addAccount1;
    @BindView(R.id.add_account_2)
    TextView addAccount2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handleMessage: ");
        }
    };
    private AccountManager accountManager;

    public static void start(Context context) {
        Intent starter = new Intent(context, AccountManagerActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manage);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        accountManager = AccountManager.get(this);


    }

    @OnClick({R.id.add_account_0, R.id.add_account_1, R.id.add_account_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_account_0:

                Bundle options = new Bundle();
                Account account1 = new Account("cth2", getString(R.string.account_type));
                accountManager.addAccountExplicitly(account1, "cthhh", options);

//        accountManager.getAuthToken(
//                account1,                     // Account retrieved using getAccountsByType()
//                "Manage your tasks",            // Auth scope
//                options,                        // Authenticator-specific options
//                this,                           // Your activity
//                new OnTokenAcquired(),          // Callback called when a token is successfully acquired
//                handler);    // Callback called if an error occurs
                break;
            case R.id.add_account_1:
                String accountType = getString(R.string.account_type);
                String authTokenType = getString(R.string.auth_token_type);
                accountManager.addAccount(accountType, authTokenType, null, null, this, new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> future) {
                        Log.d(TAG, "run: future=" + future);
                    }
                }, new Handler());
                break;
            case R.id.add_account_2:
                Account[] accounts = accountManager.getAccounts();
                Account[] accountsByType = accountManager.getAccountsByType(Constants.ACCOUNT_TYPE);
                Log.d(TAG, "initData: accountsByType=" + accountsByType.length);

                Log.d(TAG, "initData: ==========================================");

                for (Account account : accounts) {
                    Log.d(TAG, "onViewClicked: account=" + account);
//                    Log.d(TAG, "onViewClicked: name account=" + account.name);
//                    Log.d(TAG, "onViewClicked: type account=" + account.type);
                }
                break;
        }
    }
}
