package com.tneciv.zhihudaily.setting.view;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.tneciv.zhihudaily.R;

/**
 * Created by Tneciv
 * on 2016-06-23 18:46 .
 */
public class ReadySettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_settings);
        Preference login = findPreference("basic");

    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()) {
            case "basic":
                break;
            default:
                break;
        }
        return false;
    }
}
