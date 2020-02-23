package com.example.mehme.physio22.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mehme.physio22.R;
import com.example.mehme.physio22.adapter.KategorieCVAdapter;
import com.example.mehme.physio22.adapter.KundenCVAdapter;
import com.example.mehme.physio22.dtos.KategorieDTO;
import com.example.mehme.physio22.dtos.KundenDatenDTO;
import com.example.mehme.physio22.viewmodels.KategorieViewModel;
import com.example.mehme.physio22.viewmodels.KundenViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 */
public class KategorieFragment extends Fragment {


    private FloatingActionButton buttonAddKunde;
    private RecyclerView katList;
    private SwipeRefreshLayout swr;
    private TextView oberkattext;

    private LiveData<LinkedList<KategorieDTO>> kategorienlist;
    private KategorieViewModel viewModel;
    private KategorieCVAdapter kategorieCVAdapter;

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

        kategorieCVAdapter = new KategorieCVAdapter(new LinkedList<>(),getContext());
        katList.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        katList.setAdapter(kategorieCVAdapter);

        viewModel = new ViewModelProvider(this).get(KategorieViewModel.class);

        kategorienlist = viewModel.getKategorien();
        kategorienlist.observe(getViewLifecycleOwner(), new Observer<LinkedList<KategorieDTO>>() {
            @Override
            public void onChanged(LinkedList<KategorieDTO> kategorieDTOS) {
                kategorieCVAdapter.update(kategorieDTOS);
            }
        });


        return view;
    }

}
