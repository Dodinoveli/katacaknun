package com.app.katacaknun.adapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.katacaknun.R;
import com.app.katacaknun.model.FirstKata;
import java.util.ArrayList;

public class FirstKataadapter extends RecyclerView.Adapter<FirstKataadapter.KataHolder> {
    /* buat array  tiga  */
    private ArrayList<FirstKata> dataFirstKata = new ArrayList<>();

    public FirstKataadapter(ArrayList<FirstKata> dataFirstKata){
       this.dataFirstKata = dataFirstKata;
    }

    @NonNull
    @Override
    public KataHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_katacaknun, parent, false);
        return new KataHolder(view);
    }

    /*mengikat view holder*/
    @Override
    public void onBindViewHolder(@NonNull KataHolder kataHolder, int position) {
        kataHolder.judul.setText(dataFirstKata.get(position).getJudul());
        kataHolder.imageView.setImageResource(dataFirstKata.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return (dataFirstKata != null) ? dataFirstKata.size() : 0;
    }

    /*Class holder kata kata */
    public class KataHolder extends RecyclerView.ViewHolder{
        private TextView judul;
        private ImageView imageView;

        public KataHolder(@NonNull View itemView) {
            super(itemView);
            judul = (TextView)itemView.findViewById(R.id.txt_judul);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}

