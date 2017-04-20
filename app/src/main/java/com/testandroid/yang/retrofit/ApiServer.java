package com.testandroid.yang.retrofit;

import com.testandroid.yang.common.MicroCourseInfo;

import java.util.List;

import javax.security.auth.Subject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
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


}
