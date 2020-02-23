package com.example.mehme.physio22.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mehme.physio22.dtos.KundenDatenDTO;
import com.example.mehme.physio22.webservices.repositories.KundenRepository;

import java.util.LinkedList;

public class KundenViewModel extends ViewModel {

    LiveData<LinkedList<KundenDatenDTO>> kundendatenDTOs;

    public KundenViewModel(){
        kundendatenDTOs = KundenRepository.getAllKundenDaten();
    }

    public LiveData<LinkedList<KundenDatenDTO>> getKundeDaten(){
        return kundendatenDTOs;
    }

    public LiveData<LinkedList<KundenDatenDTO>> update(){
        kundendatenDTOs = KundenRepository.getAllKundenDaten();
        return kundendatenDTOs;
    }

}
