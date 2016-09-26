package com.simplepatternandroid.home;

import com.simplepatternandroid.network.NetworkError;

public interface HomeView {

    public void showLog(String log);

    public void setLoading(boolean status);

    void onSuccessGetProvinsi(ProvinsiResponse provinsiResponse);

    void onErrorGetProvinsi(NetworkError networkError);
}
