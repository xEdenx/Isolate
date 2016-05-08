package com.tneciv.zhihudaily.about;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.paolorotolo.appintro.AppIntro;
import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.home.view.MainActivity;

public class AboutActivity extends AppIntro {

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        addSlide(SampleSlide.newInstance(R.layout.appintro_first));
        addSlide(SampleSlide.newInstance(R.layout.appintro_second));
        addSlide(SampleSlide.newInstance(R.layout.appintro_third));

        showStatusBar(false);
        setProgressIndicator();
        setZoomAnimation();
        showSkipButton(true);

        setSkipText("忽略");
        setDoneText("开始");
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    @Override
    public void onSlideChanged() {

    }

    private void loadMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        preferences.edit().putBoolean("showIntro", true).apply();

        finish();
    }
}
