package com.tneciv.zhihudaily.home.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.squareup.leakcanary.RefWatcher;
import com.tneciv.zhihudaily.MyApplication;
import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.about.AboutActivity;
import com.tneciv.zhihudaily.costants.ErrorEntity;
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

    private static final int PERMISSION_WRITE_EXT = 222;

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

    private SharedPreferences config;

    private boolean nightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        askForPermission();
        config = getSharedPreferences("config", Context.MODE_PRIVATE);
        nightMode = config.getBoolean("dayNightMode", false);
        showIntro();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
        RefWatcher watcher = MyApplication.getRefWatcher(this);
        watcher.watch(this);
    }

    private void showIntro() {
        Boolean flag = config.getBoolean("showIntro", false);
        if (!flag) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initView() {
        List<Fragment> fragmentList = new ArrayList<Fragment>(Arrays.asList(new NewsFragmnt(), new HotFragment()));
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new HomeEventEntity.OperatorType("refresh"));
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        ViewpagerAdapter viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager(), this, fragmentList);
        viewpagerHome.setAdapter(viewpagerAdapter);
        tabHome.setupWithViewPager(viewpagerHome);
        tabHome.setTabMode(TabLayout.MODE_FIXED);
        tabHome.setTabGravity(TabLayout.GRAVITY_FILL);

        drawerSetting();

    }

    private void drawerSetting() {

        setNoImageMode();

        setNightMode();
    }

    private void setNightMode() {
        MenuItem dayNightItem = navigationView.getMenu().findItem(R.id.dayNightSwitch);
        SwitchCompat dayNightSwitch = (SwitchCompat) MenuItemCompat.getActionView(dayNightItem).findViewById(R.id.dayNightSwitch);
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
                    enableNightMode();
                } else {
                    disableNightMode();
                }
            }
        });
    }

    private void disableNightMode() {
        config.edit().putBoolean("dayNightMode", false).apply();
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        recreate();
    }

    private void enableNightMode() {
        config.edit().putBoolean("dayNightMode", true).apply();
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        recreate();
    }

    private void setNoImageMode() {
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

//        if (id == R.id.action_nightMode) {
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
        } else if (id == R.id.noImagesSwitch) {
            drawerSetting();
        } else if (id == R.id.dayNightSwitch) {
            drawerSetting();
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
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void errorNotify(ErrorEntity errorEntity) {
        String msg = errorEntity.getMsg();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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

    private void askForPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //申请写入权限
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                }
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION_WRITE_EXT);
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

        if (requestCode == PERMISSION_WRITE_EXT) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Snackbar.make(fab, "读写文件权限被拒绝，将不能缓存数据.", Snackbar.LENGTH_SHORT).setAction("授权", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        askForPermission();
                    }
                }).show();
            }
        }

    }
}
