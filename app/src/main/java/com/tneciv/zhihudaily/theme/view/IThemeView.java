package com.tneciv.zhihudaily.theme.view;

import com.tneciv.zhihudaily.theme.model.ThemeEntity;
import com.tneciv.zhihudaily.theme.model.ThemeResultEntity;

import java.util.List;

/**
 * Created by Tneciv on 1-31-0031.
 */
public interface IThemeView {
    void updateView(ThemeResultEntity.ThemeList themeList);
}
