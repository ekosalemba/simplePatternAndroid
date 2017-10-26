package com.simplepatternandroid.deps;

import com.simplepatternandroid.home.HomeNetworkService;
import com.simplepatternandroid.home.HomeService;
import com.simplepatternandroid.museum.MuseumNetworkService;
import com.simplepatternandroid.museum.MuseumService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MuseumModule {

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public MuseumNetworkService providesMuseumNetworkService(Retrofit retrofit) {
        return retrofit.create(MuseumNetworkService.class);
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public MuseumService providesMuseumService(MuseumNetworkService museumNetworkService) {
        return new MuseumService(museumNetworkService);
    }
}
