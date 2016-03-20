package com.tneciv.zhihudaily.theme.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.picassopalette.BitmapPalette;
import com.github.florent37.picassopalette.PicassoPalette;
import com.squareup.picasso.Picasso;
import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.theme.model.ThemeEntity;
import com.tneciv.zhihudaily.theme.model.ThemeResultEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by Tneciv on 2-6-0006.
 */
public class ThemeRecyclerAdapter extends RecyclerView.Adapter<ThemeRecyclerAdapter.ThemeViewHolder> {
    Context context;
    List<ThemeEntity> entities;
    LayoutInflater inflater;
    Boolean isNightMode;

    public ThemeRecyclerAdapter(Context context, List<ThemeEntity> entities, boolean isNightMode) {
        this.context = context;
        this.entities = entities;
        inflater = LayoutInflater.from(context);
        this.isNightMode = isNightMode;
    }

    @Override
    public ThemeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.theme_item, parent, false);
        ThemeViewHolder viewHolder = new ThemeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ThemeViewHolder holder, int position) {
        ThemeEntity themeEntity = entities.get(position);
        String thumbnail = themeEntity.getThumbnail();
        LinearLayout textAera = holder.themeTextAera;
        holder.themeTitle.setText(themeEntity.getName());
        holder.themeDesc.setText(themeEntity.getDescription());
        if (isNightMode) {
            Picasso.with(context).load(thumbnail).into(holder.imageTheme);
        } else {
            Picasso.with(context).load(thumbnail).into(holder.imageTheme, PicassoPalette.with(thumbnail, holder.imageTheme)
                    .use(PicassoPalette.Profile.MUTED_LIGHT)
                    .intoBackground(textAera, PicassoPalette.Swatch.RGB)
                    .intoTextColor(holder.themeTitle, PicassoPalette.Swatch.BODY_TEXT_COLOR)
                    .intoTextColor(holder.themeDesc, PicassoPalette.Swatch.BODY_TEXT_COLOR)
            );
        }
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    public class ThemeViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image_theme)
        ImageView imageTheme;
        @Bind(R.id.theme_title)
        TextView themeTitle;
        @Bind(R.id.theme_desc)
        TextView themeDesc;
        @Bind(R.id.theme_textAera)
        LinearLayout themeTextAera;

        public ThemeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.theme_item)
        void click(View view) {
            int position = getLayoutPosition();
            ThemeEntity entity = entities.get(position);
            int id = entity.getId();
            ThemeResultEntity.ThemeId themeId = new ThemeResultEntity.ThemeId(id);
            EventBus.getDefault().post(themeId);
        }
    }
}
