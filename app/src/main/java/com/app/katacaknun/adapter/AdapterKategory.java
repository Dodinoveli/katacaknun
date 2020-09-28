package com.app.katacaknun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.katacaknun.R;
import com.app.katacaknun.model.M_Ketegori;
import com.app.katacaknun.model.M_kata;

import java.util.ArrayList;

public class AdapterKategory extends RecyclerView.Adapter<AdapterKategory.KategoryHolder>{
    ArrayList <M_kata>list_kata=new ArrayList<>();
    private Context context;
    public AdapterKategory(Context context, ArrayList<M_kata>list_kata){
        this.context = context;
        this.list_kata = list_kata;
    }
    @NonNull
    @Override
    public KategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_kategory,parent,false);
        return new KategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KategoryHolder holder, int position) {
        holder.judul.setText(list_kata.get(position).getJudul());
    }

    @Override
    public int getItemCount() {
        return list_kata.size();
    }

    public class KategoryHolder extends RecyclerView.ViewHolder {
    private TextView judul;
        public KategoryHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.text_detail_kat);
        }
    }
}


