package com.simplepatternandroid.home;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simplepatternandroid.network.NetworkError;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
                    Log.i(TAG, response.code() + "");
                    Log.i(TAG, response.message() + "");
                    if (response.body() != null) {
                        getProvinsiCallback.onSuccess(response.body());
                    } else {
                        Log.e(TAG, response.toString());
                    }
                }

                @Override
                public void onFailure(Call<ProvinsiResponse> call, Throwable t) {
                    Log.e(TAG, t.toString());
                    getProvinsiCallback.onError(new NetworkError(t));
                }
            });
        } catch (Error error) {
            error.printStackTrace();
        }
    }

    public Subscription getProvinsiReactive(final GetProvinsiReactiveCallback getProvinsiReactiveCallback) {
        return homeNetworkService.getProvinsiReactive()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProvinsiResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.toString());
                        getProvinsiReactiveCallback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(ProvinsiResponse provinsiResponse) {
                        Log.i(TAG, provinsiResponse.toString());
                        getProvinsiReactiveCallback.onSuccess(provinsiResponse);
                    }
                });

    }

    public Subscription getSampleHttps(final GetCallback<JsonObject> getCallback) {
        return homeNetworkService.getSampleHttps()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.toString());
                        getCallback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        Log.i(TAG, jsonObject.toString());
                        getCallback.onSuccess(jsonObject);
                    }
                });

    }

    public interface GetCallback<T> {

        void onSuccess(T callback);

        void onError(NetworkError networkError);
    }

    public interface GetProvinsiCallback {

        void onSuccess(ProvinsiResponse provinsiResponse);

        void onError(NetworkError networkError);
    }

    public interface GetProvinsiReactiveCallback {

        void onSuccess(ProvinsiResponse response);

        void onError(NetworkError networkError);
    }

    public interface GetSampleCallback {

        void onSuccess(JsonArray response);

        void onError(NetworkError networkError);
    }
}
