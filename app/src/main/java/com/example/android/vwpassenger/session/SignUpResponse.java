package com.example.android.vwpassenger.session;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pulitz on 3/3/2017.
 */

public class SignUpResponse {
    @SerializedName("isSuccessful")
    private boolean result;
    @SerializedName("errorMessage")
    private String errorMessage;
    @SerializedName("ID")
    private Integer userID;

    public SignUpResponse(boolean result, String errorMessage, Integer userID) {
        this.result = result;
        this.errorMessage = errorMessage;
        this.userID = userID;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
