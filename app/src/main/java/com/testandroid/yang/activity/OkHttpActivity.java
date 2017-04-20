package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.testandroid.yang.R;
import com.testandroid.yang.common.MicroCourseInfo;
import com.testandroid.yang.common.Repo;
import com.testandroid.yang.common.Your;
import com.testandroid.yang.retrofit.ApiServer;
import com.testandroid.yang.retrofit.GitHubService;
import com.testandroid.yang.util.ProtocalManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * OkHttpActivity
 * Created by yangjiajia on 2017/4/1 0001.
 * classNotFound:OkHttp3Activity, 不能有数字！！！
 */

public class OkHttpActivity extends BaseActivity {
    private static final String TAG = "OkHttpActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.okhttp_01)
    TextView okhttp01;
    @BindView(R.id.okhttp_02)
    TextView okhttp02;
    @BindView(R.id.okhttp_03)
    TextView okhttp03;
    @BindView(R.id.okhttp_04)
    TextView okhttp04;

    private OkHttpClient okHttpClient;

    public static void start(Context context) {
        Intent starter = new Intent(context, OkHttpActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        ButterKnife.bind(this);

//        File externalCacheDir = getExternalCacheDir();

//        File filesDir = getFilesDir();

        String storageState = Environment.getExternalStorageState();
        Log.d(TAG, "onCreate: storageState=" + storageState);

        if (storageState.equals(Environment.MEDIA_MOUNTED)) {

        }

        File externalStorageDirectory = Environment.getExternalStorageDirectory();

        Log.d(TAG, "onCreate: externalStorageDirectory=" + externalStorageDirectory);
        List<String> strings = new ArrayList<>();
        strings.add("FirstOne");
        String[] strs = {"张三", "李四"};
        Collections.addAll(strings, strs);
        Collections.addAll(strings, "王五", "周六");

        boolean b = false;
        b |= false;
        b |= true;
        b |= false;

        Log.d(TAG, "onCreate: b=" + b);
        Log.d(TAG, "onCreate: strings=" + strings);

        File cacheFile = null;
        long maxsize = 100_000;

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Authenticator authenticator = new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
//                Your.sToken = service.refreshToken();
                return response.request().newBuilder()
                        .addHeader("Authorization", Your.sToken)
                        .build();
            }
        };

        Interceptor mTokenInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                if (Your.sToken == null || alreadyHasAuthorizationHeader(originalRequest)) {
                    return chain.proceed(originalRequest);
                }

                originalRequest.newBuilder();

                Request authorised = originalRequest.newBuilder()
                        .header("Authorization", Your.sToken)
                        .build();
                return chain.proceed(authorised);
            }
        };


//        BasicParamsInterceptor basicParamsInterceptor;
        //全局
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)//方法为设置出现错误进行重新连接
//                .addInterceptor(loggingInterceptor)//报错
//                .addNetworkInterceptor(mTokenInterceptor)//没反应
//                .cache(new Cache(externalStorageDirectory, maxsize))
                .build();

        initView();
        initData();
    }

    private boolean alreadyHasAuthorizationHeader(Request originalRequest) {
        return true;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.okhttp_01, R.id.okhttp_02, R.id.okhttp_03, R.id.okhttp_04,R.id.okhttp_05})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.okhttp_01:
                okhttp01();
                break;
            case R.id.okhttp_02:
                retrofit();
                break;
            case R.id.okhttp_03:
                retrofit3();
                break;
            case R.id.okhttp_04:
                retrofitRxjava();
                break;
            case R.id.okhttp_05:
                essayHeader();
                break;
        }
    }

    private void essayHeader() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                String path = request.url().encodedPath();
                String query = request.url().encodedQuery();

                Map<String, String> map = new HashMap<>();
                map.put("张三","0000000");
                map.put("张三111","000011");
                Request request1 = request.newBuilder()
                        .headers(Headers.of(map))
                        .build();
                return chain.proceed(request1);
            }
        });
    }

    private void retrofitRxjava() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://101.200.163.38/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiServer apiServer = retrofit.create(ApiServer.class);

//        Observable<ResponseBody> mic = apiServer.getMic("10097");

        Log.d(TAG, "retrofitRxjava: ");

        //返回：Response  闪退
