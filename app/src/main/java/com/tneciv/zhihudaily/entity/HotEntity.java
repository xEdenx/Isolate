package com.tneciv.zhihudaily.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tneciv
 * on 2016-11-10 12:24 .
 */

public class HotEntity {

    private List<RecentBean> recent;

    public List<RecentBean> getRecent() {
        return recent;
    }

    public void setRecent(List<RecentBean> recent) {
        this.recent = recent;
    }

    public static class RecentBean {
        /**
         * news_id : 8950063
         * url : http://news-at.zhihu.com/api/2/news/8950063
         * thumbnail : http://pic3.zhimg.com/e3a71e66a93b1311fff6e451e8ae1682.jpg
         * title : 当心，心肌梗死一开始的表现，很可能并不是心脏痛
         */

        @SerializedName("news_id")
        private int newsId;
        private String url;
        private String thumbnail;
        private String title;

        public int getNewsId() {
            return newsId;
        }

        public void setNewsId(int newsId) {
            this.newsId = newsId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
