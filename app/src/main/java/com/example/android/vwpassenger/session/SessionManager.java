package com.example.android.vwpassenger.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by Pulitz on 2/17/2017.
 */

public class SessionManager {
    private static final String PREF_NAME = "VWPref";
    private static final String LOGGED_IN = "LoggedIn";
    private static final String SESSION_ID = "SessionID";
    private static final String USER_ID = "UserID";
    private static final String NAME = "Name";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    Context _context;

    private static SessionManager sm = null;

    public static SessionManager getInstance() {
        return sm;
    }

    public static SessionManager createInstance(Context context) {
        sm = new SessionManager(context);
        return sm;
    }

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void createLoginSession(long sessionID, int userID, String name) {
        editor.putBoolean(LOGGED_IN, true);
        editor.putLong(SESSION_ID, sessionID);
        editor.putInt(USER_ID, userID);
        editor.putString(NAME, name);
        editor.commit();
    }

    /**
     *
     * @return true if logged in, else false and redirect to login page
     */
    public boolean checkLogin() {
        if(!this.isLoggedIn()) {
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
            return false;
        }
        return true;
    }

    public SessionDetails getSessionDetails() {
        return new SessionDetails(pref.getLong(SESSION_ID,-1L),
                                    pref.getInt(USER_ID,-1),
                                    pref.getString(NAME,null));
    }

    public void logoutUser(){

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, LoginActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(LOGGED_IN,false);
    }

}
