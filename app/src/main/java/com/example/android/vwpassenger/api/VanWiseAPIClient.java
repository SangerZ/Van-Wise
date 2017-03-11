package com.example.android.vwpassenger.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pulitz on 3/3/2017.
 */

public class VanWiseAPIClient {

    public static final String BASE_URL = "http://vanwise.azurewebsites.net/Passengers/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
