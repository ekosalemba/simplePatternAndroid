package com.simplepatternandroid.museum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MuseumDetailResponse {
    @SerializedName("museum_id")
    @Expose
    public String museumId;
    @SerializedName("kode_pengelolaan")
    @Expose
    public String kodePengelolaan;
    @SerializedName("nama")
    @Expose
    public String nama;
    @SerializedName("sdm")
    @Expose
    public String sdm;
    @SerializedName("alamat_jalan")
    @Expose
    public String alamatJalan;
    @SerializedName("desa_kelurahan")
    @Expose
    public String desaKelurahan;
    @SerializedName("kecamatan")
    @Expose
    public String kecamatan;
    @SerializedName("kabupaten_kota")
    @Expose
    public String kabupatenKota;
    @SerializedName("propinsi")
    @Expose
    public String propinsi;
    @SerializedName("lintang")
    @Expose
    public String lintang;
    @SerializedName("bujur")
    @Expose
    public String bujur;
    @SerializedName("koleksi")
    @Expose
    public String koleksi;
    @SerializedName("sumber_dana")
    @Expose
    public String sumberDana;
    @SerializedName("pengelola")
    @Expose
    public String pengelola;
    @SerializedName("tipe")
    @Expose
    public String tipe;
    @SerializedName("standar")
    @Expose
    public String standar;
    @SerializedName("tahun_berdiri")
    @Expose
    public String tahunBerdiri;
    @SerializedName("bangunan")
    @Expose
    public String bangunan;
    @SerializedName("luas_tanah")
    @Expose
    public String luasTanah;
    @SerializedName("status_kepemilikan")
    @Expose
    public String statusKepemilikan;
}
