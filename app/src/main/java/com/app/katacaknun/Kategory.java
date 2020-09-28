package com.app.katacaknun;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.katacaknun.RequestHandler.ApiServiceAll;
import com.app.katacaknun.adapter.AdapterKategory;
import com.app.katacaknun.endPoint.E_Detail;
import com.app.katacaknun.endPoint.RecyclerViewClickListener;
import com.app.katacaknun.listeners.RecyclerTouchListener;
import com.app.katacaknun.model.M_kata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Kategory extends AppCompatActivity {
    String KEY_ID="KEY_ID";
    String data="";
    ArrayList<M_kata> detail_list;
    RecyclerView recyclerView;
    AdapterKategory adapterKategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategory);
        Bundle bundle = getIntent().getExtras();
        data = bundle.getString(KEY_ID);
        Log.d("data","Pesan"+data);
        getKategori(data);
        recyclerView = (RecyclerView)findViewById(R.id.rec_kata);
        adapterKategory = new AdapterKategory(Kategory.this,detail_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Kategory.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterKategory);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                String kata_id = detail_list.get(position).getKata_id();
                Log.i("Kata", "kata_id = "+kata_id);
               /* Intent intent = new Intent(Kategory.this,Detail.class);
                intent.putExtra(KEY_ID,kat_id);
                finish();
                startActivity(intent);*/
            }
        }));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Kategory.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void  getKategori(String data_id){
        detail_list = new ArrayList<>();
        Retrofit retrofit = ApiServiceAll.getRetrofitService();
        E_Detail eDetail = retrofit.create(E_Detail.class);
        Call<ResponseBody> mDetailCall = eDetail.getDetail(data_id);
        mDetailCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()){
                    try {
                        JSONObject object = new JSONObject(response.body().string().toString());
                        String status = object.getString("status");
                        String data   = object.getString("data");
                        if (status.equals("true")){
                            JSONArray jsonArray = new JSONArray(data);
                            for (int item=0; item<jsonArray.length(); item++){
                                JSONObject object1 = jsonArray.getJSONObject(item);
                                String kat_id  = object1.getString("kat_id");
                                String judul = object1.getString("judul");
                                String kata_id = object1.getString("kata_id");
                                Log.d("result :  "," Result :"+judul);
                               detail_list.add(new M_kata(kat_id,judul,kata_id));
                            }
                            adapterKategory.notifyDataSetChanged();
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("data","gagal");
            }
        });
    }

}