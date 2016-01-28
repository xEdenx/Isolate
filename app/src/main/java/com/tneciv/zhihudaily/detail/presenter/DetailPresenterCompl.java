package com.tneciv.zhihudaily.detail.presenter;

import com.google.gson.Gson;
import com.tneciv.zhihudaily.api.ZhihuApi;
import com.tneciv.zhihudaily.detail.model.ContentEntity;
import com.tneciv.zhihudaily.detail.view.IDeatilView;
import com.tneciv.zhihudaily.utils.OkhttpUtils;

import java.io.IOException;

import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Tneciv on 1-16-0016.
 */
public class DetailPresenterCompl implements IDetailPresenter {
    IDeatilView iDeatilView;

    public DetailPresenterCompl(IDeatilView iDeatilView) {
        this.iDeatilView = iDeatilView;
    }

    @Override
    public void requestNewsContent(int id) {
        String newsContentUrl = ZhihuApi.getNewsContentUrl(id);
        Request request = new Request.Builder().get().url(newsContentUrl).build();
        OkhttpUtils.getInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                ContentEntity entity = gson.fromJson(string, ContentEntity.class);
                EventBus.getDefault().post(entity);
            }
        });

    }
}
