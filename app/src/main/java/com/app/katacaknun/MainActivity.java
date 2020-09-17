package com.app.katacaknun;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.katacaknun.Interface.ApiServiceKategori;
import com.app.katacaknun.RequestHandler.ApiClient;
import com.app.katacaknun.adapter.AdapterMaster;
import com.app.katacaknun.model.DataKetegori;
import com.app.katacaknun.model.ResultKategori;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.clans.fab.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private ArrayList<DataKetegori> listMaster;

    //ShimmerFrameLayout mShimmerViewContainer;
    //Membuat Variable ShareAction Provider
    private ShareActionProvider shareActionProvider;
    FloatingActionButton caknun,wallpaper;
    public static final String API_BASE_URL = "http://localhost/caknun/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

        render();
        shareActionProvider = new ShareActionProvider(MainActivity.this);
        caknun    = (FloatingActionButton)findViewById(R.id.ck);
        caknun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Kategory.class);
                startActivity(intent);
                finish();
            }
        });
        FloatingActionButton share = (FloatingActionButton)findViewById(R.id.walpper);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(MainActivity.this,Wallpaper.class);
              startActivity(intent);
              finish();
            }
        });


    }

    private void render(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServiceKategori serviceKategori = retrofit.create(ApiServiceKategori.class);

        Call<ResultKategori>result         = serviceKategori.doGetMaster();
        result.enqueue(new Callback<ResultKategori>() {
            @Override
            public void onResponse(Call<ResultKategori> call, Response<ResultKategori> response) {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                AdapterMaster  adapter = new AdapterMaster(MainActivity.this,response.body().getResult());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(layoutManager);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                Log.d(""," log data"+response.body().getResult());
            }

            @Override
            public void onFailure(Call<ResultKategori> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
       // mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

    private void setShare(){
        ApplicationInfo appInfo = getApplicationContext().getApplicationInfo();
        String apkPath = appInfo.sourceDir;
        Intent Share = new Intent();
        Share.setAction(Intent.ACTION_SEND);
        Share.setType("application/vnd.android.package-archive");
        Share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPath)));
        shareActionProvider.setShareIntent(Share);
    }

}


        /*firstKataList = new ArrayList<>();
        for (int i = 0; i< FirstRowItem.judul.length; i++){
            firstKataList.add(new FirstKata(FirstRowItem.judul[i], FirstRowItem.image[i]));
        }

        if (!firstKataList.isEmpty()){
            mShimmerViewContainer.stopShimmerAnimation();
            mShimmerViewContainer.setVisibility(View.GONE);
        }



        */