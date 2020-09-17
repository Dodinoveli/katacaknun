package com.app.katacaknun.adapter;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.katacaknun.R;
import com.app.katacaknun.model.DataKetegori;
import com.bumptech.glide.Glide;
import java.util.List;

public class AdapterMaster extends RecyclerView.Adapter<AdapterMaster.KataHolder> {
    private List<DataKetegori>dataMaster;
    private Context context;
    public AdapterMaster(Context context, List<DataKetegori> dataMaster){
       this.dataMaster = dataMaster;
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
        kataHolder.judul.setText(dataMaster.get(position).getKategori());
        Glide.with(context).load(dataMaster.get(position).getImage()).circleCrop().into(kataHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return (dataMaster != null) ? dataMaster.size() : 0;
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

