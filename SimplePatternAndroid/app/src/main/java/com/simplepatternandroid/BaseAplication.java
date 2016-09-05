package com.simplepatternandroid;

import android.app.Application;

import com.simplepatternandroid.deps.AppModule;
import com.simplepatternandroid.deps.DaggerDeps;
import com.simplepatternandroid.deps.Deps;
import com.simplepatternandroid.deps.NetworkModule;

import java.io.File;

public class BaseAplication extends Application {
    private Deps deps;

    @Override
    public void onCreate() {
        super.onCreate();
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(cacheFile, "http://staging.server.com")).build();
    }

    public Deps getDeps() {
        return deps;
    }
}
