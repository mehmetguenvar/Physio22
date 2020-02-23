package com.example.mehme.physio22.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mehme.physio22.R;
import com.example.mehme.physio22.dtos.KundenDatenDTO;
import com.example.mehme.physio22.viewmodels.KundenViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class KundenErstellungFragment extends Fragment {


    KundenDatenDTO kundenDatenDTO;

    EditText vorname;
    EditText nachname;
    EditText mail;
    EditText vsnummer;
    Button saveButton;

    KundenViewModel kundenViewModel;
    public KundenErstellungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_kunden_erstellung, container, false);

        vorname = v.findViewById(R.id.editTextKundenErstellungVorname);
        nachname = v.findViewById(R.id.editTextKundenErstellungNachname);
        mail = v.findViewById(R.id.editTextKundenErstellungMail);
        vsnummer = v.findViewById(R.id.editTextKundenErstellungVsNummer);
        saveButton = v.findViewById(R.id.buttonSaveKunde);

        kundenViewModel = new ViewModelProvider(this).get(KundenViewModel.class);

        if(getArguments() != null && getArguments().getLong("KundeId",-1) != -1){
            kundenViewModel.getKunde(getArguments().getLong("KundeId",-1)).observe(getViewLifecycleOwner(), new Observer<KundenDatenDTO>() {
                @Override
                public void onChanged(KundenDatenDTO kundenDatenDTOa) {
                    kundenDatenDTO = kundenDatenDTOa;
                    setData();
                    setBinder();
                }
            });
        }else{
            kundenDatenDTO = new KundenDatenDTO();
            setData();
            setBinder();
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kundenDatenDTO!=null)
                kundenViewModel.saveKunde(kundenDatenDTO).observe(getViewLifecycleOwner(), kundenDatenDTO1 -> {if(kundenDatenDTO1!=null) {
                    Toast.makeText(getContext(), "Speichern erfolgreich", Toast.LENGTH_LONG).show();
                    kundenDatenDTO = kundenDatenDTO1;
                    setData();
                    setBinder();
                }
                else
                    Toast.makeText(getContext(),"Fehler",Toast.LENGTH_LONG).show();
                });
            }
        });



        return v;
    }

    private void setBinder(){
        vorname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kundenDatenDTO.setVorname(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        nachname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kundenDatenDTO.setNachname(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kundenDatenDTO.setEmailadresse(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        vsnummer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int x = 0;
                try {
                    x = Integer.parseInt(charSequence.toString());
                }catch (Exception e){}
                kundenDatenDTO.setVsnummer(x);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setData(){
        vorname.setText(kundenDatenDTO.getVorname());
        nachname.setText(kundenDatenDTO.getNachname());
        mail.setText(kundenDatenDTO.getEmailadresse());
        if(kundenDatenDTO.getVsnummer()!=null)
        vsnummer.setText(kundenDatenDTO.getVsnummer().toString());
    }

}
