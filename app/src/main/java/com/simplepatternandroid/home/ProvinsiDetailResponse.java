package com.simplepatternandroid.home;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProvinsiDetailResponse {
    @SerializedName("kode_wilayah")
    private String kodeWilayah;
    @SerializedName("nama")
    private String nama;
    @SerializedName("mst_kode_wilayah")
    private String mstKodeWilayah;

    public ProvinsiDetailResponse() {
    }

    public ProvinsiDetailResponse(String kodeWilayah, String nama, String mstKodeWilayah) {
        this.kodeWilayah = kodeWilayah;
        this.nama = nama;
        this.mstKodeWilayah = mstKodeWilayah;
    }

    public String getKodeWilayah() {
        return kodeWilayah;
    }

    public void setKodeWilayah(String kodeWilayah) {
        this.kodeWilayah = kodeWilayah;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getMstKodeWilayah() {
        return mstKodeWilayah;
    }

    public void setMstKodeWilayah(String mstKodeWilayah) {
        this.mstKodeWilayah = mstKodeWilayah;
    }

    @Override
    public String toString() {
        return "ProvinsiDetailResponse{" +
                "kodeWilayah='" + kodeWilayah + '\'' +
                ", nama='" + nama + '\'' +
                ", mstKodeWilayah='" + mstKodeWilayah + '\'' +
                '}';
    }
}
