package com.example.oatsv5;

import com.example.oatsv5.Models.LoginGuestResult;
import com.example.oatsv5.Models.LoginUserResult;
import com.example.oatsv5.Models.ThesisJSONResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("guest/login")
    Call<LoginGuestResult> executeLogin(@Body HashMap<String, String> map);

    @POST("guest/register")
    Call<Void> executeRegister (@Body HashMap<String, String> map);


//ThesisView
//    @GET("/api/thesis")
//    Call<List<ThesesResult>> getTheses();

    @GET("/api/thesis")
    Call<ThesisJSONResponse> getThesesResult();


    @POST("guest/login")
    Call<LoginUserResult> executeLoginUser(@Body HashMap<String, String> map);

    @POST("guest/register")
    Call<Void> executeRegisterUser (@Body HashMap<String, String> map);

}
