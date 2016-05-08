package com.tneciv.zhihudaily.github;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;

import com.tneciv.zhihudaily.base.BaseListFragment;

import net.yslibrary.licenseadapter.LicenseAdapter;
import net.yslibrary.licenseadapter.LicenseEntry;
import net.yslibrary.licenseadapter.Licenses;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GitHubFragment extends BaseListFragment {

    private List<LicenseEntry> licenses = new ArrayList<>();

    @Override
    public void init() {
        swipeRefresh.setEnabled(false);
        addLicense(licenses);
    }

    @Override
    public void setRecyclerLayout() {
        loadLicense(licenses);
    }

    @Override
    public void requestUrl() {

    }

    private void loadLicense(List<LicenseEntry> licenses) {
        LicenseAdapter adapter = new LicenseAdapter(licenses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        Licenses.load(licenses);
    }

    private void addLicense(List<LicenseEntry> licenses) {
        // library that is not hosted on GitHub
        licenses.add(Licenses.noContent("Android SDK", "Google Inc.",
                "https://developer.android.com/sdk/terms.html"));

        // library that is hosted on GitHub, and license file is provided as "LICENSE"
        licenses.add(Licenses.fromGitHub("google/gson", Licenses.FILE_NO_EXTENSION));
        licenses.add(Licenses.fromGitHub("greenrobot/EventBus", Licenses.FILE_NO_EXTENSION));
        licenses.add(Licenses.fromGitHub("PaoloRotolo/AppIntro", Licenses.FILE_NO_EXTENSION));
        licenses.add(Licenses.fromGitHub("yshrsmz/LicenseAdapter", Licenses.FILE_NO_EXTENSION));
        licenses.add(Licenses.fromGitHub("florent37/PicassoPalette", Licenses.FILE_NO_EXTENSION));
        licenses.add(Licenses.fromGitHub("facebook/stetho", Licenses.NAME_BSD,
                Licenses.FILE_NO_EXTENSION));

        // library that is hosted on GitHub, and "LICENSE.txt" is provided
        licenses.add(Licenses.fromGitHub("JakeWharton/butterknife"));
        licenses.add(Licenses.fromGitHub("JakeWharton/DiskLruCache"));
        licenses.add(Licenses.fromGitHub("square/leakcanary"));
        licenses.add(Licenses.fromGitHub("square/okhttp"));
        licenses.add(Licenses.fromGitHub("square/picasso"));

        // library that is hosted on GitHub, but does not provide license text
        // licenses.add(Licenses.fromGitHub("gabrielemariotti/changeloglib", Licenses.LICENSE_APACHE_V2));
    }
}
