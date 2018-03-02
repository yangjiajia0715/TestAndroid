package com.testandroid.yang.util;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Created by yangjiajia on 2018/3/2.
 */

@GlideModule
public class CustomGlideModule extends AppGlideModule {
    private static final String TAG = "CustomGlideModule";

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
//        builder.setDefaultRequestOptions()
//        builder.setDiskCache()
        Log.d(TAG, "applyOptions: context=" + context);

    }

}
