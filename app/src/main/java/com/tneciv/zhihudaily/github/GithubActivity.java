package com.tneciv.zhihudaily.github;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.larswerkman.licenseview.LicenseView;
import com.tneciv.zhihudaily.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GithubActivity extends AppCompatActivity {
    @Bind(R.id.licenseView)
    LicenseView licenseView;
    @Bind(R.id.toolbarGit)
    Toolbar toolbarGit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);
        ButterKnife.bind(this);
        setSupportActionBar(toolbarGit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        try {
            licenseView.setLicenses(R.xml.license);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
