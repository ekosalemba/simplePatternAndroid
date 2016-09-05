package com.simplepatternandroid.home;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeService {
    private String TAG = HomeService.class.getSimpleName();
    HomeNetworkService homeNetworkService;

    public HomeService(HomeNetworkService homeNetworkService) {
        this.homeNetworkService = homeNetworkService;
    }

    public String hello() {
        return "Hello dagger";
    }

    public void getProvinsi(final GetProvinsiCallback getProvinsiCallback) {
        Call<ProvinsiResponse> call = homeNetworkService.getProvinsi();
        Log.i(TAG, call.toString());
        try {
            call.enqueue(new Callback<ProvinsiResponse>() {
                @Override
                public void onResponse(Call<ProvinsiResponse> call, Response<ProvinsiResponse> response) {
                    getProvinsiCallback.onSuccess(response.body());
                }

                @Override
                public void onFailure(Call<ProvinsiResponse> call, Throwable t) {
                    Log.e(TAG, t.toString());
                    getProvinsiCallback.onError(t.getMessage());
                }
            });
        } catch (Error error) {
            error.printStackTrace();
        }
    }

    public interface GetProvinsiCallback {

        void onSuccess(ProvinsiResponse provinsiResponse);

        void onError(String error);
    }
}
