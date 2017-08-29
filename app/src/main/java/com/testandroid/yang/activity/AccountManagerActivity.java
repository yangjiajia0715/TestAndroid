package com.testandroid.yang.activity;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OnAccountsUpdateListener;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.util.Constants;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * AccountManagerActivity
 * Created by yangjiajia on 2017/8/18.
 * <p>
 * http://zhenzxie.iteye.com/blog/1470022
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
//        Account account = new Account("", "");
        AccountAuthenticatorResponse response;
//        AtomicFile atomicFile;
        PackageManager pm = getPackageManager();

//   在本例中，查询PKMS中满足Intent Action为"android.accounts.AccountAuthenticator"
//   的服务信息。由以下代码可知，这些信息指的是Service中声明的MetaData信息

//        String mInterfaceName = AccountManager.AUTHENTICATOR_ATTRIBUTES_NAME;

        String mInterfaceName = AccountManager.AUTHENTICATOR_META_DATA_NAME;
        List<ResolveInfo> resolveInfos = pm.queryIntentServices(new Intent(mInterfaceName), PackageManager.GET_META_DATA);
        for (ResolveInfo resolveInfo : resolveInfos) {
//            Log.d(TAG, "initView: activityInfo=" + resolveInfo.activityInfo);
//            Log.d(TAG, "initView: activityInfo=" + resolveInfo);
        }
    }

    @Override
    public void initData() {
        accountManager = AccountManager.get(this);

        OnAccountsUpdateListener onAccountsUpdateListener = new OnAccountsUpdateListener() {
            @Override
            public void onAccountsUpdated(Account[] accounts) {

            }
        };

        String actionAuthenticatorIntent = AccountManager.ACTION_AUTHENTICATOR_INTENT;

        String authenticatorAttributesName = AccountManager.AUTHENTICATOR_ATTRIBUTES_NAME;

        String authenticatorMetaDataName = AccountManager.AUTHENTICATOR_META_DATA_NAME;

//        AccountManagerService
//        ServiceManager.addService(Context.ACCOUNT_SERVICE,new AccountManagerService(context));
//        AccountAuthenticatorCache cache
//        账户验证中心

    }

    @OnClick({R.id.add_account_0, R.id.add_account_1, R.id.add_account_2, R.id.add_account_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_account_0:
                Bundle options = new Bundle();
                Account account1 = new Account("cth2", getString(R.string.account_type));
                accountManager.addAccountExplicitly(account1, "cthhh", options);
                break;
            case R.id.add_account_1:
                String accountType = getString(R.string.account_type);
                String authTokenType = getString(R.string.auth_token_type);

                accountManager.addAccount(accountType,
                        authTokenType, null, null, this, new AccountManagerCallback<Bundle>() {
                            @Override
                            public void run(AccountManagerFuture<Bundle> future) {
                                try {
                                    Bundle result = future.getResult();
                                    Log.d(TAG, "run: result=" + result);
                                } catch (OperationCanceledException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (AuthenticatorException e) {
                                    e.printStackTrace();
                                }
                                Log.d(TAG, "run: future=" + future);
                            }
                        }, new Handler());
//                managerFuture.getResult();
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
            case R.id.add_account_3:
                Account[] accounts1 = accountManager.getAccountsByType(getString(R.string.account_type));
                for (Account account : accounts1) {

//                    accountManager.removeAccount(account, this, new AccountManagerCallback<Bundle>() {
//                        @Override
//                        public void run(AccountManagerFuture<Bundle> future) {
//
//                        }
//                    },new Handler());
//                    accountManager.removeAccountExplicitly(account);
                }
                break;
        }
    }
}
