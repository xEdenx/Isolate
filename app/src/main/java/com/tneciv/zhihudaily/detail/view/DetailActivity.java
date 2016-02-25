package com.tneciv.zhihudaily.detail.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    IDetailPresenter iDetailPresenter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.imgContent)
    ImageView imgContent;
    @Bind(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.custTitle)
    TextView custTitle;
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.nested)
    NestedScrollView nested;
    @Bind(R.id.coor)
    CoordinatorLayout coor;


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

        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        collapsingToolbar.setTitle(title);
        custTitle.setText(title);
        id = intent.getIntExtra("id", 0);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();
            }
        });
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int i = imgContent.getHeight() - toolbar.getHeight() * 2;
                if (verticalOffset <= -i) {
                    fab.setVisibility(View.GONE);
                } else {
                    fab.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void showContent(ContentEntity entity) {
        String image = entity.getImage();
        String body = entity.getBody();
        title = entity.getTitle();
        custTitle.setText(title);
        WebSettings settings = webView.getSettings();
        StringBuffer stringBuffer = new StringBuffer();
        settings.setUseWideViewPort(false);
        stringBuffer.append("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"css/detail.css\" ></head>");
        stringBuffer.append("<body>");
        stringBuffer.append(body);
        stringBuffer.append("</body></html>");
        webView.setDrawingCacheEnabled(true);
        webView.loadDataWithBaseURL("file:///android_asset/", stringBuffer.toString(), "text/html", "utf-8", null);
        Picasso.with(this).load(image).into(imgContent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

//        if (itemId == R.id.action_share) {
//            share();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "来自「壁上观」的分享：" + title + "，http://daily.zhihu.com/story/" + id);
        startActivity(Intent.createChooser(intent, getTitle()));
    }

}
