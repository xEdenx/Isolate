package com.tneciv.zhihudaily.detail.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;
import com.squareup.leakcanary.RefWatcher;
import com.squareup.picasso.Picasso;
import com.tneciv.zhihudaily.MyApplication;
import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.detail.model.ContentEntity;
import com.tneciv.zhihudaily.detail.presenter.DetailPresenterCompl;
import com.tneciv.zhihudaily.detail.presenter.IDetailPresenter;
import com.tneciv.zhihudaily.utils.CacheUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class DetailActivity extends AppCompatActivity implements IDeatilView {

    private String title;
    private int id;
    private IDetailPresenter iDetailPresenter;
    private boolean noImagesMode;
    private boolean nightMode;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        noImagesMode = preferences.getBoolean("noImagesMode", false);
        nightMode = preferences.getBoolean("dayNightMode", false);
        initView();
        iDetailPresenter = new DetailPresenterCompl(this);
        String cache = new CacheUtil(this).loadCache(String.valueOf(id));
        if (cache == null || "".equals(cache)) {
            iDetailPresenter.requestNewsContent(id);
        } else {
            Gson gson = new Gson();
            ContentEntity entity = gson.fromJson(cache, ContentEntity.class);
            showContent(entity);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
        RefWatcher watcher = MyApplication.getRefWatcher(this);
        watcher.watch(this);
    }

    private void initView() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        YoYo.with(Techniques.RollIn).playOn(fab);
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
        if (!noImagesMode) {
            Picasso.with(this).load(image).into(imgContent);
        }
        String body = entity.getBody();
        title = entity.getTitle();
        custTitle.setText(title);
        WebSettings settings = webView.getSettings();
        webviewSettings(settings);
        StringBuffer stringBuffer = handleHtml(body);
        webView.setDrawingCacheEnabled(true);
        webView.loadDataWithBaseURL("file:///android_asset/", stringBuffer.toString(), "text/html", "utf-8", null);
    }

    @NonNull
    private StringBuffer handleHtml(String body) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"css/detail.css\" ></head>");
        stringBuffer.append(nightMode ? "<body class=\"night\">" : "<body>");
        stringBuffer.append(body);
        stringBuffer.append("</body></html>");
        return stringBuffer;
    }

    private void webviewSettings(WebSettings settings) {
        settings.setDatabaseEnabled(true);

        if (noImagesMode) {
            Picasso.with(this).load(R.drawable.smile_handmaking).into(imgContent);
            settings.setLoadsImagesAutomatically(false);
        } else {
            settings.setLoadsImagesAutomatically(true);
        }

        String dbPath = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setDomStorageEnabled(true);
        settings.setAppCachePath(dbPath);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
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
