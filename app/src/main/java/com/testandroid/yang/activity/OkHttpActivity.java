package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.testandroid.yang.R;
import com.testandroid.yang.common.FileUploadInfo;
import com.testandroid.yang.common.MicroCourseInfo;
import com.testandroid.yang.common.Repo;
import com.testandroid.yang.common.ResultBeanInfo;
import com.testandroid.yang.common.User;
import com.testandroid.yang.common.Your;
import com.testandroid.yang.retrofit.ApiClient;
import com.testandroid.yang.retrofit.ApiServer;
import com.testandroid.yang.retrofit.GitHubService;
import com.testandroid.yang.util.FileUtil;
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
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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
    private int stuId = 10097;
    private int curPage = 1;

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

    @OnClick({R.id.okhttp_01, R.id.okhttp_02, R.id.okhttp_03, R.id.okhttp_04, R.id.okhttp_05
            , R.id.okhttp_07, R.id.okhttp_08, R.id.okhttp_09, R.id.okhttp_10, R.id.okhttp_11
            , R.id.okhttp_12, R.id.okhttp_13})
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
            case R.id.okhttp_07:
                testAssemble();
                break;
            case R.id.okhttp_08:
                uploadFile();
                break;
            case R.id.okhttp_09:
                testMultipart();
                break;
            case R.id.okhttp_10:
                uploadFirstFile();
                break;
            case R.id.okhttp_11:
                uploadFileWithRetrofit();
                break;
            case R.id.okhttp_12:
                testRetrofit();
                break;
            case R.id.okhttp_13:
                testRetrofitCallAdapter();
                break;
        }
    }

    private void testRetrofitCallAdapter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://101.200.163.38/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//don't forget
                .build();

        ApiServer apiServer = retrofit.create(ApiServer.class);

        apiServer.getAnswerSquare("521", curPage)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        MediaType mediaType = responseBody.contentType();
                        Log.d(TAG, "accept: mediaType=" + mediaType);
                        Log.d(TAG, "accept: string=" + string);
                    }
                });
    }

    private void testRetrofit() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://101.200.163.38/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//don't for get
                    .build();

            ApiServer apiServer = retrofit.create(ApiServer.class);

            apiServer.getAnswerSquare("521", curPage)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBody>() {
                        @Override
                        public void accept(@NonNull ResponseBody responseBody) throws Exception {
                            String string = responseBody.string();
                            MediaType mediaType = responseBody.contentType();
                            Log.d(TAG, "accept: mediaType=" + mediaType);
                            Log.d(TAG, "accept: string=" + string);
                        }
                    });

            curPage++;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "testRetrofit: e=" + e);
        }
    }

    /**
     * 完美
     */
    private void uploadFileWithRetrofit() {
        String sdPath = FileUtil.getSDPath();
        Log.d(TAG, "uploadFileWithRetrofit: sdPath=" + sdPath);
        File file = new File(sdPath + "/DCIM/Camera/IMG_20161025_155722.jpg");///////

        if (!file.exists()) {
            Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
            return;
        }

//        ApiClient.getInstance()
//                .uploadFile(file)
//                .delay(2, TimeUnit.SECONDS, false)
//                .subscribe(new Consumer<ResultBeanInfo<FileUploadInfo>>() {
//                    @Override
//                    public void accept(@NonNull ResultBeanInfo<FileUploadInfo> fileUploadInfoResultBeanInfo) throws Exception {
//
//                    }
//                });

        ApiClient.getInstance()
                .uploadFile(file)
                .subscribe(new Observer<ResultBeanInfo<FileUploadInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBeanInfo<FileUploadInfo> fileUploadInfoBaseBeanInfo) {
                        Log.d(TAG, "onNext: fileUploadInfoBaseBeanInfo=" + fileUploadInfoBaseBeanInfo);
                        if (fileUploadInfoBaseBeanInfo != null) {
                            FileUploadInfo info = fileUploadInfoBaseBeanInfo.getData();
                            Log.d(TAG, "onNext: fileUploadInfoBaseBeanInfo=" + info);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: e=" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //ocr 第一张图片 识别 识别成功 相同 2
    private void uploadFirstFile() {

        String sdPath = FileUtil.getSDPath();
        Log.d(TAG, "uploadFirstFile: sdPath=" + sdPath);
//        File file = new File(sdPath + "/DCIM/Camera/IMG_20161025_155722.jpg");///////
//        File file = new File(sdPath + "/DCIM/Camera/IMG_20170428_122142.jpg");///////
        File file = new File(sdPath + "/CuoTiHui/temp_croped1_mosaic.jpg");///////

        MediaType mediaType = MediaType.parse("multipart/form-data");
        String type = mediaType.type();
        String subtype = mediaType.subtype();

        Log.d(TAG, "uploadFile: type=" + type);
        Log.d(TAG, "uploadFile: subtype=" + subtype);
        Log.d(TAG, "uploadFile: mediaType=" + mediaType.toString());
        Log.d(TAG, "uploadFile: file=" + file.getName());

        if (!file.exists()) {/////
            Toast.makeText(this, "文件不存在....", Toast.LENGTH_SHORT).show();
            return;
        }

//        RequestBody requestBody = MultipartBody.create(MediaType.parse("image/png"), file);
//        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

//        HashMap<String, String> map = new HashMap<>();
//        Headers headers = Headers.of(map);

//        map.put("subjectType", mSearchSubjectInfo.subjectType);
//        map.put("pupilId", String.valueOf(mUserInfo.pupilId));
//        map.put("type", "QUICK_OCR");

        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
//                .addPart(Headers.of("Content-Disposition", "form-data; name=\"file\";filename=\"file.jpg\""), RequestBody.create(MediaType.parse("image/png"), file))
                .addFormDataPart("file", file.getName(), MultipartBody.create(MediaType.parse("image/png"), file))
//                .addFormDataPart("subjectType\r\n math","math")
//                .addFormDataPart("pupilId\r\n 10097","10097")
//                .addFormDataPart("type\r\n QUICK_OCR","QUICK_OCR")
                .addFormDataPart("subjectType", "math")//ok ok
                .addFormDataPart("pupilId", "10097")
                .addFormDataPart("type", "QUICK_OCR")
                .build();

        Request request = new Request.Builder()
                .post(multipartBody)
//                .url("http://101.200.163.38/LoginServer/px/file/upload.json")
                .url("http://101.200.163.38/LoginServer/px/file/uploadForOcr.json")
                .build();

        Log.d(TAG, "onResponse: resprequest--request---request-----");

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: e=" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: response=" + response);
                if (response.isSuccessful()) {//ok
                    String body = response.body().string();
                    Log.d(TAG, "onResponse: body=" + body);
                }
            }
        });
    }

    private void testMultipart() {
        Log.d(TAG, "----------testMultipart------");

        try {

            MultipartBody.Part part = MultipartBody.Part.createFormData("错题会", "hhhh.jpg", RequestBody.create(null, "好的".getBytes()));
            Headers headers = part.headers();
            String toString = part.toString();
            RequestBody body = part.body();

            Log.d(TAG, "testMultipart: headers: " + headers);

            MultipartBody multipartBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addPart(part)
                    .addFormDataPart("", "file name", RequestBody.create(MediaType.parse("image/png"), new File("")))
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "testMultipart: e: " + e);
        }

    }

    /**
     * 可以了 ok
     */
    private void uploadFile() {
//        TagInfo tagInfo = new TagInfo();
//        List<TagInfo.ResultBean> result = tagInfo.getResult();
//        TagInfo.ResultBean resultBean = result.get(0);
//        resultBean.getAlias();

        String sdPath = FileUtil.getSDPath();
        Log.d(TAG, "uploadFile: sdPath=" + sdPath);
        File file = new File(sdPath + "/DCIM/Camera/IMG_20161025_155722.jpg");///////

        MediaType mediaType = MediaType.parse("multipart/form-data");
        String type = mediaType.type();
        String subtype = mediaType.subtype();

        Log.d(TAG, "uploadFile: type=" + type);
        Log.d(TAG, "uploadFile: subtype=" + subtype);
        Log.d(TAG, "uploadFile: mediaType=" + mediaType.toString());
        Log.d(TAG, "uploadFile: file=" + file.getName());

        if (!file.exists()) {/////
            Toast.makeText(this, "文件不存在....", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "uploadFile", Toast.LENGTH_SHORT).show();

//        RequestBody requestBody = MultipartBody.create(MediaType.parse("image/png"), file);
//        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

//        MultipartBody.Part.create()
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
//                .addPart(Headers.of("Content-Disposition", "form-data; name=\"file\";filename=\"file.jpg\""), RequestBody.create(MediaType.parse("image/png"), file))
//                .addPart(part)
                .addFormDataPart("哈哈哈", file.getName(), MultipartBody.create(MediaType.parse("image/png"), file))
                .build();

        Request request = new Request.Builder()
                .post(multipartBody)
                .url("http://101.200.163.38/LoginServer/px/file/upload.json")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: e=" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: response=" + response);
                if (response.isSuccessful()) {
                    String id = response.body().string();
                    Log.d(TAG, "onResponse: id=" + id);
                }
            }
        });
    }

    private void testAssemble() {
        stuId++;

        ApiClient.getInstance()
                .getMicInfos(String.valueOf(stuId))
                .subscribe(new Observer<List<MicroCourseInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(List<MicroCourseInfo> microCourseInfos) {
                        Log.d(TAG, "onNext: microCourseInfos=" + microCourseInfos);
                        if (microCourseInfos != null) {
                            for (MicroCourseInfo info : microCourseInfos) {
                                Log.d(TAG, "onNext: info=" + info.getSubject_name());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: e=" + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
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
                map.put("张三", "0000000");
                map.put("张三111", "000011");
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
                .callFactory(okHttpClient)
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


//        .map(new Function<ResponseBody, List<MicroCourseInfo>>() {
//            @Override
//            public List<MicroCourseInfo> apply(@NonNull ResponseBody responseBody) throws Exception {
//                return null;
//            }
//        })
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

        ApiServer apiServer = retrofit.create(ApiServer.class);

        retrofit2.Call<User> user = apiServer.createUser(new User());


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
