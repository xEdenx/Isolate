package com.tneciv.zhihudaily.home.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tneciv.zhihudaily.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tneciv on 1-15-0015.
 */
public class ViewpagerAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private List<Fragment> fragments = new ArrayList<>();

    public ViewpagerAdapter(FragmentManager fm, Context context, List<Fragment> fragments) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String[] titles = context.getResources().getStringArray(R.array.title_tab);
        return titles[position];
    }
}
