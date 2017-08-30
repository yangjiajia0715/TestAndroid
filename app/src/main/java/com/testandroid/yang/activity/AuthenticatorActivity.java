package com.testandroid.yang.activity;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.testandroid.yang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.accounts.AccountManager.KEY_ERROR_MESSAGE;
import static com.netease.nimlib.sdk.media.player.AudioPlayer.TAG;

/**
 * AuthenticatorActivity
 * Created by yangjiajia on 2017/8/16.
 */

public class AuthenticatorActivity extends AccountAuthenticatorActivity {
    public final static String PARAM_USER_PASS = "USER_PASS";
    private static final String ARG_IS_ADDING_NEW_ACCOUNT = "aaaaa";
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.ok)
    Button ok;
    private AccountManager manager;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_authenticator);
        ButterKnife.bind(this);
        manager = AccountManager.get(this);
        Log.d(TAG, "onCreate: getIntent=" + getIntent());
        Log.d(TAG, "onCreate: getExtras=" + getIntent().getExtras());
    }

    @OnClick(R.id.ok)
    public void onViewClicked() {
        final String userName = username.getText().toString();
        final String userPass = password.getText().toString();

        final String accountType = getIntent().getStringExtra(AccountManager.KEY_ACCOUNT_TYPE);
//        if (TextUtils.isEmpty(accountType)) {
//            accountType = getString(R.string.account_type);
//        }

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPass)) {
            Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        new AsyncTask<String, Void, Intent>() {

            @Override
            protected Intent doInBackground(String... params) {

                Log.d(TAG, "doInBackground: ");

                String authtoken = null;
                Bundle data = new Bundle();
                try {
//                    authtoken = sServerAuthenticate.userSignIn(userName, userPass, mAuthTokenType);
//                    test
                    authtoken = DateUtils.formatDateTime(AuthenticatorActivity.this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_DATE);

                    data.putString(AccountManager.KEY_ACCOUNT_NAME, userName);
                    data.putString(AccountManager.KEY_ACCOUNT_TYPE, accountType);
                    data.putString(AccountManager.KEY_AUTHTOKEN, authtoken);
                    //not null
                    data.putString(AccountManager.KEY_AUTHENTICATOR_TYPES, getString(R.string.auth_token_type));
                    data.putString(PARAM_USER_PASS, userPass);

                } catch (Exception e) {
                    data.putString(AccountManager.KEY_ERROR_MESSAGE, e.getMessage());
                }

                final Intent res = new Intent();
                res.putExtras(data);
                return res;
            }

            @Override
            protected void onPostExecute(Intent intent) {
                if (intent.hasExtra(KEY_ERROR_MESSAGE)) {
                    Toast.makeText(getBaseContext(), intent.getStringExtra(KEY_ERROR_MESSAGE), Toast.LENGTH_SHORT).show();
                } else {
                    finishLogin(intent);
                }
            }
        }.execute();
    }

    private void finishLogin(Intent intent) {
        Log.d(TAG, "finishLogin: intent=" + intent);

        String accountName = intent.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        String accountPassword = intent.getStringExtra(PARAM_USER_PASS);
        String accountType = intent.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE);
        if (TextUtils.isEmpty(accountType)) {
            accountType = getString(R.string.account_type);
        }
        final Account account = new Account(accountName, accountType);

//        if (getIntent().getBooleanExtra(ARG_IS_ADDING_NEW_ACCOUNT, false)) {
        if (getIntent().getBooleanExtra(ARG_IS_ADDING_NEW_ACCOUNT, true)) {
            String authtoken = intent.getStringExtra(AccountManager.KEY_AUTHTOKEN);
//            String authtokenType = mAuthTokenType;
            if (TextUtils.isEmpty(authtoken)) {
            }
            String authtokenType = intent.getStringExtra(AccountManager.KEY_AUTHENTICATOR_TYPES);

            // Creating the account on the device and setting the auth token we got
            // (Not setting the auth token will cause another call to the server to authenticate the user)
            manager.addAccountExplicitly(account, accountPassword, null);
            manager.setAuthToken(account, authtokenType, authtoken);
        } else {
            manager.setPassword(account, accountPassword);
        }

        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        finish();
    }


}
