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

import com.app.katacaknun.ApiService.ApiServiceKategori;
import com.app.katacaknun.RequestHandler.ApiClient;
import com.app.katacaknun.adapter.AdapterMaster;
import com.app.katacaknun.model.DataKetegori;
import com.app.katacaknun.model.ResultKategori;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.clans.fab.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterMaster  adapter;
    RecyclerView.LayoutManager manager;

    List<DataKetegori>list = new ArrayList<DataKetegori>();

    ShimmerFrameLayout mShimmerViewContainer;
    //Membuat Variable ShareAction Provider
    private ShareActionProvider shareActionProvider;
    FloatingActionButton caknun,wallpaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        manager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
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
        ApiServiceKategori api = ApiClient.getRetrofit().create(ApiServiceKategori.class);
        Call<ResultKategori>result         = api.doGetMaster();
        result.enqueue(new Callback<ResultKategori>() {
            @Override
            public void onResponse(Call<ResultKategori> call, Response<ResultKategori> response) {
                Log.d(""," log data"+response.body().getResult());
                list    = response.body().getResult();
                if (response.isSuccessful()){
                    if (response.code()==200){
                        adapter = new AdapterMaster(MainActivity.this,list);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                    mShimmerViewContainer.startShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResultKategori> call, Throwable t) {
                t.printStackTrace();
                Log.d("RETRO", "FAILED : respon gagal");
                mShimmerViewContainer.stopShimmerAnimation();
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