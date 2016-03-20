package com.tneciv.zhihudaily.theme.view;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.api.ZhihuApi;
import com.tneciv.zhihudaily.base.BaseActivity;
import com.tneciv.zhihudaily.home.view.NewsFragmnt;
import com.tneciv.zhihudaily.theme.model.ThemeResultEntity;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class ThemeActivity extends BaseActivity {

    @Override
    public void initView() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_base, new ThemeListFragment());
        transaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void getThemeItem(ThemeResultEntity.ThemeId themeId) {
        int themeIdId = themeId.getId();
        String themeNewsUrl = ZhihuApi.getThemeNewsUrl(themeIdId);
        NewsFragmnt fragmnt = new NewsFragmnt();
        Bundle bundle = new Bundle();
        bundle.putString("themeIdUrl", themeNewsUrl);
        fragmnt.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(String.valueOf(themeIdId));
        transaction.replace(R.id.frame_base, fragmnt).commit();
    }
}
