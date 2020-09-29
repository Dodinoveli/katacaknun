package com.app.katacaknun;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.katacaknun.RequestHandler.ApiServiceAll;
import com.app.katacaknun.adapter.AdapterKategory;
import com.app.katacaknun.endPoint.E_Detail;
import com.app.katacaknun.endPoint.RecyclerViewClickListener;
import com.app.katacaknun.listeners.RecyclerTouchListener;
import com.app.katacaknun.model.M_kata;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;

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
    String KEY_KATA_ID="KATA_ID";
    String KEY_KAT_ID="KAT_ID";
    ArrayList<M_kata> detail_list;
    RecyclerView recyclerView;
    AdapterKategory adapterKategory;
    ShimmerFrameLayout mShimmerViewContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategory);
        Bundle bundle = getIntent().getExtras();
        String  data = bundle.getString(KEY_ID);

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
                String kat_id  = detail_list.get(position).getKat_id();
                Log.i("Kata", "kata_id = "+kata_id);
                Log.i("Kata", "kat  ID = "+kat_id);
                Intent intent = new Intent(Kategory.this,Detail.class);
                intent.putExtra(KEY_KATA_ID,kata_id);
                intent.putExtra(KEY_KAT_ID,kat_id);
                finish();
                startActivity(intent);
            }
        }));

        ImageView imageView = (ImageView)findViewById(R.id.img_act_kat);
        Glide.with(this).load(R.drawable.loo).circleCrop().into(imageView);

        mShimmerViewContainer = (ShimmerFrameLayout)findViewById(R.id.shimmer_view_kat);
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
                    if (response.code()==200){
                        try {
                            JSONObject object = new JSONObject(response.body().string().toString());
                            String status = object.getString("status");
                            String data   = object.getString("data");
                            if (status.equals("true")){
                                JSONArray jsonArray = new JSONArray(data);
                                for (int item=0; item<jsonArray.length(); item++){
                                    JSONObject object1  = jsonArray.getJSONObject(item);
                                    String kat_id       = object1.getString("kat_id");
                                    String judul        = object1.getString("judul");
                                    String kata_id      = object1.getString("kata_id");
                                    Log.d("result :  "," Result :"+judul);
                                    detail_list.add(new M_kata(kat_id,judul,kata_id,""));
                                }
                                adapterKategory.notifyDataSetChanged();
                            }
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                    mShimmerViewContainer.startShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    mShimmerViewContainer.setDuration(5000);
                    mShimmerViewContainer.setRepeatMode(ObjectAnimator.REVERSE);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mShimmerViewContainer.stopShimmerAnimation();
                Log.d("data","gagal");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();

    }
}