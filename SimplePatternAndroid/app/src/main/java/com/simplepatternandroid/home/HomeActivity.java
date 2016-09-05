package com.simplepatternandroid.home;

import android.os.Bundle;
import android.util.Log;

import com.simplepatternandroid.BaseActivity;
import com.simplepatternandroid.BaseAplication;
import com.simplepatternandroid.R;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity {
    private String TAG = HomeActivity.class.getSimpleName();

    @Inject
    public HomeService homeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseAplication) getApplication()).getDeps().inject(this);
        setContentView(R.layout.activity_home);
        Log.i(TAG, homeService.hello());
    }
}
