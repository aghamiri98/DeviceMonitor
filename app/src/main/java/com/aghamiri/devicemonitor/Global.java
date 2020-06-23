package com.aghamiri.devicemonitor;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;


public class Global extends Application {
    public static final Handler HANDLER = new Handler();
    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();



    }


}
