package com.simplepatternandroid.deps;

import com.simplepatternandroid.home.HomeActivity;
import com.simplepatternandroid.museum.MuseumHomeActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        HomeModule.class,
        MuseumModule.class
})

public interface Deps {

    void inject(HomeActivity mainActivity);

    void inject(MuseumHomeActivity museumHomeActivity);
}
