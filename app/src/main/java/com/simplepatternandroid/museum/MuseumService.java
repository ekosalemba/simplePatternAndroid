package com.simplepatternandroid.museum;

import android.util.Log;

import com.simplepatternandroid.home.ProvinsiDetailResponse;
import com.simplepatternandroid.home.ProvinsiResponse;
import com.simplepatternandroid.network.NetworkError;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MuseumService {
    MuseumNetworkService museumNetworkService;

    public MuseumService(MuseumNetworkService museumNetworkService) {
        this.museumNetworkService = museumNetworkService;
    }

    public Subscription getMuseumByProvince(ProvinsiDetailResponse provinsiDetailResponse, final Callback<MuseumResponse> museumResponseCallback) {
        return museumNetworkService.getMuseumByProvince(provinsiDetailResponse.kodeWilayah.trim())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MuseumResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        museumResponseCallback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(MuseumResponse museumResponse) {
                        museumResponseCallback.onSuccess(museumResponse);
                    }
                });
    }

    public interface Callback<T> {
        void onSuccess(T callback);

        void onError(NetworkError networkError);
    }
}
