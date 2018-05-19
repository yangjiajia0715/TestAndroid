package com.testandroid.yang.retrofit;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * 启步接口，调试用，连接本地数据库
 * Created by yangjiajia on 2018/5/17.
 */
public interface ApiQiBu {
    @GET("/questionRecord/listAll")
    Observable<ResponseBody> listAll();
}
