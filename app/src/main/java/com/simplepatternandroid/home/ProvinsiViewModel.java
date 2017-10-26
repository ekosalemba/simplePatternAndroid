package com.simplepatternandroid.home;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class ProvinsiViewModel extends BaseObservable {
    private ProvinsiDetailResponse provinsiDetailResponse;

    public ProvinsiViewModel(ProvinsiDetailResponse provinsiDetailResponse) {
        this.provinsiDetailResponse = provinsiDetailResponse;
    }

    @Bindable
    public String getNama() {
        if (provinsiDetailResponse.nama != null) {
            return provinsiDetailResponse.nama;
        }
        return "";
    }
}
