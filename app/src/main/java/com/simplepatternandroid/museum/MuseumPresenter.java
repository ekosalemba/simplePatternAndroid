package com.simplepatternandroid.museum;

import com.simplepatternandroid.home.ProvinsiDetailResponse;
import com.simplepatternandroid.network.NetworkError;

public class MuseumPresenter {
    MuseumService museumService;
    MuseumHomeView museumHomeView;

    public MuseumPresenter(MuseumService museumService, MuseumHomeView museumHomeView) {
        this.museumService = museumService;
        this.museumHomeView = museumHomeView;
    }

    public void getMuseumByProvince(ProvinsiDetailResponse provinsiDetailResponse) {
        museumService.getMuseumByProvince(provinsiDetailResponse, new MuseumService.Callback<MuseumResponse>() {
            @Override
            public void onSuccess(MuseumResponse callback) {
                museumHomeView.onSuccessGetMuseumByProvince(callback);
            }

            @Override
            public void onError(NetworkError networkError) {
                museumHomeView.onErrorGetMuseumByProvince(networkError);
            }
        });
    }
}
