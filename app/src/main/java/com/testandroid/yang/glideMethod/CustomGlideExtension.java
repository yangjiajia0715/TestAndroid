package com.testandroid.yang.glideMethod;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by yangjiajia on 2018/3/5.
 */
@GlideExtension
public class CustomGlideExtension {
    private CustomGlideExtension() {
    }

    @GlideOption
    public static void mineThumb(RequestOptions options){
        options.override(300);
    }
}
