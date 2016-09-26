package com.simplepatternandroid.home;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

public interface HomeNetworkService {
    @GET("CWilayah/wilayahGET?mst_kode_wilayah=010000")
    Call<ProvinsiResponse> getProvinsi();

    @GET("CWilayah/wilayahGET")
    Observable<ProvinsiResponse> getProvinsiReactive();

    @GET("UserInformationRecoveryService/getUserIdentitySupportedClaims?dialect=http%3A%2F%2Fwso2.org%2Fclaims\n")
    Observable<JsonObject> getSampleHttps();
}
