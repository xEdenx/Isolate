package com.tneciv.zhihudaily.home.view;


import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.tneciv.zhihudaily.api.ZhihuApi;
import com.tneciv.zhihudaily.base.BaseListFragment;
import com.tneciv.zhihudaily.home.model.HomeEventEntity;
import com.tneciv.zhihudaily.home.model.NewsEntity;
import com.tneciv.zhihudaily.home.presenter.INewsPresenter;
import com.tneciv.zhihudaily.home.presenter.NewsPresenterCompl;
import com.tneciv.zhihudaily.utils.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragmnt extends BaseListFragment implements INewsView {

    INewsPresenter iNewsPresenter;

    String url;

    List<NewsEntity> newsEntityList = new ArrayList<>();

    NewsRecyclerAdapter newsRecyclerAdapter;

    public NewsFragmnt() {
    }

    @Override
    public void init() {
        iNewsPresenter = new NewsPresenterCompl(this);
        newsRecyclerAdapter = new NewsRecyclerAdapter(getContext(), newsEntityList);
        recyclerView.setAdapter(newsRecyclerAdapter);
    }

    @Override
    public void setRecyclerLayout() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void requestUrl() {
        if (this.getArguments() == null) {
            url = ZhihuApi.NEWS_LATEST;
        } else {
            if (this.getArguments().getString("historyUrl") != null) {
                url = this.getArguments().getString("historyUrl");
            } else if (this.getArguments().getString("themeIdUrl") != null) {
                url = this.getArguments().getString("themeIdUrl");
            }
        }
        iNewsPresenter.requestUrl(url);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void updateView(HomeEventEntity.NewEntityList entityList) {
        List<NewsEntity> list = entityList.getNewsEntityList();
        this.newsEntityList.clear();
        this.newsEntityList.addAll(list);
        newsRecyclerAdapter.notifyDataSetChanged();
        swipeRefresh.setRefreshing(false);
    }

}
