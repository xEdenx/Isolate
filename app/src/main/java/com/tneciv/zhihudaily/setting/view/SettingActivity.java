package com.tneciv.zhihudaily.setting.view;

import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.base.BaseActivity;

public class SettingActivity extends BaseActivity {

    @Override
    public void initView() {
        android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_base, new SettingFragment());
        fragmentTransaction.commit();
    }
}
