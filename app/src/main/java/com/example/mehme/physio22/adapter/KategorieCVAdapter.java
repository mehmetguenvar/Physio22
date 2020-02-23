package com.example.mehme.physio22.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mehme.physio22.R;
import com.example.mehme.physio22.dtos.KategorieDTO;
import com.example.mehme.physio22.dtos.KundenDatenDTO;

import java.io.Serializable;
import java.util.LinkedList;

public class KategorieCVAdapter extends RecyclerView.Adapter<KategorieCVAdapter.ViewHolder> implements Serializable {

    private LinkedList<KategorieDTO> kategorieDTOS;
    private Context context;
    private LayoutInflater inflater;

    public KategorieCVAdapter(LinkedList<KategorieDTO> kategorieDTOS, Context context){
        this.kategorieDTOS = kategorieDTOS;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public KategorieCVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cardview_kategorie,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KategorieCVAdapter.ViewHolder holder, int position) {
        holder.setData(kategorieDTOS.get(position));
    }

    @Override
    public int getItemCount() {
        return kategorieDTOS.size();
    }

    public void update(LinkedList<KategorieDTO> kategorieDTOS){
        this.kategorieDTOS = kategorieDTOS;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bezeichnung;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bezeichnung = itemView.findViewById(R.id.textViewCVKategorieBeschreibung);

        }

        public void setData(KategorieDTO kategorieDTO){
            if(kategorieDTO.getBezeichnung()!=null)
            bezeichnung.setText(kategorieDTO.getBezeichnung());

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Bundle x = new Bundle();
                    x.putLong("KategorieId",kategorieDTO.getId());
                    //Navigation.findNavController(view).navigate(R.id.action_nav_kunde_to_kundenErstellungFragment,x);
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(kategorieDTO.isIsLeaf()==null || kategorieDTO.isIsLeaf()){
                        //uebungen anzeigen
                    }else{
                        // zur nächsten Kategorie
                    }
                }
            });
        }
    }
}
