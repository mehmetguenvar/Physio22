package com.example.mehme.physio22.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mehme.physio22.R;
import com.example.mehme.physio22.dtos.KundenDatenDTO;

import java.io.Serializable;
import java.util.LinkedList;

public class KundenCVAdapter extends RecyclerView.Adapter<KundenCVAdapter.ViewHolder> implements Serializable {

    private LinkedList<KundenDatenDTO> kundenDatenDTOS;
    private Context context;
    private LayoutInflater inflater;

    public KundenCVAdapter(LinkedList<KundenDatenDTO> kundenDatenDTOS, Context context){
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
    public void onBindViewHolder(@NonNull KundenCVAdapter.ViewHolder holder, int position) {
        holder.setData(kundenDatenDTOS.get(position));
    }

    @Override
    public int getItemCount() {
        return kundenDatenDTOS.size();
    }

    public void update(LinkedList<KundenDatenDTO> kundenDatenDTOS){
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

        public void setData(KundenDatenDTO kunde){
            name.setText(String.format("%s %s", kunde.getVorname(), kunde.getNachname()));
            mail.setText(kunde.getEmailadresse());
            if(kunde.getVsnummer()!=null)
            vsnummer.setText(kunde.getVsnummer().toString());
            else vsnummer.setText("");
        }
    }
}
