package com.app.katacaknun.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Master_katrgory {

    @SerializedName("kat_id")
    @Expose
    private String kat_id;
    @SerializedName("kategori")
    @Expose
    private String kategori;

    public Master_katrgory(String kat_id, String kategori) {
        this.kat_id = kat_id;
        this.kategori = kategori;
    }

    public String getKat_id() {
        return kat_id;
    }

    public void setKat_id(String kat_id) {
        this.kat_id = kat_id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
