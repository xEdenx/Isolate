package com.tneciv.zhihudaily.theme.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tneciv on 2-12-0012.
 */
public final class ThemeResultEntity implements Serializable {

    private static final long serialVersionUID = -4321779320129795368L;

    private ThemeResultEntity() throws InstantiationException{
        throw new InstantiationException("This class is not for instantiation");
    }

    public static class ThemeList {
        private List<ThemeEntity> entities;

        public ThemeList(List<ThemeEntity> entities) {
            this.entities = entities;
        }

        public List<ThemeEntity> getEntities() {
            return entities;
        }

        public void setEntities(List<ThemeEntity> entities) {
            this.entities = entities;
        }
    }

    public static class ThemeId {
        private int id;

        public ThemeId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
