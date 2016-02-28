package com.tneciv.zhihudaily.home.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.about.AboutActivity;
import com.tneciv.zhihudaily.base.ErrorEntity;
import com.tneciv.zhihudaily.github.GithubActivity;
import com.tneciv.zhihudaily.history.view.HistoryActivity;
import com.tneciv.zhihudaily.home.model.HomeEventEntity;
import com.tneciv.zhihudaily.theme.view.ThemeActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<Fragment> fragmentList;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tab_home)
    TabLayout tabHome;
    @Bind(R.id.viewpager_home)
    ViewPager viewpagerHome;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        isNeedToShowIntro();
        initView();
    }

    private void isNeedToShowIntro() {
        SharedPreferences preferences = getSharedPreferences("config", MODE_PRIVATE);
        Boolean flag = preferences.getBoolean("isIntroed", false);
        if (flag == false) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        fragmentList = new ArrayList<Fragment>(Arrays.asList(new NewsFragmnt(), new HotFragment()));
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new HomeEventEntity.OperatorType("refresh"));
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        ViewpagerAdapter viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager(), this, fragmentList);
        viewpagerHome.setAdapter(viewpagerAdapter);
        tabHome.setupWithViewPager(viewpagerHome);
        tabHome.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_recent) {
            startActivityByName(MainActivity.class, true);
        } else if (id == R.id.nav_theme) {
            startActivityByName(ThemeActivity.class, true);
        } else if (id == R.id.nav_slideshow) {
            startActivityByName(HistoryActivity.class, true);
        } else if (id == R.id.nav_send) {
            startActivityByName(AboutActivity.class, true);
        } else if (id == R.id.nav_gitHub) {
            startActivityByName(GithubActivity.class, true);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void startActivityByName(Class<?> activityName, Boolean isFinish) {
        Intent intent = new Intent(this, activityName);
        startActivity(intent);
        if (isFinish) {
            finish();
        }
        return;
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void errorNotify(ErrorEntity errorEntity) {
        String msg = errorEntity.getMsg();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        startActivityByName(MainActivity.class, true);
    }

    /**
     * 实现再按一次退出提醒
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 3000) {
                Snackbar.make(fab, "再按一次退出", Snackbar.LENGTH_SHORT).setAction("立即退出", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
