package com.tneciv.zhihudaily.history.view;

import android.support.v4.app.FragmentTransaction;

import com.tneciv.zhihudaily.base.BaseActivity;
import com.tneciv.zhihudaily.R;

public class HistoryActivity extends BaseActivity {

    @Override
    public void initView() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_base, new HistoryFragment());
        transaction.commit();
    }

}
