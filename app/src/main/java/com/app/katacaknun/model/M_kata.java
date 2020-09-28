package com.app.katacaknun.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class M_kata {
 @SerializedName("kat_id")
 @Expose
 private String   kat_id;
 @SerializedName("judul")
 @Expose
 private String   judul;
 @SerializedName("kata_id")
 @Expose
 private String kata_id;


    public M_kata(String kat_id, String judul, String kata_id) {
        this.kat_id = kat_id;
        this.judul = judul;
        this.kata_id = kata_id;
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

    public String getKata_id() {
        return kata_id;
    }

    public void setKata_id(String kata_id) {
        this.kata_id = kata_id;
    }
}
