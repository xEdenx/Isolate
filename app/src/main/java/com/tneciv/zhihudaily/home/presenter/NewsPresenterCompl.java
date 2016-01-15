package com.tneciv.zhihudaily.home.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.tneciv.zhihudaily.Costants.ZhihuApi;
import com.tneciv.zhihudaily.home.model.NewsEntity;
import com.tneciv.zhihudaily.home.view.INewsView;
import com.tneciv.zhihudaily.utils.OkhttpUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Tneciv on 1-15-0015.
 */
public class NewsPresenterCompl implements INewsPresenter {

    INewsView iNewsView;

    public NewsPresenterCompl(INewsView iNewsView) {
        this.iNewsView = iNewsView;
        Log.d("NewsPresenterCompl", "news");
        String url = ZhihuApi.NEWS_LATEST;
        Request request = new Request.Builder().get().url(url).build();
//        OkHttpClient okHttpClient = new OkHttpClient();

        OkhttpUtils.getInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Gson gson = new Gson();
                String s = response.body().string();
                Type type = new TypeToken<List<NewsEntity>>() {
                }.getType();
                JsonElement jsonElement = new JsonParser().parse(s).getAsJsonObject().get("stories");
                List<NewsEntity> list = gson.fromJson(jsonElement, type);
                EventBus.getDefault().post(list);
            }
        });
    }

    @Override
    public void getNews() {
    }

}
