package com.testandroid.yang.glide;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

import java.util.LinkedHashMap;

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

        int memoryCacheSizeBytes = 1024 * 1024 * 20; // 20mb
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));
        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>();



    }

}
