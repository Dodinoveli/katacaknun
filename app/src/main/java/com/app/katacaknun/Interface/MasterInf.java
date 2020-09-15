package com.app.katacaknun.Interface;

import com.app.katacaknun.model.GetMaster;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MasterInf {

    /*get master*/
    @GET("Master/api_kategory")
    Call<GetMaster>doGetMaster();
}
