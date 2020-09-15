package com.app.katacaknun.adapter;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.katacaknun.R;
import com.app.katacaknun.model.FirstKata;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FirstKataadapter extends RecyclerView.Adapter<FirstKataadapter.KataHolder> {
    /* buat array  tiga  */
    private ArrayList<FirstKata> dataFirstKata = new ArrayList<>();
    private Context context;
    public FirstKataadapter(Context context,ArrayList<FirstKata> dataFirstKata){
       this.dataFirstKata = dataFirstKata;
       this.context = context;
    }

    @Override
    public KataHolder onCreateViewHolder(ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_katacaknun, parent, false);
        return new KataHolder(view);
    }

    /*mengikat view holder*/
    @Override
    public void onBindViewHolder(KataHolder kataHolder, int position) {
        kataHolder.judul.setText(dataFirstKata.get(position).getJudul());
        //kataHolder.imageView.setImageResource(dataFirstKata.get(position).getImage());
        // Glide.with(context).load(imageUrls.get(i).getImageUrl()).into(viewHolder.img);
        Glide.with(context).load(dataFirstKata.get(position).getImage()).circleCrop().into(kataHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return (dataFirstKata != null) ? dataFirstKata.size() : 0;
    }

    /*Class holder kata kata */
    public class KataHolder extends RecyclerView.ViewHolder{
        private TextView judul;
        private ImageView imageView;

        public KataHolder(View itemView) {
            super(itemView);
            judul = (TextView)itemView.findViewById(R.id.txt_judul);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}

