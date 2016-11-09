package com.tneciv.zhihudaily.retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tneciv.zhihudaily.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceFactory {

    private static final String DEFAULT_BASE_URL = "http://dudu.zhihu.com/api/7/";
    private static volatile Retrofit defaultInstance;

    private ApiServiceFactory() throws InstantiationException {
        throw new InstantiationException("This class is not for instantiation");
    }

    public static Retrofit getInstance() {

        if (defaultInstance == null) {
            synchronized (Retrofit.class) {
                if (defaultInstance == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();

                    /**
                     * Okhttp Log 信息拦截器
                     */
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
                        builder.addInterceptor(loggingInterceptor);
                    }

                    OkHttpClient okHttpClient = builder.build();

                    defaultInstance = new Retrofit.Builder()
                            .baseUrl(DEFAULT_BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(okHttpClient)
                            .build();

                }
            }
        }

        return defaultInstance;
    }

}
