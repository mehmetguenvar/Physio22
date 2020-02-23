package com.example.mehme.physio22.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
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
import android.widget.Button;

import com.example.mehme.physio22.R;
import com.example.mehme.physio22.adapter.KundenCVAdapter;
import com.example.mehme.physio22.dtos.KundenDatenDTO;
import com.example.mehme.physio22.viewmodels.KundenViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;


public class PatientFragment extends Fragment {

    private FloatingActionButton buttonAddKunde;
    private RecyclerView kundenList;
    private SwipeRefreshLayout swr;

    private LiveData<LinkedList<KundenDatenDTO>> kundenDaten;
    private KundenViewModel viewModel;
    private KundenCVAdapter kundenCVAdapter;

    public PatientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_kunde, container, false);
//
        //buttonPatientsuche = (Button) view.findViewById(R.id.patientsuche);
//
        //buttonPatientsuche.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Navigation.findNavController(view).navigate(R.id.action_patientFragment_to_patientsucheFragment);
        //    }
        //});

        buttonAddKunde = view.findViewById(R.id.fragmentKundeAddKundeButton);
        kundenList = view.findViewById(R.id.fragmentKundeRV);
        swr = view.findViewById(R.id.fragmentKundeSwipeRefresher);
        kundenCVAdapter = new KundenCVAdapter(new LinkedList<>(),getContext());
        kundenList.setAdapter(kundenCVAdapter);
        kundenList.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));


        viewModel = new ViewModelProvider(this).get(KundenViewModel.class);

        kundenDaten = viewModel.update();
        kundenDaten.observe(getViewLifecycleOwner(), new Observer<LinkedList<KundenDatenDTO>>() {
            @Override
            public void onChanged(LinkedList<KundenDatenDTO> kundenDatenDTOS) {
                kundenCVAdapter.update(kundenDatenDTOS);
                swr.setRefreshing(false);
            }
        });

        swr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.update();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}