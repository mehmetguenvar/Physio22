package com.example.mehme.physio22.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mehme.physio22.dtos.KategorieDTO;
import com.example.mehme.physio22.dtos.KundenDatenDTO;
import com.example.mehme.physio22.webservices.repositories.KategorieRepository;
import com.example.mehme.physio22.webservices.repositories.KundenRepository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class KategorieViewModel extends ViewModel {

    LiveData<LinkedList<KategorieDTO>> mainKategorieDTOs;
    HashMap<Long,LiveData<LinkedList<KategorieDTO>>> kategories;

    public KategorieViewModel(){
        //kategorieDTOs = KategorieRepository.getAllKategories();
        //mainKategorieDTOs = KategorieRepository.getMainKategories();
        mainKategorieDTOs = new MutableLiveData<>(new LinkedList<>());
        kategories = new HashMap<>();
    }

    //public LiveData<LinkedList<KategorieDTO>> getKategorien(){
    //    return kategorieDTOs;
    //}

    public void update(Long id){
        //kategorieDTOs = KategorieRepository.getAllKategories();
        if(id==null)
        mainKategorieDTOs = KategorieRepository.getMainKategories();
        else{
            KategorieRepository.getKategoriesByOberkategorie(id);
        }
    }


    public MutableLiveData<KategorieDTO> getKategorie(long oberkategorieId,long id){

        if(oberkategorieId == -1){
            if(mainKategorieDTOs.getValue().stream().anyMatch(kategorieDTO -> kategorieDTO.getId()==id)){
                return new MutableLiveData<KategorieDTO>(mainKategorieDTOs.getValue().stream().filter(kategorieDTO -> kategorieDTO.getId() == id).findFirst().get());
            }else{
                return KategorieRepository.getKategorie(id);
            }
        }else{
            if(kategories.get(oberkategorieId)!= null && kategories.get(oberkategorieId).getValue().stream().anyMatch(kategorieDTO -> kategorieDTO.getId()==id)){
                return new MutableLiveData<KategorieDTO>(kategories.get(oberkategorieId).getValue().stream().filter(kategorieDTO -> kategorieDTO.getId() == id).findFirst().get());
            }else{
                return KategorieRepository.getKategorie(id);
            }
        }
    }

    public LiveData<LinkedList<KategorieDTO>> getKategoriesByOberKategorie(long kategorieId) {

        if(kategories.get(kategorieId) == null){
            kategories.put(kategorieId,KategorieRepository.getKategoriesByOberkategorie(kategorieId));
        }

        return kategories.get(kategorieId);
    }


    public MutableLiveData<LinkedList<KategorieDTO>> getMainKategories(){
        if(mainKategorieDTOs.getValue() != null && mainKategorieDTOs.getValue().size() > 0){
            return (MutableLiveData<LinkedList<KategorieDTO>>) mainKategorieDTOs;

        }else{
            mainKategorieDTOs = KategorieRepository.getMainKategories();
            return (MutableLiveData<LinkedList<KategorieDTO>>) mainKategorieDTOs;
        }
    }



    public MutableLiveData<KategorieDTO> saveKategorie(KategorieDTO kategorieDTO){
        return KategorieRepository.saveKategorie(kategorieDTO);
    }


}
