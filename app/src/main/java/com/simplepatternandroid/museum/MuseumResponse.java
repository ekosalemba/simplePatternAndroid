package com.simplepatternandroid.museum;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MuseumResponse {
    @SerializedName("data")
    public List<MuseumDetailResponse> data = new ArrayList<>();

}
