package com.tneciv.zhihudaily.retrofit;

import com.tneciv.zhihudaily.entity.HotEntity;
import com.tneciv.zhihudaily.entity.NewsEntity;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Tneciv
 * on 2016-11-10 12:22 .
 */

public interface ZhihuService {
    @GET("news/hot")
    Flowable<HotEntity> getHotList();

    @GET("news/{id}")
    Flowable<NewsEntity> getDetail(@Path("id") int id);
}
