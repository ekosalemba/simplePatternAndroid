package com.simplepatternandroid.museum;


import com.simplepatternandroid.home.ProvinsiResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MuseumNetworkService {

    @GET("CcariMuseum/searchGET")
    Observable<MuseumResponse> getMuseumByProvince(@Query("kode_prop") String kodeWilayah);

}
