package com.tneciv.zhihudaily.detail.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.tneciv.zhihudaily.api.ZhihuApi;
import com.tneciv.zhihudaily.detail.model.ContentEntity;
import com.tneciv.zhihudaily.utils.CacheUtil;
import com.tneciv.zhihudaily.utils.OkhttpUtil;

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
    private Context mContext;

    public DetailPresenterCompl(Context context) {
        this.mContext = context;
    }

    @Override
    public void requestNewsContent(final int id) {
        final String newsContentUrl = ZhihuApi.getNewsContentUrl(id);
        Request request = new Request.Builder().get().url(newsContentUrl).build();
        OkhttpUtil.getInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                new CacheUtil(mContext).cacheFiles(String.valueOf(id), string);
                Gson gson = new Gson();
                ContentEntity entity = gson.fromJson(string, ContentEntity.class);
                EventBus.getDefault().post(entity);
            }
        });

    }
}
