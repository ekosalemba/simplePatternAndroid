package com.simplepatternandroid.deps;

import com.simplepatternandroid.home.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        HomeModule.class
})

public interface Deps {
    void inject(HomeActivity mainActivity);
}
