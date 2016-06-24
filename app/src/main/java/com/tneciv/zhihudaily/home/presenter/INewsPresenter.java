package com.tneciv.zhihudaily.home.presenter;


/**
 * Created by Tneciv
 * on 2016-06-24 17:55 .
 */
public interface INewsPresenter {
    void requestUrl(String url);

    void parseJsonOfHots(String responseCallback);

    void parseJsonOfNews(String callback);
}
