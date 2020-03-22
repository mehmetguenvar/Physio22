package com.example.mehme.physio22.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mehme.physio22.R;
import com.example.mehme.physio22.adapter.UebungCVAdapter;
import com.example.mehme.physio22.viewmodels.UebungViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UebungFragment extends Fragment {

    private UebungViewModel mViewModel;

    public static UebungFragment newInstance() {
        return new UebungFragment();
    }

    TextView kategorie;
    RecyclerView uebungRV;
    FloatingActionButton addUebungButton;
    UebungCVAdapter adapter;
    SwipeRefreshLayout swr;
    boolean isFromKategorie = false;
    long katId = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_uebung, container, false);

        kategorie = v.findViewById(R.id.textViewUebungObekarText);
        uebungRV = v.findViewById(R.id.RVUebungen);
        addUebungButton = v.findViewById(R.id.floatingActionButtonFragmentUebung);
        swr = v.findViewById(R.id.swrUebungObekarText);

        if(getArguments() != null && getArguments().getLong("KategorieId",-1) != -1 && getArguments().getString("KategorieName")!=null){
            isFromKategorie = true;
            katId = getArguments().getLong("KategorieId",-1);
            kategorie.setText(getArguments().getString("KategorieName"));
        }else{
            kategorie.setVisibility(View.GONE);
        }

        addUebungButton.setOnClickListener(view ->{
            Bundle x = new Bundle();
            if(isFromKategorie){
            x.putLong("KategorieId",katId);
            x.putString("KategorieName",kategorie.getText().toString());}
            Navigation.findNavController(view).navigate(R.id.action_uebungFragment_to_uebungErstellungFragment,x);
        } );

        swr.setOnRefreshListener(() -> {
            mViewModel.update();
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(UebungViewModel.class);

        if(isFromKategorie){
            mViewModel.filterByKategorie(katId);
        }else mViewModel.disableFilter();

        adapter = new UebungCVAdapter(mViewModel.getUebungs().getValue(),getContext());
        uebungRV.setAdapter(adapter);
        uebungRV.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        mViewModel.dataSource.observe(getViewLifecycleOwner(),uebungs -> {
            adapter.submitList(uebungs);
            swr.setRefreshing(false);
        });


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
