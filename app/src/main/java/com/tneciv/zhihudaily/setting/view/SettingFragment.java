package com.tneciv.zhihudaily.setting.view;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.constants.Constants;

/**
 * Created by Tneciv
 * on 2016-06-23 18:46 .
 */
public class SettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceManager().setSharedPreferencesName(Constants.PREF_CONFIG_KEY);
        addPreferencesFromResource(R.xml.pref_settings);
        initSharedPref();
    }

    private void initSharedPref() {
        Preference version = findPreference("version");
        try {
            final PackageInfo packageInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
            final String versionName = packageInfo.versionName;
            version.setSummary("v" + versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

}
