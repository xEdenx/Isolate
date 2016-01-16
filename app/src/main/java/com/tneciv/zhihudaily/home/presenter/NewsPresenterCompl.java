package com.tneciv.zhihudaily.home.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.tneciv.zhihudaily.Costants.ZhihuApi;
import com.tneciv.zhihudaily.home.model.NewsEntity;
import com.tneciv.zhihudaily.home.view.INewsView;
import com.tneciv.zhihudaily.utils.OkhttpUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Tneciv on 1-15-0015.
 */
public class NewsPresenterCompl implements INewsPresenter {

    INewsView iNewsView;

    public NewsPresenterCompl(INewsView iNewsView) {
        this.iNewsView = iNewsView;
    }

    @Override
    public void requestUrl() {
        String url = ZhihuApi.NEWS_LATEST;
        Request build = new Request.Builder().get().url(url).build();
        OkhttpUtils.getInstance().newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
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


}
