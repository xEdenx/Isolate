package com.tneciv.zhihudaily.home.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.picassopalette.PicassoPalette;
import com.squareup.picasso.Picasso;
import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.detail.view.DetailActivity;
import com.tneciv.zhihudaily.home.model.HotEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tneciv on 1-17-0017.
 */
public class HotRecyclerAdapter extends RecyclerView.Adapter<HotRecyclerAdapter.HotViewHolder> {

    private Context context;
    private List<HotEntity> list = new ArrayList<>();
    private LayoutInflater inflater;
    private Boolean isNightMode;

    public HotRecyclerAdapter(Context context, List<HotEntity> list, Boolean isNightMode) {
        this.context = context;
        this.list = list;
        this.isNightMode = isNightMode;
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
        String imgUrl = entity.getThumbnail();
        TextView titleHot = holder.titleHot;
        titleHot.setText(entity.getTitle());
        if (isNightMode) {
            Picasso.with(context).load(imgUrl).into(holder.imgHot);
        } else {
            Picasso.with(context).load(imgUrl).into(holder.imgHot, PicassoPalette.with(imgUrl, holder.imgHot)
                    .use(PicassoPalette.Profile.MUTED_LIGHT)
                    .intoBackground(titleHot, PicassoPalette.Swatch.RGB)
                    .intoTextColor(titleHot, PicassoPalette.Swatch.BODY_TEXT_COLOR)
            );
        }
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
