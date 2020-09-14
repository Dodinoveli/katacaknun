package com.app.katacaknun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.app.katacaknun.adapter.WallpaperAdapter;
import com.app.katacaknun.item.SecondRowItem;
import com.app.katacaknun.model.Wallpp;
import java.util.ArrayList;

public class Wallpaper extends AppCompatActivity {
    RecyclerView recyclerView;
    WallpaperAdapter adapter;
    ArrayList<Wallpp>Wallp_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        render();
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_wallp);
        adapter = new WallpaperAdapter(Wallp_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Wallpaper.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void render(){
        Wallp_list = new ArrayList<>();
        for (int x=0; x< SecondRowItem.judul_walpp.length; x++){
            Wallp_list.add(new Wallpp(SecondRowItem.judul_walpp[x],SecondRowItem.image[x]));
        }
    }
}