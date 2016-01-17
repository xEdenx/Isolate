package com.tneciv.zhihudaily.home.view;

import com.tneciv.zhihudaily.home.model.HomeEventEntity;
import com.tneciv.zhihudaily.home.model.HotEntity;

import java.util.List;

/**
 * Created by Tneciv on 1-17-0017.
 */
public interface IHotView {
    void showHotResult(HomeEventEntity.HotEntityList hotEntityList);
}
