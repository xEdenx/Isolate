package com.tneciv.zhihudaily.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tneciv
 * on 2016-11-10 17:55 .
 */

public class NewsEntity {

    /**
     * body : <div class="main-wrap content-wrap">
     * image_source : 《奇异博士》
     * title : 瞎扯 Plus · 如何正确地吐槽
     * image : http://pic2.zhimg.com/3ab230ff74d2da885127d02551af0fc9.jpg
     * share_url : http://daily.zhihu.com/story/8959706
     * js : []
     * thumbnail : http://pic1.zhimg.com/c9db0a4bf420e7a61464cb8b8a74d7d0.jpg
     * ga_prefix : 110906
     * id : 8959706
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    @SerializedName("image_source")
    private String imageSource;
    private String title;
    private String image;
    @SerializedName("share_url")
    private String shareUrl;
    private String thumbnail;
    private int id;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
