package com.tneciv.zhihudaily.detail.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.detail.model.ContentEntity;
import com.tneciv.zhihudaily.detail.presenter.DetailPresenterCompl;
import com.tneciv.zhihudaily.detail.presenter.IDetailPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class DetailActivity extends AppCompatActivity implements IDeatilView {

    String title;
    int id;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    IDetailPresenter iDetailPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
        iDetailPresenter = new DetailPresenterCompl(this);
        iDetailPresenter.requestNewsContent(id);
    }

    private void initView() {
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        getSupportActionBar().setTitle(title);
        id = intent.getIntExtra("id", 0);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own here", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void showContent(ContentEntity entity) {
        String shareUrl = entity.getShare_url();
        Toast.makeText(this, "Url" + shareUrl, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }
}
