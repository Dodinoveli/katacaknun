package com.app.katacaknun.endPoint;
import com.app.katacaknun.RequestHandler.Config;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface E_Detail {

@GET(Config.API_SUB_KAT)
    Call<ResponseBody>getDetail(
            @Query("kat_id") String id);
}
