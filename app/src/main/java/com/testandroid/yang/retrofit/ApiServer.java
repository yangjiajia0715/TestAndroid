package com.testandroid.yang.retrofit;

import com.testandroid.yang.common.MicroCourseInfo;

import java.util.List;

import retrofit2.http.GET;

/**
 * ApiServer
 * Created by yangjiajia on 2017/4/19 0019.
 */

public interface ApiServer {

    @GET("group/{id}/users")
    public List<MicroCourseInfo> getAnswerSquare();
}
