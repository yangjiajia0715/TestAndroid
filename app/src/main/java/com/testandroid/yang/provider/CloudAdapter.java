package com.testandroid.yang.provider;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

/**
 * 同步provider到服务器
 * Created by yangjiajia on 2017/9/16.
 */

public class CloudAdapter extends AbstractThreadedSyncAdapter {
    public CloudAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {

    }
}
