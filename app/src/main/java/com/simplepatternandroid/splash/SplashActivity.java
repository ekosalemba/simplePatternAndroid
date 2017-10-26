package com.simplepatternandroid.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.simplepatternandroid.BaseActivity;
import com.simplepatternandroid.R;
import com.simplepatternandroid.home.HomeActivity;

public class SplashActivity extends BaseActivity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(this, 1500);
    }

    @Override
    public void run() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
