package com.tneciv.zhihudaily.home.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.detail.view.DetailActivity;
import com.tneciv.zhihudaily.home.model.HotEntity;
import com.tneciv.zhihudaily.home.model.NewsEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tneciv on 1-17-0017.
 */
public class HotRecyclerAdapter extends RecyclerView.Adapter<HotRecyclerAdapter.HotViewHolder> {

    Context context;
    List<HotEntity> list = new ArrayList<>();
    LayoutInflater inflater;

    public HotRecyclerAdapter(Context context, List<HotEntity> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public HotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.hot_item, parent, false);
        HotViewHolder hotViewHolder = new HotViewHolder(view);
        return hotViewHolder;
    }

    @Override
    public void onBindViewHolder(HotViewHolder holder, int position) {
        HotEntity entity = list.get(position);
        holder.titleHot.setText(entity.getTitle());
        Picasso.with(context).load(entity.getThumbnail()).into(holder.imgHot);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_hot)
        ImageView imgHot;
        @Bind(R.id.title_hot)
        TextView titleHot;

        public HotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.hot_container)
        void click(View view) {
            int position = getLayoutPosition();
            HotEntity entity = list.get(position);
            int id = entity.getNews_id();
            String title = entity.getTitle();
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("title", title);
            view.getContext().startActivity(intent);
        }
    }
}
