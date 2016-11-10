package com.tneciv.zhihudaily.retrofit;

import com.tneciv.zhihudaily.entity.HotEntity;
import com.tneciv.zhihudaily.entity.NewsEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Tneciv
 * on 2016-11-10 12:22 .
 */

public interface ZhihuService {
    @GET("news/hot")
    Observable<HotEntity> getHotList();

    @GET("news/{id}")
    Observable<NewsEntity> getDetail(@Path("id") int id);
}
