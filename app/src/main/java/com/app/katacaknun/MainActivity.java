package com.app.katacaknun;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.katacaknun.endPoint.E_Kategori;
import com.app.katacaknun.RequestHandler.ApiServiceAll;
import com.app.katacaknun.adapter.AdapterMaster;
import com.app.katacaknun.endPoint.RecyclerViewClickListener;
import com.app.katacaknun.listeners.RecyclerTouchListener;
import com.app.katacaknun.model.M_Ketegori;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.clans.fab.FloatingActionButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    String KEY_ID = "KEY_ID";
    RecyclerView recyclerView;
    ArrayList<M_Ketegori>list ;
    AdapterMaster  adapter;
    ShimmerFrameLayout mShimmerViewContainer;
    //Membuat Variable ShareAction Provider
    private ShareActionProvider shareActionProvider;
    FloatingActionButton caknun,wallpaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getJson();
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_row_kat);
        adapter = new AdapterMaster(MainActivity.this,list);

        LinearLayoutManager manager      = new LinearLayoutManager(MainActivity.this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
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

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                String kat  = list.get(position).getKategori();
                String kat_id = list.get(position).getKat_id();
                //Toast.makeText(MainActivity.this,"id "+kat_id,Toast.LENGTH_LONG);
                Log.i("If Status", "kat id "+kat_id);
                Log.d("Kat","Pesan "+kat);
                Intent intent = new Intent(MainActivity.this,Kategory.class);
                intent.putExtra(KEY_ID,kat_id);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        }));

    }

    private void getJson(){
        list = new ArrayList<>();
        Retrofit retrofit = ApiServiceAll.getRetrofitService();
        E_Kategori endPoinKategori = retrofit.create(E_Kategori.class);
        Call<ResponseBody> call = endPoinKategori.doGetMaster();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){

                    if (response.code()==200){
                        try {
                            JSONObject object = new JSONObject(response.body().string().toString());
                            String result     = object.getString("result");
                            String pesan      = object.getString("pesan");
                            if (result.equals("true")){
                                Log.i("If Status", "berhasil");
                                JSONArray jsonArray = new JSONArray(pesan);
                                for (int x=0; x<jsonArray.length(); x++){
                                    JSONObject object1=jsonArray.getJSONObject(x);
                                    String kat_id = object1.getString("kat_id");
                                    String kategori = object1.getString("kategori");
                                    String tgl = object1.getString("tgl");
                                    String subStr = tgl.substring(0,10);
                                    list.add(new M_Ketegori(kat_id,kategori,0,subStr));
                                }
                                adapter.notifyDataSetChanged();
                            }
                        } catch (IOException | JSONException e) {
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
                Log.i("If Status", "gagal ");
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
