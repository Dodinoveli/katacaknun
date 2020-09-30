package com.app.katacaknun.adapter;
import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.app.katacaknun.R;
import com.app.katacaknun.model.M_Ketegori;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class AdapterMaster extends RecyclerView.Adapter<AdapterMaster.KataHolder> {

    private ArrayList<M_Ketegori>dataMaster = new ArrayList<>();
    private Context context;

    public AdapterMaster(Context context, ArrayList<M_Ketegori> dataMaster){
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
        kataHolder.tgl.setText(dataMaster.get(position).getTgl());
        //Glide.with(context).load(dataMaster.get(position).getImage()).circleCrop().into(kataHolder.imageView);
        Glide.with(context).load(R.drawable.loo).circleCrop().into(kataHolder.imageView);
    }

    @Override
    public int getItemCount() {
        int a;
        if(dataMaster != null && !dataMaster.isEmpty()) {
            a = dataMaster.size();
        }
        else {
            a = 0;
        }
        return a;
    }


    /*Class holder kata kata */
    public class KataHolder extends RecyclerView.ViewHolder  {
        private TextView judul,tgl;
        private ImageView imageView;

        public KataHolder(View itemView) {
            super(itemView);
            judul = (TextView)itemView.findViewById(R.id.txt_judul);
            tgl = (TextView)itemView.findViewById(R.id.tgl);
            imageView = (ImageView) itemView.findViewById(R.id.img);

        }

    }
}

