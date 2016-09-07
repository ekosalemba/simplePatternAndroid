package com.simplepatternandroid.home;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

public interface HomeNetworkService {
    @GET("CWilayah/wilayahGET?mst_kode_wilayah=010000")
    Call<ProvinsiResponse> getProvinsi();

    @GET("CWilayah/wilayahGET?mst_kode_wilayah=010000")
    Observable<ProvinsiResponse> getProvinsiReactive();
}
