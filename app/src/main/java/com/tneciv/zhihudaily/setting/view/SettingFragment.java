package com.tneciv.zhihudaily.setting.view;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import com.jakewharton.disklrucache.DiskLruCache;
import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.constants.Constants;
import com.tneciv.zhihudaily.utils.CacheUtil;

import java.io.IOException;

/**
 * Created by Tneciv
 * on 2016-06-23 18:46 .
 */
public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {

    private Preference basic;
    private SwitchPreference noImageMode;
    private SwitchPreference dayNightMode;
    private Preference cacheClean;
    private DiskLruCache diskLruCache;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceManager().setSharedPreferencesName(Constants.PREF_CONFIG_KEY);
        addPreferencesFromResource(R.xml.pref_settings);
        diskLruCache = new CacheUtil(getActivity()).getDiskLruCache(Constants.CACHE_DIR);
        long cacheSize = diskLruCache.size();
        initSharedPref();
        long l = cacheSize < 1024 ? 1 : cacheSize / 1024;
        cacheClean.setSummary("当前缓存大小为：" + l + "kb");
    }

    private void initSharedPref() {
        basic = findPreference("basic");
        noImageMode = (SwitchPreference) findPreference(Constants.NO_IMAGE_MODE);
        dayNightMode = (SwitchPreference) findPreference(Constants.DAY_NIGHT_MODE);
        cacheClean = findPreference(Constants.CACHE_CLEAN);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()) {
            case Constants.CACHE_CLEAN:
                try {
                    diskLruCache.delete();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        switch (preference.getKey()) {
            case Constants.NO_IMAGE_MODE:
                return true;
            default:
                break;
        }
        return false;
    }
}
