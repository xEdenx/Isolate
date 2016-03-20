package com.tneciv.zhihudaily.api;

/**
 * Created by Tneciv on 1-16-0016.
 */
public class ZhihuApi {

    public static final String NEWS_CONTENT = "http://dudu.zhihu.com/api/4/news/";
    public static final String NEWS_HOT = "http://dudu.zhihu.com/api/6/news/hot";
    public static final String NEWS_HISTORY = "http://dudu.zhihu.com/api/4/news/before/";
    public static final String THEME_LIST = "http://dudu.zhihu.com/api/6/sections";
    public static final String THEME_NEWS_LIST = "http://dudu.zhihu.com/api/6/section/";
    public static final String NEWS_LATEST = "http://dudu.zhihu.com/api/4/news/latest";
    public static final String NEWS_DETAIL = "http://dudu.zhihu.com/story/";

    public static String getNewsContentUrl(int id) {
        return NEWS_CONTENT + id;
    }

    public static String getHistoryNewsUrl(String date) {
        return NEWS_HISTORY + date;
    }

    public static String getThemeNewsUrl(int themeId) {
        return THEME_NEWS_LIST + themeId;
    }
}
