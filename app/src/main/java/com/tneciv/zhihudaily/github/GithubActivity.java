package com.tneciv.zhihudaily.github;

import android.support.v4.app.FragmentTransaction;

import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.base.BaseActivity;

public class GithubActivity extends BaseActivity {

    @Override
    public void initView() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_base, new GitHubFragment());
        transaction.commit();
    }

}
