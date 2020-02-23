package com.example.mehme.physio22.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mehme.physio22.dtos.KategorieDTO;
import com.example.mehme.physio22.dtos.KundenDatenDTO;
import com.example.mehme.physio22.webservices.repositories.KategorieRepository;
import com.example.mehme.physio22.webservices.repositories.KundenRepository;

import java.util.LinkedList;

public class KategorieViewModel extends ViewModel {

    LiveData<LinkedList<KategorieDTO>> kategorieDTOs;

    public KategorieViewModel(){
        kategorieDTOs = KategorieRepository.getAllKategories();
    }

    public LiveData<LinkedList<KategorieDTO>> getKategorien(){
        return kategorieDTOs;
    }

    public LiveData<LinkedList<KategorieDTO>> update(){
        kategorieDTOs = KategorieRepository.getAllKategories();
        return kategorieDTOs;
    }

    public MutableLiveData<KategorieDTO> getKategorie(long id){
        if(kategorieDTOs.getValue().stream().anyMatch(kundenDatenDTO -> kundenDatenDTO.getId()==id)){
            return new MutableLiveData<KategorieDTO>(kategorieDTOs.getValue().stream().filter(kategorieDTO -> kategorieDTO.getId() == id).findFirst().get());
        }else{
            return KategorieRepository.getKategorie(id);
        }
    }

    public MutableLiveData<KategorieDTO> saveKategorie(KategorieDTO kategorieDTO){
        return KategorieRepository.saveKategorie(kategorieDTO);
    }

}
