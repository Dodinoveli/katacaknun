package com.app.katacaknun.model;

public class Wallpp {
    private String judul_walpp;
    private  int imageWalpp;

    public Wallpp(String judul_walpp, int imageWalpp) {
        this.judul_walpp = judul_walpp;
        this.imageWalpp = imageWalpp;
    }

    public String getJudul_walpp() {
        return judul_walpp;
    }

    public void setJudul_walpp(String judul_walpp) {
        this.judul_walpp = judul_walpp;
    }

    public int getImageWalpp() {
        return imageWalpp;
    }

    public void setImageWalpp(int imageWalpp) {
        this.imageWalpp = imageWalpp;
    }

}
