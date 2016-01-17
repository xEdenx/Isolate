package com.tneciv.zhihudaily.home.presenter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.tneciv.zhihudaily.Api.ZhihuApi;
import com.tneciv.zhihudaily.home.model.HomeEventEntity;
import com.tneciv.zhihudaily.home.model.HotEntity;
import com.tneciv.zhihudaily.home.model.NewsEntity;
import com.tneciv.zhihudaily.home.view.IHotView;
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
    IHotView iHotView;

    public NewsPresenterCompl(INewsView iNewsView) {
        this.iNewsView = iNewsView;
    }

    public NewsPresenterCompl(IHotView iHotView) {
        this.iHotView = iHotView;
    }

    @Override
    public void requestUrl(final String url) {
        Request build = new Request.Builder().get().url(url).build();
        OkhttpUtils.getInstance().newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();


                if (url == ZhihuApi.NEWS_LATEST) {
                    String callback = response.body().string();
                    Type type = new TypeToken<List<NewsEntity>>() {
                    }.getType();
                    JsonElement jsonElement = new JsonParser().parse(callback).getAsJsonObject().get("stories");
                    List<NewsEntity> newsEntities = gson.fromJson(jsonElement, type);
//                    EventBus.getDefault().post(newsEntities);
                    EventBus.getDefault().post(new HomeEventEntity.NewEntityList(newsEntities));

                } else if (url == ZhihuApi.NEWS_HOT) {
                    String responseCallback = response.body().string();
                    Type type = new TypeToken<List<HotEntity>>() {
                    }.getType();
                    JsonElement jsonElement = new JsonParser().parse(responseCallback).getAsJsonObject().get("recent");
                    List<HotEntity> hotEntities = gson.fromJson(jsonElement, type);
                    EventBus.getDefault().post(new HomeEventEntity.HotEntityList(hotEntities));
//                    EventBus.getDefault().post(hotEntities);
                }

            }
        });
    }


}
