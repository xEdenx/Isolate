package com.tneciv.zhihudaily;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Tneciv
 * on 2016-11-09 20:55 .
 */

public class IsolateApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        CrashReport.initCrashReport(getApplicationContext(), "900053273", BuildConfig.DEBUG);
    }

}
