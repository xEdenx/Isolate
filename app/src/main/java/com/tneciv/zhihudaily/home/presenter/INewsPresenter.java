package com.tneciv.zhihudaily.home.presenter;


/**
 * Created by Tneciv on 1-15-0015.
 */
public interface INewsPresenter {
    void requestUrl(String url);

    void parseJsonOfHots(String responseCallback);

    void parseJsonOfNews(String callback);
}
