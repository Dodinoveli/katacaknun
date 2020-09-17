package com.app.katacaknun.model;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class ResultKategori {
    @SerializedName("result")
    List<DataKetegori> result;

    public List<DataKetegori> getResult() {
        return result;
    }

    public void setResult(List<DataKetegori> result) {
        this.result = result;
    }
}
