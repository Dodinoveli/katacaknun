package com.app.katacaknun.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMaster {
    @SerializedName("status")
    private String status;
    @SerializedName("pesan")
    private String pesan;
    List<Master_katrgory>list_master;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<Master_katrgory> getList_master() {
        return list_master;
    }

    public void setList_master(List<Master_katrgory> list_master) {
        this.list_master = list_master;
    }
}
