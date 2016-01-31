package com.tneciv.zhihudaily.theme.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.api.ZhihuApi;
import com.tneciv.zhihudaily.theme.presenter.IThemePresenter;
import com.tneciv.zhihudaily.theme.presenter.ThemePresenterCompl;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThemeFragment extends Fragment implements IThemeView {


    @Bind(R.id.home_container)
    RecyclerView homeContainer;
    @Bind(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    IThemePresenter iThemePresenter;

    public ThemeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        iThemePresenter = new ThemePresenterCompl(this);
        String url = ZhihuApi.THEME_LIST;
        iThemePresenter.getThemeList(url);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void updateView() {

    }
}
