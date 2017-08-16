package com.testandroid.yang.activity;

import android.accounts.AccountAuthenticatorActivity;
import android.content.Context;
import android.content.Intent;

/**
 * AuthenticatorActivity
 * Created by yangjiajia on 2017/8/16.
 */

public class AuthenticatorActivity extends AccountAuthenticatorActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, AuthenticatorActivity.class);
        context.startActivity(starter);
    }

    public static final  String ARG_ACCOUNT_TYPE = "";
    public static final String ARG_AUTH_TYPE = "";
    public static final String ARG_IS_ADDING_NEW_ACCOUNT = "";
}
