package com.example.android.vwpassenger.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pulitz on 3/3/2017.
 */

public class LoginResponse {
    @SerializedName("LoginStatus")
    private int loginStatus;
    @SerializedName("Message")
    private String message;
    @SerializedName("UserID")
    private Integer userID;

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
