package com.example.mehme.physio22.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mehme.physio22.Database.entities.Uebung;
import com.example.mehme.physio22.R;

import java.io.Serializable;
import java.util.List;

public class UebungCVAdapter extends PagedListAdapter<Uebung, UebungCVAdapter.ViewHolder> implements Serializable {

    private PagedList<Uebung> uebungDtos;
    private Context context;
    private LayoutInflater inflater;

    private static DiffUtil.ItemCallback<Uebung> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Uebung>() {

                @Override
                public boolean areItemsTheSame(Uebung oldItem, Uebung newItem) {
                    // The ID property identifies when items are the same.
                    return oldItem.getUebungId() == newItem.getUebungId();
                }

                @Override
                public boolean areContentsTheSame(Uebung oldItem, Uebung newItem) {
                    // Don't use the "==" operator here. Either implement and use .equals(),
                    // or write custom data comparison logic here.
                    return oldItem.isSame(newItem);
                }
            };


    public UebungCVAdapter(PagedList<Uebung> uebungDtos, Context context){
        super(DIFF_CALLBACK);
        this.uebungDtos = uebungDtos;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UebungCVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cardview_uebung,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (!payloads.isEmpty()) {
            Uebung item = getItem(position);
            ((ViewHolder)holder).setData(item);
        } else {
            onBindViewHolder(holder, position);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.setData(getItem(position));
    }


    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public void update(PagedList<Uebung> uebungDtos){
        this.uebungDtos = uebungDtos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bezeichnung;
        ImageView bild;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bezeichnung = itemView.findViewById(R.id.textViewCarvdiewUebung);
            bild = itemView.findViewById(R.id.imageViewCarvdViewUebung);
        }

        public void setData(Uebung uebung){
            bezeichnung.setText(uebung.getBezeichnung());
            try {
                bild.setImageBitmap(BitmapFactory.decodeByteArray(uebung.getBild(),0,uebung.getBild().length));
            }catch (Exception e){

            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle x = new Bundle();
                    x.putLong("UebungId",uebung.getUebungId());
                    x.putBoolean("LoadTrueData",true);
                    Navigation.findNavController(view).navigate(R.id.action_uebungFragment_to_uebungErstellungFragment,x);
                }
            });
        }
    }
}
