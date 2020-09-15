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

import com.app.katacaknun.Interface.MasterInf;
import com.app.katacaknun.RequestHandler.ApiClient;
import com.app.katacaknun.adapter.AdapterMaster;
import com.app.katacaknun.model.FirstKata;
import com.app.katacaknun.item.FirstRowItem;
import com.app.katacaknun.model.GetMaster;
import com.app.katacaknun.model.Master_katrgory;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.clans.fab.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    MasterInf masterInf;

    private RecyclerView recyclerView;
    private AdapterMaster adapter;
    private ArrayList<Master_katrgory> firstKataList;

    ShimmerFrameLayout mShimmerViewContainer;
    //Membuat Variable ShareAction Provider
    private ShareActionProvider shareActionProvider;
    FloatingActionButton caknun,wallpaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        masterInf = ApiClient.getClient().create(MasterInf.class);

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        render();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new AdapterMaster(MainActivity.this,firstKataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

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
        /*firstKataList = new ArrayList<>();
        for (int i = 0; i< FirstRowItem.judul.length; i++){
            firstKataList.add(new FirstKata(FirstRowItem.judul[i], FirstRowItem.image[i]));
        }

        if (!firstKataList.isEmpty()){
            mShimmerViewContainer.stopShimmerAnimation();
            mShimmerViewContainer.setVisibility(View.GONE);
        }*/


        Call<GetMaster>callmstr = masterInf.doGetMaster();
        callmstr.enqueue(new Callback<GetMaster>() {
            @Override
            public void onResponse(Call<GetMaster> call, Response<GetMaster> response) {
                List<Master_katrgory> KontakList = response.body().getList_master();
                Log.d("Retrofit Get", "Jumlah data Kontak: " +
                        String.valueOf(KontakList.size()));
                if (response.isSuccessful()) {
                    adapter.notifyDataSetChanged();
                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    recyclerView.smoothScrollToPosition(0);
                }
            }

            @Override
            public void onFailure(Call<GetMaster> call, Throwable t) {
                if (!call.isCanceled()){
                    t.printStackTrace();
                }
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