//        Observable<retrofit2.Response> micResponse = apiServer.getMicResponse("10097");
//        micResponse.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<retrofit2.Response>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "retrofitRxjava: onSubscribe" );
//                    }
//
//                    @Override
//                    public void onNext(retrofit2.Response response) {
//                        Log.d(TAG, "retrofitRxjava: onNext response=" + response );
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "retrofitRxjava: onError e=" + e );
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "retrofitRxjava: onComplete" );
//                    }
//                });


        apiServer.getMic("10097")
                .subscribeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "retrofitRxjava--onSubscribe: ");
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Log.d(TAG, "retrofitRxjava--onNext responseBody: " + responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d(TAG, "retrofitRxjava--onNext e: " + e);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "retrofitRxjava--onError: e=" + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "retrofitRxjava--onComplete: ");
                    }
                });

    }

    private void retrofit3() {

        new Thread() {
            @Override
            public void run() {
                try {
                    //可行 浏览器
                    String xiaoman2 = "http://101.200.163.38/LoginServer/px.json?dataType=course_find_by_stu&stu_id=10097";

                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    Request request = chain.request();
                                    Log.d(TAG, "intercept: ------intercept-----request=" + request.method());
                                    Request.Builder builder = request.newBuilder();
//                                    builder.head()
                                    return chain.proceed(request);
                                }
                            })
                            .build();
//        LoginServer/px.json/

                    Retrofit retrofit = new Retrofit.Builder()
//                            .addConverterFactory(GsonConverterFactory.create())
//                            .addConverterFactory()
                            .callFactory(okHttpClient)
                            .baseUrl("http://101.200.163.38/")
                            .build();

//                    Retrofit.Builder builder = retrofit.newBuilder();
                    Log.d(TAG, "retrofit3: retrofit=" + retrofit.baseUrl());
                    ApiServer apiServer = retrofit.create(ApiServer.class);

//                    retrofit2.Call<ResponseBody> findByStu = apiServer.getMicroCourseInfos("course_find_by_stu", "10097");
//
//                    Proxy.newProxyInstance(getClassLoader(), new Class[]{ApiServer.class}, new InvocationHandler() {
//                        @Override
//                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                            return null;
//                        }
//                    });
//                    retrofit2.Call<ResponseBody> microCourseInfosdata = apiServer.getMicroCourseInfosdata("10097");

                    retrofit2.Call<List<MicroCourseInfo>> microCourseInfosdata = apiServer.getMicroCourseInfosdata("10097");
                    retrofit2.Response<List<MicroCourseInfo>> responseBodyResponse = microCourseInfosdata.execute();

//                    retrofit2.Response<ResponseBody> responseBodyResponse = findByStu.execute();

                    boolean successful = responseBodyResponse.isSuccessful();
//
                    Log.d(TAG, "retrofit3: successful=" + successful);
                    if (successful) {
                        List<MicroCourseInfo> body = responseBodyResponse.body();
                        if (body != null && !body.isEmpty()) {
                            Log.d(TAG, "retrofit3: body=" + body.size());
                            for (MicroCourseInfo info : body) {
                                Log.d(TAG, "retrofit3: getSubject_name=" + info.getSubject_name());
                            }
                        }
                    }

//        Log.d(TAG, "retrofit3: answerSquare=" + answerSquare);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, "retrofit3: string e=" + e);
                } finally {
                    Log.d(TAG, "retrofit3: string end");
                }

            }
        }.start();

    }


    private void retrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();

//        Class service = GetAnswerSquare.class;

        GitHubService gitHubService = retrofit.create(GitHubService.class);

        retrofit2.Call<List<Repo>> yangjiajia = gitHubService.listRepos("yangjiajia0715");

//        gitHubService.createUser(new User());

        Log.d(TAG, "retrofit: gitHubService=" + gitHubService);

//        Object instance = Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
//                new InvocationHandler() {
//
//                    @Override
//                    public Object invoke(Object proxy, Method method, Object[] args)
//                            throws Throwable {
////                        Log.d(TAG, "retrofit: proxy=" + proxy);
//                        Log.d(TAG, "retrofit: method=" + method.getName());
//                        return new Student("zs",11);
//                    }
//                });
//
//        Log.d(TAG, "retrofit: instance=" + instance);

//        retrofit.create()
//        GetAnswerSquare answerSquare = retrofit.create(GetAnswerSquare.class);
//        User user = answerSquare.getUser();

    }

    private void okhttp01() {

        try {
            new Thread() {
                @Override
                public void run() {
                    super.run();

                    //可行 浏览器
                    String xiaoman2 = "http://101.200.163.38/LoginServer/px.json?dataType=course_find_by_stu&stu_id=10097";
                    String xiaoman = "http://101.200.163.38/LoginServer/px.json?";
                    String uu = "http://101.200.163.38/LoginServer/appServlet?auth=12345&id=10097";

                    String url = ProtocalManager.GET_ANSWER_SQUARE_LIST + "?teacherId=520";

                    url = xiaoman;

//                    dataType=course_find_by_stu&stu_id=10097
                    FormBody formBody = new FormBody.Builder()
                            .add("dataType", "course_find_by_stu")
                            .add("stu_id", "10097")
                            .add("search", "biezhihua")
                            .build();

//                    Log.d(TAG, "run: formBody=" + formBody.name(0));
//                    Log.d(TAG, "run: formBody=" + formBody.contentLength());
//                    Log.d(TAG, "run: formBody=" + formBody.size());
//                    Log.d(TAG, "run: formBody=" + formBody.contentType());

//                    FormEncodingBuilder builder;
                    Request request = new Request.Builder()
                            .url(url)
                            .post(formBody)
                            .addHeader("header", "headerValue")
                            .build();

//                UrlEncodedFormEntity

                    try {
//                    Response response = okHttpClient.newCall(request).execute();

                        Call call = okHttpClient.newCall(request);

//                    Request request1 = call.request();

//                    call.enqueue(new Callback() {
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//
//                        }
//
//                        @Override
//                        public void onResponse(Call call, Response response) throws IOException {
//
//                        }
//                    });
                        Response response = call.execute();
                        Log.d(TAG, "run: response=" + response);
                        if (response.code() == 200) {
                            ResponseBody body = response.body();
                            Log.d(TAG, "run:  body.string=" + body.string());
                        }
                    } catch (IOException e) {
                        Log.d(TAG, "run: e=" + e);
                        e.printStackTrace();
                    }
                }
            }.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
