package com.tneciv.zhihudaily.home.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tneciv.zhihudaily.Api.ZhihuApi;
import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.home.model.HomeEventEntity;
import com.tneciv.zhihudaily.home.model.HotEntity;
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
public class HotFragment extends Fragment implements IHotView {

    INewsPresenter iNewsPresenter;

    @Bind(R.id.home_container)
    RecyclerView recyclerView;

    List<HotEntity> hotEntities = new ArrayList<>();
    HotRecyclerAdapter recyclerAdapter;

    public HotFragment() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iNewsPresenter = new NewsPresenterCompl(this);
        iNewsPresenter.requestUrl(ZhihuApi.NEWS_HOT);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        recyclerAdapter = new HotRecyclerAdapter(getContext(), hotEntities);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        return view;
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void showHotResult(HomeEventEntity.HotEntityList hotEntityList) {
        List<HotEntity> hotEntities = hotEntityList.getHotEntities();
        this.hotEntities.clear();
        this.hotEntities.addAll(hotEntities);
        recyclerAdapter.notifyDataSetChanged();
    }
}
