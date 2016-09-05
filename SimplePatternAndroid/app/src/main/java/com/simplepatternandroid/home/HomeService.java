package com.simplepatternandroid.home;

public class HomeService {
    HomeNetworkService homeNetworkService;

    public HomeService(HomeNetworkService homeNetworkService) {
        this.homeNetworkService = homeNetworkService;
    }

    public String hello() {
        return "Hello dagger";
    }
}
