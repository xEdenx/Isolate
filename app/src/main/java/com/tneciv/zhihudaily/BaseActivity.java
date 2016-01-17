package com.tneciv.zhihudaily;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.tneciv.zhihudaily.home.view.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Bind(R.id.toolbar_base)
    Toolbar toolbar;
    @Bind(R.id.nav_view_base)
    NavigationView navigationView;
    @Bind(R.id.drawer_layout_base)
    DrawerLayout drawer;
    @Bind(R.id.containerBase)
    FrameLayout containerBase;
    @Bind(R.id.appBarBase)
    AppBarLayout appBarBase;
    @Bind(R.id.baseCoordLayout)
    CoordinatorLayout baseCoordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(R.layout.activity_base);
    }

    private void initView() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initContentView(@LayoutRes int layoutResID) {
        getDelegate().setContentView(layoutResID);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, containerBase, true);
    }

    @Override
    public void setContentView(View view) {
        containerBase.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        containerBase.addView(view, params);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_recent) {
            startActivityByName(MainActivity.class, true);
        } else if (id == R.id.nav_theme) {
            startActivityByName(BaseActivity.class, true);
        } else if (id == R.id.nav_slideshow) {
            startActivityByName(TestActivity.class, true);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_gitHub) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    void startActivityByName(Class<?> activityName, Boolean isFinish) {
        Intent intent = new Intent(this, activityName);
        startActivity(intent);
        if (isFinish) {
            finish();
        }
        return;
    }
}
