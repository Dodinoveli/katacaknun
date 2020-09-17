package com.app.katacaknun.Interface;
import com.app.katacaknun.model.ResultKategori;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceKategori {
    @GET("api/Master/api_kategory")
    Call<ResultKategori>doGetMaster();
}
