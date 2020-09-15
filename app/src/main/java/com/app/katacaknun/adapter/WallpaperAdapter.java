package com.app.katacaknun.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.katacaknun.R;
import com.app.katacaknun.model.Wallpp;
import java.util.ArrayList;


public class WallpaperAdapter  extends RecyclerView.Adapter<WallpaperAdapter.HolderWallpaper>{
    private ArrayList<Wallpp>dataWallpp = new ArrayList<>();

    public WallpaperAdapter(ArrayList<Wallpp>dataWallpp) {
        this.dataWallpp=dataWallpp;
    }

    @Override
    public HolderWallpaper onCreateViewHolder(ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.row_wallpaper,parent,false);
        return new HolderWallpaper(view);
    }

    @Override
    public void onBindViewHolder(HolderWallpaper holderWallpaper, int i) {
          //holderWallpaper.terapkan.setText(dataWallpp.get(i).getTerapkan());
        holderWallpaper.wallpaper.setImageResource(dataWallpp.get(i).getImageWalpp());
        holderWallpaper.judul_walpp.setText(dataWallpp.get(i).getJudul_walpp());
    }

    @Override
    public int getItemCount() {
        // return (dataKata != null) ? dataKata.size() : 0;
        return (dataWallpp !=null)? dataWallpp.size():0;
    }

    class HolderWallpaper extends RecyclerView.ViewHolder {
        TextView judul_walpp;
        Button terapkan;
        ImageView wallpaper;
        public HolderWallpaper(View itemView) {
            super(itemView);
            judul_walpp = (TextView)itemView.findViewById(R.id.judul_walpp);
            //terapkan = (Button)itemView.findViewById(R.id.terapkan);
            wallpaper = (ImageView) itemView.findViewById(R.id.image_walpp);
        }
    }
}
