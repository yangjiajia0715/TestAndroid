package com.testandroid.yang.retrofit;

import com.testandroid.yang.common.ResultBeanInfo;
import com.testandroid.yang.common.FileUploadInfo;
import com.testandroid.yang.common.MicroCourseInfo;
import com.testandroid.yang.common.User;

import java.util.List;

import javax.security.auth.Subject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * ApiServer
 * Created by yangjiajia on 2017/4/19 0019.
 */

public interface ApiServer {
    @GET("/heiheihei")
    int add(int a, int b);

    //keyong
    @GET("LoginServer/px.json")
    Call<ResponseBody> getMicroCourseInfos(@Query("dataType") String dataType, @Query("stu_id") String stuId);

    //keyong
    @GET("LoginServer/px.json?dataType=course_find_by_stu")
    Call<List<MicroCourseInfo>> getMicroCourseInfosdata(@Query("stu_id") String stuId);

    /**
     * test
     */
    @GET("LoginServer/{id}/px.json")
    Call<Subject> getSub(@Path("id") int id);

    @GET("LoginServer/px.json?dataType=course_find_by_stu")
    Observable<ResponseBody> getMic(@Query("stu_id") String stuId);

    @GET("LoginServer/px.json?dataType=course_find_by_stu")
    Observable<Response> getMicResponse(@Query("stu_id") String stuId);

    //    @Headers("Content-Disposition:form-data; name=\"file\";filename=\"file.jpg\"")
    @Multipart
    @POST("LoginServer/px/file/upload.json")
    Observable<ResultBeanInfo<FileUploadInfo>> uploadFile(@Part MultipartBody.Part part);

    //temp
    @Multipart
    @POST("LoginServer/px/file/upload.json")
    Observable<ResultBeanInfo<FileUploadInfo>> uploadFile44(@Part("file") RequestBody foo);

    //ooccrr
//    @Multipart
//    @Headers("Content-Disposition:form-data; name=\"file\";filename=\"file.jpg\"")
//    @POST("LoginServer/px/file/upload.json")
//    Observable<ResponseBody> uploadFileOCR(@Headers("") );

    @POST("users/new")
    Call<User> createUser(@Body User user);

    @Multipart
    @PUT("user/photo")
    Call<User> updateUser(@Part("photo") RequestBody photo, @Part("description") RequestBody description);

    @Multipart
    @POST("LoginServer/px/file/uploadForOcr.json")
    Observable<ResponseBody> uploadFirstFile1(@Part MultipartBody.Part part);

    @Multipart
    @POST("LoginServer/px/file/uploadForOcr.json")
    Observable<ResponseBody> uploadFirstFile(@Part RequestBody body);

    @GET("LoginServer/aq/teacher/findAll.json")
    Observable<ResponseBody> getAnswerSquare(@Query("teacherId") String teacherId, @Query("_pageNum") int pageNum);

    List<List<? extends User>> getLLL(@Url String url);

    //继教网老账号ID
//#define DWACCOUNT_USERID @"435D54363B37C983"
//咱们自己的新账号ID
//#define DWACCOUNT_USERID @"2BDF604C7486AE0F"
//继教网老账号apiKey
//#define DWACCOUNT_APIKEY @"iSgdPc2uGMhiml0UrdXh4AgS3AayonAv"
//咱们自己新账号的apiKey
//#define DWACCOUNT_APIKEY @"YUsABCcw7U4j0PfLpCEubg5tsq74ubzd"

}
