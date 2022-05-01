package com.example.oatsv5;

import com.example.oatsv5.Models.Courses.CoursesJSONResponse;
import com.example.oatsv5.Models.Departments.DepartmentJSONResponse;
import com.example.oatsv5.Models.GuestUser;
import com.example.oatsv5.Models.LoginGuestResult;
import com.example.oatsv5.Models.LoginUserResult;
import com.example.oatsv5.Models.StudentUser;
import com.example.oatsv5.Models.Thesis.ThesisJSONResponse;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("guest/login")
    Call<LoginGuestResult> executeLogin(@Body HashMap<String, String> map);

    @POST("guest/register")
    Call<Void> executeRegister (@Body HashMap<String, String> map);

        @GET("/guest/infor")
    Call<LoginGuestResult> getGuestUser(@Header("Authorization") String authToken);



//ThesisView
//    @GET("/api/thesis")
//    Call<List<ThesesResult>> getTheses();

    @GET("/api/thesis")
    Call<ThesisJSONResponse> getThesesResult();

    @GET("/api/department")
    Call<DepartmentJSONResponse>getDepartments();

    @GET("/api/course")
    Call<CoursesJSONResponse>getCourses();

    @POST("user/login")
    Call<LoginUserResult> executeLoginUser(@Body HashMap<String, String> map);

    @POST("user/register")
    Call<Void> executeRegisterUser (@Body HashMap<String, String> map);



}
