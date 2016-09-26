package com.simplepatternandroid.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProvinsiDetailResponse implements Parcelable {
    @SerializedName("kode_wilayah")
    public String kodeWilayah;
    @SerializedName("nama")
    public String nama;
    @SerializedName("mst_kode_wilayah")
    public String mstKodeWilayah;

    public ProvinsiDetailResponse(String kodeWilayah, String nama, String mstKodeWilayah) {
        this.kodeWilayah = kodeWilayah;
        this.nama = nama;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kodeWilayah);
        dest.writeString(this.nama);
        dest.writeString(this.mstKodeWilayah);
    }

    protected ProvinsiDetailResponse(Parcel in) {
        this.kodeWilayah = in.readString();
        this.nama = in.readString();
        this.mstKodeWilayah = in.readString();
    }

    public static final Parcelable.Creator<ProvinsiDetailResponse> CREATOR = new Parcelable.Creator<ProvinsiDetailResponse>() {
        @Override
        public ProvinsiDetailResponse createFromParcel(Parcel source) {
            return new ProvinsiDetailResponse(source);
        }

        @Override
        public ProvinsiDetailResponse[] newArray(int size) {
            return new ProvinsiDetailResponse[size];
        }
    };
}
