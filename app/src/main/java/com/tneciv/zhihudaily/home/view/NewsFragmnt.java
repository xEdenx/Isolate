package com.tneciv.zhihudaily.home.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.home.model.NewsEntity;
import com.tneciv.zhihudaily.home.presenter.INewsPresenter;
import com.tneciv.zhihudaily.home.presenter.NewsPresenterCompl;

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
public class NewsFragmnt extends Fragment implements INewsView {

    INewsPresenter iNewsPresenter;

    @Bind(R.id.home_container)
    RecyclerView recyclerView;
    List<NewsEntity> newsEntityList = new ArrayList<>();
    HomeRecyclerAdapter homeRecyclerAdapter;

    public NewsFragmnt() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iNewsPresenter = new NewsPresenterCompl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);

//        EventBus.getDefault().post(OperatorTag.REFRESH);
        homeRecyclerAdapter = new HomeRecyclerAdapter(getContext(), newsEntityList);
        recyclerView.setAdapter(homeRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showResult(List<NewsEntity> list) {

        Log.d("NewsFragmnt", "show");
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void setNewsEntityList(List<NewsEntity> list) {
        Log.d("NewsFragmnt", "list:" + list);
        newsEntityList.clear();
        newsEntityList.addAll(list);
        homeRecyclerAdapter.notifyDataSetChanged();
    }


}
