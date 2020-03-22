package com.example.mehme.physio22.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mehme.physio22.R;
import com.example.mehme.physio22.adapter.KategorieCVAdapter;
import com.example.mehme.physio22.dtos.KategorieDTO;
import com.example.mehme.physio22.viewmodels.KategorieViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 */
public class KategorieFragment extends Fragment {


    private FloatingActionButton buttonAddKategorie;
    private RecyclerView katList;
    private SwipeRefreshLayout swr;
    private TextView oberkattext;

    private LiveData<LinkedList<KategorieDTO>> kategorienlist;
    private KategorieViewModel viewModel;
    private KategorieCVAdapter kategorieCVAdapter;

    private long kategorieId = -1;

    public KategorieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kategorie, container, false);

        katList = view.findViewById(R.id.RVKategorien);
        oberkattext = view.findViewById(R.id.textViewKategorieObekarText);
        buttonAddKategorie = view.findViewById(R.id.floatingActionButtonFragmentKategorie);

        kategorieCVAdapter = new KategorieCVAdapter(new LinkedList<>(),getContext());
        katList.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        katList.setAdapter(kategorieCVAdapter);

        viewModel = new ViewModelProvider(getActivity()).get(KategorieViewModel.class);

        if(getArguments() != null && getArguments().getLong("KategorieId",-1) != -1){
            kategorieId = getArguments().getLong("KategorieId",-1);
            oberkattext.setText(getArguments().getString("KategorieName",""));
            kategorienlist = viewModel.getKategoriesByOberKategorie(getArguments().getLong("KategorieId",-1));
        }else{
            kategorienlist = viewModel.getMainKategories();
        }

        //kategorienlist = viewModel.getKategorien();
        kategorienlist.observe(getViewLifecycleOwner(), new Observer<LinkedList<KategorieDTO>>() {
            @Override
            public void onChanged(LinkedList<KategorieDTO> kategorieDTOS) {
                kategorieCVAdapter.update(kategorieDTOS);
            }
        });

        buttonAddKategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle x = new Bundle();
                if(kategorieId != -1)
                x.putLong("OberKategorieId",kategorieId);
                Navigation.findNavController(view).navigate(R.id.action_nav_kategorien_to_kategorieErstellungFragment,x);
            }
        });


        return view;
    }

}
