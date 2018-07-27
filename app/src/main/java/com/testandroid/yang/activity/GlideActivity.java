package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.disklrucache.DiskLruCache;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.testandroid.yang.R;
import com.testandroid.yang.glide.GlideApp;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * GlideActivity
 *
 * @author yangjiajia
 * @date 2017/11/24
 */

public class GlideActivity extends BaseActivity {
    private static final String TAG = "GlideActivity";

    @BindView(R.id.imageview1)
    ImageView imageview1;
    @BindView(R.id.imageview2)
    ImageView imageview2;
    @BindView(R.id.imageview3)
    ImageView imageview3;
    @BindView(R.id.imageview4)
    ImageView imageview4;
    @BindView(R.id.imageview5)
    ImageView imageview5;
    private String mUrl;

    public static void start(Context context) {
        Intent starter = new Intent(context, GlideActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {

        mUrl = "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=2146927009,3405641870&fm=173&s=8E734D805C1202D6C6F52815030050C2&w=640&h=360&img.JPG";

        String urlXiZang = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1519907833264&di=a6c33ff2a2b3a21348f49629cf8767a9&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01975e55470a000000002b014adbc6.jpg%401280w_1l_2o_100sh.jpg";

        Glide.with(this)
                .load(urlXiZang)
                .apply(new RequestOptions().override(900, 900).diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageview1);

        DiskLruCache diskLruCache;

        HashMap<String, String> hashMap = new HashMap<>(10);
        hashMap.put("", "");
        String aa;
        if (TextUtils.equals("", "")) {

        }

        ImageLoader.getInstance();

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.image_loading)
                .override(300 * 3, 300 * 3)
                .error(R.drawable.img_default);

        BitmapTransitionOptions bitmapTransitionOptions = BitmapTransitionOptions.withCrossFade(5000);
        DrawableTransitionOptions drawableTransitionOptions = DrawableTransitionOptions.withCrossFade(5000);

        Glide.with(this)
                .load(mUrl)
                .apply(options)
//                .transition(transitionOptions)
//                .transition(bitmapTransitionOptions)
                .into(imageview2);

        //v4 取消加载，其实并不需要手动加载 ，因为Glide with方法传入的Activity fragmeng 销毁的时候，
        // Glide会自动取消加载 并且回收所有加载过程中的所使用的资源
//        Glide.with(this).clear(imageview3);

        GlideApp.with(this)
                .load(urlXiZang)
                .into(imageview5);

        RequestBuilder<Drawable> requestBuilder = Glide.with(this).load(mUrl);
        requestBuilder.transition(DrawableTransitionOptions.withCrossFade());


    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.imageview1, R.id.imageview2, R.id.imageview3, R.id.imageview4, R.id.imageview5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageview1:
                ImageView.ScaleType scaleType = imageview1.getScaleType();
                Log.d(TAG, "onViewClicked: " + scaleType);
                break;
            case R.id.imageview2:
                break;
            case R.id.imageview3:
                break;
            case R.id.imageview4:
                RequestOptions requestOptions = new RequestOptions()
                        .dontTransform()
                        .override(100, 100);

                Glide.with(this)
                        .load(mUrl)
                        .apply(requestOptions)
                        .into(imageview3);

                break;
            case R.id.imageview5:
                preload();
                break;
            default:
                break;
        }
    }

    private void preload() {

        FutureTarget<Bitmap> futureTarget =
                Glide.with(this)
                        .asBitmap()
                        .load(mUrl)
                        .submit(100, 100);

//        Bitmap bitmap = futureTarget.get();

// Do something with the Bitmap and then when you're done with it:
//        Glide.with(this).clear(futureTarget);

        Glide.with(this)
                .load(mUrl)
                .into(new BaseTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
//                        transition.transition()
//                        resource.
                    }

                    @Override
                    public void getSize(SizeReadyCallback cb) {

                    }

                    @Override
                    public void removeCallback(SizeReadyCallback cb) {

                    }
                });

        Glide.with(this)
                .load(mUrl)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {

                    }
                });

        ViewTarget<ImageView, Drawable> viewTarget = Glide.with(this)
//                .asBitmap()
                .load(mUrl)
                .into(imageview1);

        Glide.with(this).clear(viewTarget);

//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//        imageview1.setLayoutParams(layoutParams);
    }
}
