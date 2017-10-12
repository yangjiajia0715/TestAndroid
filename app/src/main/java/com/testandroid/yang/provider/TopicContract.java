package com.testandroid.yang.provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import static com.testandroid.yang.provider.TopicContentProvider.AUTHORITY;

/**
 * 错题合约类
 * Created by yangjiajia on 2017/10/12.
 * {@link com.testandroid.yang.common.TopicInfo}
 */

public final class TopicContract {
    //    private static final Uri content_uri = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT).build();

    public static final String TABLE_NAME = "topic";

    public static final Uri CONTENT_URI = new Uri.Builder()
            .scheme(ContentResolver.SCHEME_CONTENT)
            .authority(AUTHORITY)
            .path(TABLE_NAME)
            .build();

    public static class Topic implements BaseColumns{
        public static final String NAME = "name";
        public static final String TOPIC_ID = "topic_id";
    }
}
