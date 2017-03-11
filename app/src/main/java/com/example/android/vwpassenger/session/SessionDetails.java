package com.example.android.vwpassenger.session;

/**
 * Created by Pulitz on 2/17/2017.
 */

public class SessionDetails {
    private long sessionID;
    private int userID;
    private String name;

    public SessionDetails(long sessionID, int userID, String name) {
        this.sessionID = sessionID;
        this.userID = userID;
        this.name = name;
    }

    public long getSessionID() {
        return sessionID;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }
}
