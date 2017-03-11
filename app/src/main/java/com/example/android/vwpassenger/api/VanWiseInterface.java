package com.example.android.vwpassenger.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Pulitz on 3/3/2017.
 */

public interface VanWiseInterface {
//    @GET("Register")
//    Call<RegisterResponse> Register(@Query("username") String username, @Query("password_hash") String passwordHash, @Query("display_name") String displayName, @Query("phone") String phone);

    @GET("SignUp")
    Call<SignUpResponse> SignUp(@Query("username") String username, @Query("password_hash") String passwordHash, @Query("name") String displayName, @Query("phone") String phone);

    @GET("Login")
    Call<LoginResponse> Login(@Query("un") String username, @Query("p") String password_hash);



}