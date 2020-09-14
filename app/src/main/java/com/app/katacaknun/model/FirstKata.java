package com.app.katacaknun.model;

public class FirstKata {
    private String judul;
    private int image;

    public FirstKata(String judul, int image) {
        this.judul = judul;
        this.image = image;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
