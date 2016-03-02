package com.tneciv.zhihudaily.theme.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.api.ZhihuApi;
import com.tneciv.zhihudaily.base.GridSpacingItemDecoration;
import com.tneciv.zhihudaily.theme.model.ThemeEntity;
import com.tneciv.zhihudaily.theme.model.ThemeResultEntity;
import com.tneciv.zhihudaily.theme.presenter.IThemePresenter;
import com.tneciv.zhihudaily.theme.presenter.ThemePresenterCompl;
import com.tneciv.zhihudaily.theme.presenter.ThemeRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThemeFragment extends Fragment implements IThemeView, SwipeRefreshLayout.OnRefreshListener {


    @Bind(R.id.home_container)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    List<ThemeEntity> entities = new ArrayList<>();

    IThemePresenter iThemePresenter;

    ThemeRecyclerAdapter adapter;

    final String url = ZhihuApi.THEME_LIST;

    public ThemeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        iThemePresenter = new ThemePresenterCompl(this);
        iThemePresenter.handleRequestUrl(url);
        adapter = new ThemeRecyclerAdapter(getContext(), entities);
        recyclerView.setAdapter(adapter);
        int spanCount = 2;
        int spacing = 8;
        boolean includeEdge = false;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), spanCount);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeRefresh.setColorSchemeResources(R.color.accent);
        swipeRefresh.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
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

    @Override
    public void onRefresh() {
        swipeRefresh.setRefreshing(true);
        iThemePresenter.handleRequestUrl(url);
    }

//    @Subscribe(threadMode = ThreadMode.MainThread)
//    public void getThemeItem(ThemeResultEntity.ThemeId themeId) {
//        int themeIdId = themeId.getId();
//        String themeNewsUrl = ZhihuApi.getThemeNewsUrl(themeIdId);
//        NewsFragmnt fragmnt = new NewsFragmnt();
//        Bundle bundle = new Bundle();
//        bundle.putString("themeIdUrl", themeNewsUrl);
//        fragmnt.setArguments(bundle);
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame_base, fragmnt);
//        transaction.commit();
//    }
}
