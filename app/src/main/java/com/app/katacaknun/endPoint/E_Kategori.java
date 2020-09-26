package com.app.katacaknun.endPoint;
import com.app.katacaknun.RequestHandler.Config;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface E_Kategori {
    @GET(Config.API_KATEGORI)
    Call<ResponseBody>doGetMaster();
}
