package com.simplepatternandroid;

import android.app.Application;

import com.simplepatternandroid.deps.AppModule;
import com.simplepatternandroid.deps.DaggerDeps;
import com.simplepatternandroid.deps.Deps;

public class BaseAplication extends Application {
    private Deps deps;

    @Override
    public void onCreate() {
        super.onCreate();
        deps = DaggerDeps.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public Deps getDeps() {
        return deps;
    }
}
