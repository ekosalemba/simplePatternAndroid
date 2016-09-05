package com.simplepatternandroid.home;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeNetworkService {
    @GET("/get/user")
    Call<UserResponse> getUser();
}
