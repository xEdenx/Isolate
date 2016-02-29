package com.tneciv.zhihudaily;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatDelegate;

import im.fir.sdk.FIR;

/**
 * Created by Tneciv on 2-19-0019.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FIR.init(this);
    }

    static {

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    }
}
