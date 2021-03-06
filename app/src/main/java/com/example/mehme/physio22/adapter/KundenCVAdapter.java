package com.example.mehme.physio22.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mehme.physio22.Database.entities.KundenDaten;
import com.example.mehme.physio22.R;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class KundenCVAdapter extends PagedListAdapter<KundenDaten,KundenCVAdapter.ViewHolder> implements Serializable {

    private PagedList<KundenDaten> kundenDatenDTOS;
    private Context context;
    private LayoutInflater inflater;

    private static DiffUtil.ItemCallback<KundenDaten> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<KundenDaten>() {

                @Override
                public boolean areItemsTheSame(KundenDaten oldItem, KundenDaten newItem) {
                    // The ID property identifies when items are the same.
                    return oldItem.getKundenDatenId() == newItem.getKundenDatenId();
                }

                @Override
                public boolean areContentsTheSame(KundenDaten oldItem, KundenDaten newItem) {
                    // Don't use the "==" operator here. Either implement and use .equals(),
                    // or write custom data comparison logic here.
                    return oldItem.isSame(newItem);
                }
            };


    public KundenCVAdapter(PagedList<KundenDaten> kundenDatenDTOS, Context context){
        super(DIFF_CALLBACK);
        this.kundenDatenDTOS = kundenDatenDTOS;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public KundenCVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cardview_kunde,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (!payloads.isEmpty()) {
            KundenDaten item = getItem(position);
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

    public void update(PagedList<KundenDaten> kundenDatenDTOS){
        this.kundenDatenDTOS = kundenDatenDTOS;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView mail;
        TextView vsnummer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textViewCVKundeName);
            mail = itemView.findViewById(R.id.textViewCVKundeMail);
            vsnummer = itemView.findViewById(R.id.textViewCVKundeCVNummer);
        }

        public void setData(KundenDaten kunde){
            name.setText(String.format("%s %s", kunde.getVorname(), kunde.getNachname()));
            mail.setText(kunde.getEmailadresse());
            if(kunde.getVsnummer()!=null)
            vsnummer.setText(kunde.getVsnummer().toString());
            else vsnummer.setText("");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle x = new Bundle();
                    x.putLong("KundeId",kunde.getKundenDatenId());
                    Navigation.findNavController(view).navigate(R.id.action_nav_kunde_to_kundenErstellungFragment,x);
                }
            });
        }
    }
}
