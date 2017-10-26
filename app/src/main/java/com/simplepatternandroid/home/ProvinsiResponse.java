package com.simplepatternandroid.home;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProvinsiResponse {
    @SerializedName("data")
    List<ProvinsiDetailResponse> data = new ArrayList<ProvinsiDetailResponse>();

    public ProvinsiResponse(List<ProvinsiDetailResponse> data) {
        this.data = data;
    }

    public List<ProvinsiDetailResponse> getData() {
        return data;
    }

    public void setData(List<ProvinsiDetailResponse> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProvinsiResponse{" +
                "data=" + data +
                '}';
    }
}
