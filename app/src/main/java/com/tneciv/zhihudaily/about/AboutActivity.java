package com.tneciv.zhihudaily.about;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.home.view.MainActivity;

public class AboutActivity extends AppIntro {

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        addSlide(SampleSlide.newInstance(R.layout.appintro_first));
        addSlide(SampleSlide.newInstance(R.layout.appintro_second));
        addSlide(SampleSlide.newInstance(R.layout.appintro_third));

        setProgressIndicator();
        setZoomAnimation();
        showSkipButton(true);
        showDoneButton(true);

        setSkipText("忽略");
        setDoneText("开始");
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    private void loadMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        SharedPreferences preferences = getSharedPreferences("config", MODE_PRIVATE);
        preferences.edit().putBoolean("isIntroed", true).commit();

        finish();
    }
}
