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

/**
 * ApiServer
 * Created by yangjiajia on 2017/4/19 0019.
 */

public interface ApiServer {

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
//    @Headers({"Header1: jkkkkk","Header2:kkkkkk"})
    Observable<ResultBeanInfo<FileUploadInfo>> uploadFile(@Part MultipartBody.Part part);

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

}
