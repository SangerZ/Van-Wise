package com.example.android.vwpassenger.session;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pulitz on 3/13/2017.
 */

public class UserData {
    @SerializedName("UserID")
    private int userID;
    @SerializedName("DisplayName")
    private String displayName;

    public UserData(int userID, String displayName) {
        this.userID = userID;
        this.displayName = displayName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
