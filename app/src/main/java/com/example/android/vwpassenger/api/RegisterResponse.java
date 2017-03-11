package com.example.android.vwpassenger.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pulitz on 3/3/2017.
 */
public class RegisterResponse {
    @SerializedName("RegisterResult")
    private int result;
    @SerializedName("Message")
    private String message;
    @SerializedName("UserID")
    private Integer userID;

    public RegisterResponse(int result, String message, Integer userID) {
        this.result = result;
        this.message = message;
        this.userID = userID;
    }

    public int isResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
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
