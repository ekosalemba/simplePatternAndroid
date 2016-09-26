package com.simplepatternandroid;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {
    protected ActionBar action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBaseActionBar();
    }

    public void setBaseActionBar() {
        if (getSupportActionBar() != null)
            action = getSupportActionBar();

        if (action != null) {
//            View view = LayoutInflater.from(this).inflate(R.layout.title_bar, null);

//            action.setDisplayOptions(ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_SHOW_HOME |
//                    ActionBar.DISPLAY_SHOW_CUSTOM);
//            action.setIcon(R.drawable.ic_actionbar);
            action.setDisplayHomeAsUpEnabled(true);
            action.setDisplayShowHomeEnabled(true);
            action.setHomeButtonEnabled(true);
//            action.setCustomView(view);


            //setStatusBarColor(findViewById(R.id.statusBarBackground), getResources().getColor(R
            // .color.gojek_actionbar));

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                setTranslucentStatus(true);
//            }

//            SystemBarTintManager tintManager = new SystemBarTintManager(this);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(R.color.gojek_actionbar);
        }
    }

    public void setDisplayShowHome(boolean status) {
        if (action != null) {
            action.setDisplayHomeAsUpEnabled(status);
            action.setDisplayShowHomeEnabled(status);
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            hideKeyboard();
            onBackPressed();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context
                .INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }
}
