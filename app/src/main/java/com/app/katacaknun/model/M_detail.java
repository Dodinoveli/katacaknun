package com.app.katacaknun.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class M_detail  {
 @SerializedName("kat_id")
 @Expose
 private String   kat_id;
 @SerializedName("judul")
 @Expose
 private String   judul;

    public M_detail(String kat_id, String judul) {
        this.kat_id = kat_id;
        this.judul = judul;
    }

    public String getKat_id() {
        return kat_id;
    }

    public void setKat_id(String kat_id) {
        this.kat_id = kat_id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
}
