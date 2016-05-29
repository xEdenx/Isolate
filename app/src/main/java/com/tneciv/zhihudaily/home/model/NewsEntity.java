package com.tneciv.zhihudaily.home.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tneciv on 1-15-0015.
 */
public class NewsEntity implements Serializable {

    private static final long serialVersionUID = -2272364171423006034L;
    /**
     * title : 他经常被嘲笑长得太可爱，慢慢就想成为女孩子
     * ga_prefix : 011521
     * images : ["http://pic4.zhimg.com/52d43efd84ad69c32897a06dc33aec0f.jpg"]
     * multipic : true
     * type : 0
     * id : 7654843
     */

    private String title;
    private String ga_prefix;
    private boolean multipic;
    private int type;
    private int id;
    private List<String> images;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public boolean isMultipic() {
        return multipic;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public List<String> getImages() {
        return images;
    }
}
