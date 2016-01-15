package com.tneciv.zhihudaily.home.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

import com.tneciv.zhihudaily.home.model.NewsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tneciv on 1-15-0015.
 */
public class HomeRecyclerAdapter extends RecyclerView.Adapter {
    List<NewsEntity> list = new ArrayList<>();
    Context context;

    public HomeRecyclerAdapter(Context context, List<NewsEntity> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
