package com.example.android.vwpassenger.web_service;

import com.example.android.vwpassenger.queue.EnqueueResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Pulitz on 3/3/2017.
 */

public interface VanWiseInterface {
    @GET("Register")
    Call<SessionResponse> Register(@Query("username") String username, @Query("passwordHash") String passwordHash, @Query("displayName") String displayName, @Query("phoneNumber") String phoneNumber);

    @GET("Login")
    Call<SessionResponse> Login(@Query("username") String username, @Query("passwordHash") String passwordHash);

    @GET("GetAllQueues")
    Call<QueueListResponse> GetAllQueues(@Query("userID") int userID);

//    @GET("getAllTripInQueue")
//    Call


    @GET("Enqueue")
    Call<EnqueueResponse> Enqueue(@Query("user_id") int userID, @Query("trip_id") int tripID);

}
