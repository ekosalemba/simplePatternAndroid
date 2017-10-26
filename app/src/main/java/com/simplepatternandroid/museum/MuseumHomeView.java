package com.simplepatternandroid.museum;

import com.simplepatternandroid.network.NetworkError;

public interface MuseumHomeView {
    void onSuccessGetMuseumByProvince(MuseumResponse callback);

    void onErrorGetMuseumByProvince(NetworkError networkError);
}
