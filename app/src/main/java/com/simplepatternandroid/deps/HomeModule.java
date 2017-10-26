package com.simplepatternandroid.deps;

import com.simplepatternandroid.home.HomeNetworkService;
import com.simplepatternandroid.home.HomeService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class HomeModule {

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public HomeNetworkService providesHomeNetworkService(Retrofit retrofit) {
        return retrofit.create(HomeNetworkService.class);
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public HomeService providesHomeService(HomeNetworkService homeNetworkService) {
        return new HomeService(homeNetworkService);
    }
}
