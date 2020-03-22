package com.example.mehme.physio22.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.QuickContactBadge;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mehme.physio22.R;
import com.example.mehme.physio22.dtos.KategorieDTO;
import com.example.mehme.physio22.viewmodels.KategorieViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class KategorieErstellungFragment extends Fragment {


    public KategorieErstellungFragment() {
        // Required empty public constructor
    }


    EditText bezeichnung;
    Switch isLeaf;
    Button buttonSave;

    KategorieViewModel kategorieViewModel;
    MutableLiveData<KategorieDTO> kategorieDTO;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kategorie_erstellung, container, false);

        kategorieViewModel = new ViewModelProvider(getActivity()).get(KategorieViewModel.class);

        if(getArguments()==null ){
            kategorieDTO = new MutableLiveData<>(new KategorieDTO());
        }else{
            long katid = getArguments().getLong("KategorieId",-1);
            long  oberkat = getArguments().getLong("OberKategorieId",-1);


            if(katid == -1 && oberkat == -1){
                kategorieDTO = new MutableLiveData<>(new KategorieDTO());
            }else {
                if(katid != -1 && oberkat == -1)
                kategorieDTO = kategorieViewModel.getKategorie(oberkat,katid);
                else{
                    if(katid != -1 )
                    kategorieDTO = kategorieViewModel.getKategorie(oberkat,katid);
                    else{
                        kategorieDTO = new MutableLiveData<>(new KategorieDTO());

                        kategorieDTO.getValue().setOberkategorieId(oberkat);
                    }

                }
            }

        }


        bezeichnung = view.findViewById(R.id.editTextNameFragmentKategorieErstellung);
        isLeaf = view.findViewById(R.id.switchIsLeafFragmentKategorieErstellung);
        buttonSave = view.findViewById(R.id.buttonAddKategorieFragmentKategorieErstellung);

        kategorieDTO.observe(getViewLifecycleOwner(), new Observer<KategorieDTO>() {
            @Override
            public void onChanged(KategorieDTO kategorieDTO) {
                setData(kategorieDTO);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kategorieDTO.getValue() != null){
                    kategorieViewModel.saveKategorie(kategorieDTO.getValue()).observe(getViewLifecycleOwner(), new Observer<KategorieDTO>() {
                        @Override
                        public void onChanged(KategorieDTO kategorieDTO) {
                            if(kategorieDTO == null){
                                Toast.makeText(getContext(),"Fehler beim speichern",Toast.LENGTH_LONG).show();

                            }
                            else{
                                Toast.makeText(getContext(),"Erfolgreich gespeicher",Toast.LENGTH_LONG).show();
                                Navigation.findNavController(view).navigateUp();
                            }
                        }
                    });
                }
            }
        });

        setBinders();
        return view;
    }

    private void setBinders(){
        bezeichnung.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(kategorieDTO.getValue() != null)
                    kategorieDTO.getValue().setBezeichnung(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        isLeaf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(kategorieDTO.getValue() != null)
                kategorieDTO.getValue().setIsLeaf(b);
            }
        });
    }

    private void setData(KategorieDTO kategorieDTO){
        bezeichnung.setText(kategorieDTO.getBezeichnung());
        isLeaf.setChecked(kategorieDTO.isIsLeaf());
    }



}
