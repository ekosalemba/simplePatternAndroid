package com.simplepatternandroid.home;

import android.databinding.BaseObservable;

public class ProvinsiViewModel extends BaseObservable {
    private ProvinsiDetailResponse provinsiDetailResponse;

    public ProvinsiViewModel(ProvinsiDetailResponse provinsiDetailResponse) {
        this.provinsiDetailResponse = provinsiDetailResponse;
    }

    public String getNama() {
        if (provinsiDetailResponse.getNama() != null) {
            return provinsiDetailResponse.getNama();
        }
        return "";
    }
}
