package com.app.madscalsi.Utility;

import android.content.Context;
import android.content.SharedPreferences;


public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    public static final String PREF_NAME = "MADS_PREF";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void saveBoolean(Context context, String Key, boolean value){
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putBoolean(Key, value); //3
        editor.commit(); //4
    }

    public boolean getBooleanValue(Context context, String Key) {
        return pref.getBoolean(Key, false);
    }



        public void saveString(Context context, String Key, String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putString(Key, text); //3
        editor.commit(); //4
    }

    public String getStringValue(Context context, String Key) {
        SharedPreferences settings;
        String text = "";
        settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        text = settings.getString(Key, "null");
        return text;
    }


    public void saveInt(Context context, String Key, int text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putInt(Key, text); //3
        editor.commit(); //4
    }

    public int getIntValue(Context context, String Key) {
        SharedPreferences settings;
        int text ;
        settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        text = settings.getInt(Key,0);
        return text;
    }
}
