package com.tneciv.zhihudaily.base;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.home.model.HomeEventEntity;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.home_container)
    public RecyclerView recyclerView;
    @Bind(R.id.swipeRefresh)
    public SwipeRefreshLayout swipeRefresh;

    public SharedPreferences config;

    public BaseListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        config = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeResources(R.color.accent, R.color.primary);
        init();
        setRecyclerLayout();
        requestUrl();
        handleListData();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void operator(HomeEventEntity.OperatorType type) {
        if (type.getOperatorType() == "refresh") {
            swipeRefresh.setRefreshing(true);
            onRefresh();
        }
    }

    @Override
    public void onRefresh() {
        requestUrl();
    }

    /**
     * in order to execute
     */
    public abstract void init();

    public abstract void setRecyclerLayout();

    public abstract void requestUrl();

    public abstract void handleListData();

}
