package com.tneciv.zhihudaily.api;

/**
 * Created by Tneciv on 1-16-0016.
 */
public class ZhihuApi {
    public static final String NEWS_LATEST = "http://news-at.zhihu.com/api/4/news/latest";
    public static final String NEWS_CONTENT = "http://news-at.zhihu.com/api/4/news/";
    public static final String NEWS_HOT = "http://news-at.zhihu.com/api/3/news/hot";

    public static String getNewsContentUrl(int id) {
        return NEWS_CONTENT + id;
    }
}
