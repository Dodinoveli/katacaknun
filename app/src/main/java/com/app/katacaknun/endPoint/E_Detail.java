package com.app.katacaknun.endPoint;
import com.app.katacaknun.RequestHandler.Config;
import com.app.katacaknun.model.M_detail;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface E_Detail {

@GET(Config.API_DETAIL)
    Call<ArrayList<M_detail>>getDetail(
            @Query("kat_id") String id);
}
