package com.example.android.vwpassenger.session;

/**
 * Created by Pulitz on 2/17/2017.
 */

public class SessionDetails {
    private long sessionID;
    private int userID;
    private String displayName;

    public SessionDetails(long sessionID, int userID, String displayName) {
        this.sessionID = sessionID;
        this.userID = userID;
        this.displayName = displayName;
    }

    public long getSessionID() {
        return sessionID;
    }

    public int getUserID() {
        return userID;
    }

    public String getDisplayName() {
        return displayName;
    }
}
