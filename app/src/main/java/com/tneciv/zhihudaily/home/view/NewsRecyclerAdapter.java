package com.tneciv.zhihudaily.home.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.detail.view.DetailActivity;
import com.tneciv.zhihudaily.home.model.NewsEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tneciv on 1-15-0015.
 */
public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder> {
    List<NewsEntity> list = new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    public NewsRecyclerAdapter(Context context, List<NewsEntity> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.news_item, parent, false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(view);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        NewsEntity entity = list.get(position);
        holder.titleNews.setText(entity.getTitle());
        Picasso.with(context).load(entity.getImages().get(0)).into(holder.imgNews);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_news)
        ImageView imgNews;
        @Bind(R.id.title_news)
        TextView titleNews;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_container)
        void click(View view) {
            int position = getLayoutPosition();
            NewsEntity entity = list.get(position);
            int id = entity.getId();
            String title = entity.getTitle();
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("title", title);
            view.getContext().startActivity(intent);
        }
    }
}
