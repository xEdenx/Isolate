package com.tneciv.zhihudaily.home.model;

import java.util.List;

/**
 * Created by Tneciv on 1-17-0017.
 */
public final class HomeEventEntity {

    private HomeEventEntity() throws InstantiationException{
        throw new InstantiationException("This class is not for instantiation");
    }

    public static class HotEntityList {
        private List<HotEntity> hotEntities;

        public HotEntityList(List<HotEntity> hotEntities) {
            this.hotEntities = hotEntities;
        }

        public List<HotEntity> getHotEntities() {
            return hotEntities;
        }

        public void setHotEntities(List<HotEntity> hotEntities) {
            this.hotEntities = hotEntities;
        }
    }

    public static class NewEntityList {
        private List<NewsEntity> newsEntityList;

        public NewEntityList(List<NewsEntity> newsEntityList) {
            this.newsEntityList = newsEntityList;
        }

        public List<NewsEntity> getNewsEntityList() {
            return newsEntityList;
        }

        public void setNewsEntityList(List<NewsEntity> newsEntityList) {
            this.newsEntityList = newsEntityList;
        }
    }

    public static class OperatorType {
        private String operatorType;

        public OperatorType(String operatorType) {
            this.operatorType = operatorType;
        }

        public String getOperatorType() {
            return operatorType;
        }

        public void setOperatorType(String operatorType) {
            this.operatorType = operatorType;
        }
    }
}
