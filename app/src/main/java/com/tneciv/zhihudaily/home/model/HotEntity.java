package com.tneciv.zhihudaily.home.model;

import java.io.Serializable;

/**
 * Created by Tneciv
 * on 2016-06-24 17:55 .
 */
public class HotEntity implements Serializable {

    private static final long serialVersionUID = -7507137328585675881L;
    /**
     * news_id : 7741804
     * url : http://news-at.zhihu.com/api/2/news/7741804
     * thumbnail : http://pic3.zhimg.com/cc3ee17d28cd16655762c49c3eccc3ce.jpg
     * title : 短短两周，这种濒危的羚羊死了 12 万多头，原因很可能在这里
     */

    private int news_id;
    private String url;
    private String thumbnail;
    private String title;

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNews_id() {
        return news_id;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }
}
