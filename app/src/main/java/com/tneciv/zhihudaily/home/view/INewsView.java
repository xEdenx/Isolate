package com.tneciv.zhihudaily.home.view;


import com.tneciv.zhihudaily.home.model.NewsEntity;

import java.util.List;

/**
 * Created by Tneciv on 1-15-0015.
 */
public interface INewsView {
    void showResult(List<NewsEntity> list);
}
