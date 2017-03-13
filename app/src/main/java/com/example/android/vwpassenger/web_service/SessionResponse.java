package com.example.android.vwpassenger.web_service;

import com.example.android.vwpassenger.session.UserData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pulitz on 3/13/2017.
 */

public class SessionResponse {
    @SerializedName("ResultCode")
    private int resultCode;
    @SerializedName("UserData")
    private UserData userData;
    @SerializedName("Message")
    private String message;

    public SessionResponse(int resultCode, UserData userData, String message) {
        this.resultCode = resultCode;
        this.userData = userData;
        this.message = message;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
