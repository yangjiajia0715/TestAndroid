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
import com.testandroid.yang.common.Repo;
import com.testandroid.yang.common.Your;
import com.testandroid.yang.retrofit.GitHubService;
import com.testandroid.yang.util.ProtocalManager;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

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

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
//                chain.proceed()
                return null;
            }
        };

//        File externalCacheDir = getExternalCacheDir();

//        File filesDir = getFilesDir();

        String storageState = Environment.getExternalStorageState();
        Log.d(TAG, "onCreate: storageState=" + storageState);

        if (storageState.equals(Environment.MEDIA_MOUNTED)) {

        }

        File externalStorageDirectory = Environment.getExternalStorageDirectory();

        Log.d(TAG, "onCreate: externalStorageDirectory=" + externalStorageDirectory);

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
            @Override public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                if (Your.sToken == null || alreadyHasAuthorizationHeader(originalRequest)) {
                    return chain.proceed(originalRequest);
                }

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
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(mTokenInterceptor)
//                .addInterceptor(interceptor)
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

    @OnClick({R.id.okhttp_01, R.id.okhttp_02, R.id.okhttp_03, R.id.okhttp_04})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.okhttp_01:
                okhttp01();
                break;
            case R.id.okhttp_02:
                retrofit();
                break;
            case R.id.okhttp_03:
                break;
            case R.id.okhttp_04:
                break;
        }
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
        new Thread() {
            @Override
            public void run() {
                super.run();

                //可行 浏览器
                String xiaoman = "http://101.200.163.38/LoginServer/px.json?dataType=course_find_by_stu&stu_id=10097";
                String uu = "http://101.200.163.38/LoginServer/appServlet?auth=12345&id=10097";
                String url = ProtocalManager.GET_ANSWER_SQUARE_LIST + "?teacherId=520";
                Request request = new Request.Builder()
                        .url(url)
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

    }
}
