package com.ashhasib.android_boilerplate.sharedpreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class UserSessionManager {

    private Context context;
    private int MODE = 0;

    private static String PREF_NAME = "AppPreference";
    private static String STATUS = "STATUS";

    private static String USERNAME = "USERNAME";
    private static String PASSWORD = "PASSWORD";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    //public UserSessionManager(){}



    public UserSessionManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }



    public void write(String username, String password) {

        this.editor.putString(USERNAME,username);
        this.editor.putString(PASSWORD, password);
        this.editor.putBoolean(STATUS, true);
        this.editor.commit();
    }

    public void clear() {

        this.editor.clear();
        this.editor.commit();
    }

    public boolean isLoggedIn() {
        return this.sharedPreferences.getBoolean(STATUS, false);
    }

}
