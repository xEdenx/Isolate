package com.tneciv.zhihudaily.utils;

import com.squareup.okhttp.OkHttpClient;

/**
 * Created by Tneciv on 1-15-0015.
 */
public class OkhttpUtils {
    static volatile OkHttpClient defaultInstance;

    public static OkHttpClient getInstance() {
        if (defaultInstance == null) {
            synchronized (OkHttpClient.class) {
                if (defaultInstance == null) {
                    defaultInstance = new OkHttpClient();
                }
            }
        }
        return defaultInstance;
    }
}
