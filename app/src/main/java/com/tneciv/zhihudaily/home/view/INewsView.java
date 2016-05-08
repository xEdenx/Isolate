package com.tneciv.zhihudaily.home.view;


import com.tneciv.zhihudaily.home.model.HomeEventEntity;

/**
 * Created by Tneciv on 1-15-0015.
 */
public interface INewsView {
    void updateView(HomeEventEntity.NewEntityList newEntityList);
}
