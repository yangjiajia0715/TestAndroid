package com.testandroid.yang.account;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

/**
 * SyncAdapter
 * Created by yangjiajia on 2017/8/28.
 */

public class SyncAdapter extends AbstractThreadedSyncAdapter {

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
                              ContentProviderClient provider, SyncResult syncResult) {

    }
}
