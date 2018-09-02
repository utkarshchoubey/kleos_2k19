package com.technocracy.nitraipur.kleos2k18.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserPreferences {
    private Activity activity;

    public UserPreferences(Activity activity){
        this.activity = activity;
    }


    public void setUsername(String username) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("username", username);
        editor.apply();
    }
    public String getUsername() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        return preferences.getString("username", "");
    }

    public void setPassword(String password) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("password", password);
        editor.apply();
    }
    public String getPassword() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        return preferences.getString("password", "");
    }
}