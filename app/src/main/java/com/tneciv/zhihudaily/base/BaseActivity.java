package com.tneciv.zhihudaily.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.about.AboutActivity;
import com.tneciv.zhihudaily.costants.ErrorEntity;
import com.tneciv.zhihudaily.github.GithubActivity;
import com.tneciv.zhihudaily.history.view.HistoryActivity;
import com.tneciv.zhihudaily.home.view.MainActivity;
import com.tneciv.zhihudaily.theme.view.ThemeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.frame_base)
    FrameLayout frameLayout;
    @Bind(R.id.nav_view_base)
    NavigationView navigationView;
    @Bind(R.id.drawer_layout_base)
    DrawerLayout drawerLayout;
    @Bind(R.id.toolbar_base)
    Toolbar toolbar;

    private SharedPreferences config;
    public int mDayNightMode = AppCompatDelegate.MODE_NIGHT_AUTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        config = getSharedPreferences("config", Context.MODE_PRIVATE);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        drawerSetting();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        int uiMode = getResources().getConfiguration().uiMode;
        int dayNightUiMode = uiMode & Configuration.UI_MODE_NIGHT_MASK;

        if (dayNightUiMode == Configuration.UI_MODE_NIGHT_NO) {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_NO;
        } else if (dayNightUiMode == Configuration.UI_MODE_NIGHT_YES) {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_YES;
        } else {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_AUTO;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    public abstract void initView();

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main, menu);
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
            startActivityByName(ThemeActivity.class, true);
        } else if (id == R.id.nav_slideshow) {
            startActivityByName(HistoryActivity.class, true);
        } else if (id == R.id.nav_send) {
            startActivityByName(AboutActivity.class, true);
        } else if (id == R.id.nav_gitHub) {
            startActivityByName(GithubActivity.class, true);
        } else if (id == R.id.noImagesSwitch) {
            drawerSetting();
        } else if (id == R.id.dayNightSwitch) {
            drawerSetting();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void startActivityByName(Class<?> activityName, Boolean isFinish) {
        Intent intent = new Intent(this, activityName);
        startActivity(intent);
        if (isFinish) {
            finish();
        }
        return;
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void errorHandler(ErrorEntity errorEntity) {
        String msg = errorEntity.getMsg();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void drawerSetting() {

        MenuItem noImageItem = navigationView.getMenu().findItem(R.id.noImagesSwitch);
        SwitchCompat noImagesSwitch = (SwitchCompat) MenuItemCompat.getActionView(noImageItem).findViewById(R.id.noImagesSwitch);
        boolean noImagesMode = config.getBoolean("noImagesMode", false);
        if (noImagesMode) {
            noImagesSwitch.setChecked(true);
        } else {
            noImagesSwitch.setChecked(false);
        }
        noImagesSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    config.edit().putBoolean("noImagesMode", true).apply();
                } else {
                    config.edit().putBoolean("noImagesMode", false).apply();
                }
            }
        });

        MenuItem dayNightItem = navigationView.getMenu().findItem(R.id.dayNightSwitch);
        SwitchCompat dayNightSwitch = (SwitchCompat) MenuItemCompat.getActionView(dayNightItem).findViewById(R.id.dayNightSwitch);
        boolean nightMode = config.getBoolean("dayNightMode", false);
        if (nightMode) {
            dayNightSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            dayNightSwitch.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        dayNightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    config.edit().putBoolean("dayNightMode", true).apply();
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                } else {
                    config.edit().putBoolean("dayNightMode", false).apply();
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                }
            }
        });

    }

}
