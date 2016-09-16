package com.simplepatternandroid.home;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simplepatternandroid.network.NetworkError;

public class HomePresenter {
    String TAG = HomePresenter.class.getSimpleName();
    HomeService homeService;
    HomeView homeView;

    public HomePresenter(HomeService homeService, HomeView homeView) {
        this.homeService = homeService;
        this.homeView = homeView;
    }

    public void getProvinsi() {
        homeService.getProvinsi(new HomeService.GetProvinsiCallback() {
            @Override
            public void onSuccess(ProvinsiResponse provinsiResponse) {
                Log.i(TAG, provinsiResponse.toString());
            }

            @Override
            public void onError(NetworkError networkError) {
                Log.i(TAG, networkError.toString());
            }
        });
    }

    public void getProvinsiReactive() {
        homeService.getProvinsiReactive(new HomeService.GetProvinsiReactiveCallback() {
            @Override
            public void onSuccess(ProvinsiResponse provinsiResponse) {
                Log.i(TAG, provinsiResponse.toString());
            }

            @Override
            public void onError(NetworkError networkError) {
                Log.i(TAG, networkError.toString());
            }
        });
    }

    public void getSampleHttps() {
        homeService.getSampleHttps(new HomeService.GetCallback<JsonObject>() {
            @Override
            public void onSuccess(JsonObject jsonObject) {
                Log.i(TAG, jsonObject.toString());
            }

            @Override
            public void onError(NetworkError networkError) {
                Log.e(TAG, networkError.toString());
            }
        });
    }
}
