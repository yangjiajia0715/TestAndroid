package com.testandroid.yang.retrofit;

import com.testandroid.yang.common.Repo;
import com.testandroid.yang.common.Student;
import com.testandroid.yang.common.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by yangjiajia on 2017/4/17 0017.
 */

public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @GET("group/{id}/users")
    Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);

    @GET("group/{id}/ussss")
    Call<User> getuser(@Path("id") int userid, @Query("sort") String sort);

    @GET("user/{userId}/stu")
    Call<Student> getstu(@Path("userId") int userId, @QueryMap Map<String,String> map);

    @POST("post/stu")
    Call<Student> createUser(@Body User user);


}
