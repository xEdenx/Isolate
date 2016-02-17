package com.tneciv.zhihudaily.detail.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
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
//    @Bind(R.id.fab)
//    FloatingActionButton fab;
    IDetailPresenter iDetailPresenter;
    @Bind(R.id.imgContent)
    ImageView imgContent;
    @Bind(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.custTitle)
    TextView custTitle;
    @Bind(R.id.webView)
    WebView webView;


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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        Window window = getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        collapsingToolbar.setTitle(title);
        custTitle.setText(title);
        id = intent.getIntExtra("id", 0);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "别瞎点", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void showContent(ContentEntity entity) {
        String image = entity.getImage();
        String body = entity.getBody();
        String title = entity.getTitle();
        custTitle.setText(title);
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(false);
        String css = "<style>img{display: inline;height: auto;max-width: 100%;}.author{font-size:25px}.bio{font-size:18px}</style>";
        webView.setDrawingCacheEnabled(true);
        webView.loadDataWithBaseURL(null, css + body, "text/html", "utf-8", null);
        Picasso.with(this).load(image).into(imgContent);
    }

}
