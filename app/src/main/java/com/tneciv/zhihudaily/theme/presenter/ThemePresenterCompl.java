package com.tneciv.zhihudaily.theme.presenter;

import com.tneciv.zhihudaily.theme.view.IThemeView;

/**
 * Created by Tneciv on 1-31-0031.
 */
public class ThemePresenterCompl implements IThemePresenter {
    IThemeView iThemeView;

    public ThemePresenterCompl(IThemeView iThemeView) {
        this.iThemeView = iThemeView;
    }

    @Override
    public void getThemeList(String url) {

    }
}
