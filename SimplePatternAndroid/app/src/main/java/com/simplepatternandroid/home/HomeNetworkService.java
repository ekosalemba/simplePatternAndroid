package com.simplepatternandroid.home;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeNetworkService {
    @GET("CWilayah/wilayahGET?mst_kode_wilayah=010000")
    Call<ProvinsiResponse> getProvinsi();
}
