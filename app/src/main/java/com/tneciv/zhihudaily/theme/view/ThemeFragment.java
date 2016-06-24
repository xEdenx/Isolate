package com.tneciv.zhihudaily.theme.view;


import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;

import com.tneciv.zhihudaily.api.ZhihuApi;
import com.tneciv.zhihudaily.base.BaseListFragment;
import com.tneciv.zhihudaily.constants.Constants;
import com.tneciv.zhihudaily.theme.model.ThemeEntity;
import com.tneciv.zhihudaily.theme.model.ThemeResultEntity;
import com.tneciv.zhihudaily.theme.presenter.IThemePresenter;
import com.tneciv.zhihudaily.theme.presenter.ThemePresenterCompl;
import com.tneciv.zhihudaily.theme.presenter.ThemeRecyclerAdapter;
import com.tneciv.zhihudaily.utils.view.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThemeFragment extends BaseListFragment implements IThemeView {


    private List<ThemeEntity> entities = new ArrayList<>();

    private IThemePresenter iThemePresenter;

    private ThemeRecyclerAdapter adapter;

    public ThemeFragment() {
    }

    @Override
    public void init() {
        boolean isNightMode = config.getBoolean(Constants.DAY_NIGHT_MODE, false);
        iThemePresenter = new ThemePresenterCompl(getContext());
        adapter = new ThemeRecyclerAdapter(getContext(), entities, isNightMode);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setRecyclerLayout() {
        int spanCount = 2;
        int spacing = 8;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), spanCount);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, false));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void requestUrl() {
        iThemePresenter.handleRequestUrl(ZhihuApi.THEME_LIST);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void updateView(ThemeResultEntity.ThemeList themeList) {
        List<ThemeEntity> themeListEntities = themeList.getEntities();
        this.entities.clear();
        this.entities.addAll(themeListEntities);
        adapter.notifyDataSetChanged();
        swipeRefresh.setRefreshing(false);
    }

}
