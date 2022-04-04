package com.example.oatsv5;

import java.util.HashMap;
import java.util.List;

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
    @GET("/items/get")
    Call<List<ThesesResult>> getThesesResult();
}
