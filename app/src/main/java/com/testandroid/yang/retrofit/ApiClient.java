package com.testandroid.yang.retrofit;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.testandroid.yang.BuildConfig;
import com.testandroid.yang.common.ResultBeanInfo;
import com.testandroid.yang.common.FileUploadInfo;
import com.testandroid.yang.common.MicroCourseInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ApiClient
 * Created by yangjiajia on 2017/4/25 0025.
 */

public class ApiClient {
    private static final String TAG = "ApiClient";
    private static final int CONNECT_TIME_OUT = 20;//连接超时 20s
    private OkHttpClient okHttpClient;
    private final Retrofit retrofit;

    private static ApiClient INSTANCE;
    private final ApiServer apiServer;

    public static ApiClient getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ApiClient();
        return INSTANCE;
    }

    private ApiClient() {
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new TestIntercept())
                .addInterceptor(new Test2Intercept())
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .build();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            Log.d(TAG, "ApiClient-------httpLoggingInterceptor=" + httpLoggingInterceptor);
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            okHttpClient.networkInterceptors().add(httpLoggingInterceptor);//异常
        }

        retrofit = new Retrofit.Builder()
                .callFactory(okHttpClient)
                .baseUrl("http://101.200.163.38/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiServer = retrofit.create(ApiServer.class);
    }

    public Observable<List<MicroCourseInfo>> getMicInfos(String stuId) {
        return apiServer.getMic(stuId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ResponseBody, List<MicroCourseInfo>>() {
                    @Override
                    public List<MicroCourseInfo> apply(@NonNull ResponseBody responseBody) throws Exception {
                        String bodys = responseBody.string();
                        Log.d(TAG, "apply: bodys=" + bodys);
                        if (TextUtils.isEmpty(bodys))
                            return null;

                        try {
                            JSONObject jsonObject = new JSONObject(bodys);
                            JSONArray array = jsonObject.optJSONArray("data");
                            if (array == null)
                                return null;

                            Log.d(TAG, "apply: bodys array=" + array);
                            List<MicroCourseInfo> microCourseInfos = JSON.parseArray(array.toString(), MicroCourseInfo.class);
                            Log.d(TAG, "apply: bodys microCourseInfos=" + microCourseInfos.size());
                            return microCourseInfos;
                        } catch (JSONException e) {
                            Log.d(TAG, "apply: bodys e=" + e);
                            e.printStackTrace();
                        }

                        return null;
                    }
                });
    }

    public Observable<ResultBeanInfo<FileUploadInfo>> uploadFile(@android.support.annotation.NonNull File file) {

        RequestBody body = MultipartBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), body);

        Retrofit retrofitFileUpload = retrofit.newBuilder()
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServer apiServer1 = retrofitFileUpload.create(ApiServer.class);

        return apiServer1.uploadFile(part)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())//doOnndext
                ;

    }
}
