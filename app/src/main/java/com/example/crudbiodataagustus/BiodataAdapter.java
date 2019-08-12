package com.example.crudbiodataagustus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudbiodataagustus.model.DataBiodataItem;

import org.w3c.dom.Text;

import java.util.List;

public class BiodataAdapter extends RecyclerView.Adapter<BiodataAdapter.ViewHolder> {
    Context context;
    List<DataBiodataItem> dataBiodataItems;

    public BiodataAdapter(Context context, List<DataBiodataItem> dataBiodataItems){
        this.context = context;
        this.dataBiodataItems = dataBiodataItems;
    }

    @NonNull
    @Override
    public BiodataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_biodata, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BiodataAdapter.ViewHolder holder, int i) {

        final String id = dataBiodataItems.get(i).getId();
        final String nama = dataBiodataItems.get(i).getNama();
        final String jekel = dataBiodataItems.get(i).getJekel();
        final String hobi = dataBiodataItems.get(i).getHobi();
        final String alamat = dataBiodataItems.get(i).getAlamat();


        holder.nama.setText(nama);
        holder.jekel.setText(jekel);
        holder.hobi.setText(hobi);
        holder.alamat.setText(alamat);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBiodataItem data = new DataBiodataItem();
                data.setId(id);
                data.setNama(nama);
                data.setJekel(jekel);
                data.setHobi(hobi);
                data.setAlamat(alamat);

                Intent i = new Intent(context, UpdateDeleteActivity.class); //context krn ini bukan activity, jd butuh context
                i.putExtra(UpdateDeleteActivity.key_biodata, data);
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataBiodataItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nama;
        TextView jekel;
        TextView hobi;
        TextView alamat;

        public ViewHolder(@NonNull View itemView){
            super (itemView);

            nama = itemView.findViewById(R.id.list_nama);
            jekel = itemView.findViewById(R.id.list_jekel);
            hobi = itemView.findViewById(R.id.list_hobi);
            alamat = itemView.findViewById(R.id.list_alamat);
        }
    }
}
