package com.tneciv.zhihudaily.theme.view;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.WaitActivity;
import com.tneciv.zhihudaily.base.BaseActivity;
import com.tneciv.zhihudaily.base.ErrorEntity;
import com.tneciv.zhihudaily.history.view.HistoryFragment;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class ThemeActivity extends WaitActivity {

//    @Override
//    public void initView() {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame_base, new ThemeFragment());
//        transaction.commit();
//    }
//
//    @Subscribe(threadMode = ThreadMode.MainThread)
//    public void errorHandler(ErrorEntity errorEntity) {
//        String msg = errorEntity.getMsg();
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
//        startActivityByName(ThemeActivity.class, true);
//    }
}
