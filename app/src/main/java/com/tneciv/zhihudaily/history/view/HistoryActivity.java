package com.tneciv.zhihudaily.history.view;

import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tneciv.zhihudaily.base.BaseActivity;
import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.base.ErrorEntity;
import com.tneciv.zhihudaily.home.view.MainActivity;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class HistoryActivity extends BaseActivity {

    @Override
    public void initView() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_base, new HistoryFragment());
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_calendar) {
            initView();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
