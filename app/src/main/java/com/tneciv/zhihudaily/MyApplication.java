package com.tneciv.zhihudaily;

import android.app.Application;

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
}
