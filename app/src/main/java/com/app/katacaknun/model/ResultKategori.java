package com.app.katacaknun.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultKategori {
    @SerializedName("result")
    ArrayList<DataKetegori>result;

    public ArrayList<DataKetegori> getResult() {
        return result;
    }

    public void setResult(ArrayList<DataKetegori> result) {
        this.result = result;
    }
}
