package com.app.katacaknun.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class DataKetegori {
    @SerializedName("kat_id")
    @Expose
    private String kat_id;
    @SerializedName("kategori")
    @Expose
    private String kategori;
    private int image;

    public DataKetegori(String kat_id, String kategori, int image) {
        this.kat_id = kat_id;
        this.kategori = kategori;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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
