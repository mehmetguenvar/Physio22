package com.example.mehme.physio22.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.mehme.physio22.Database.entities.KundenDaten;
import com.example.mehme.physio22.Database.entities.Uebung;
import com.example.mehme.physio22.webservices.repositories.GenericBoundaryCallback;
import com.example.mehme.physio22.webservices.repositories.Listing;
import com.example.mehme.physio22.webservices.repositories.NetworkState;
import com.example.mehme.physio22.webservices.repositories.UebungRepository;

public class UebungViewModel extends AndroidViewModel {

    private UebungRepository uebungRepository;
    private MutableLiveData<Listing<Uebung>> listing;
    public LiveData<PagedList<Uebung>> dataSource;
    public LiveData<NetworkState> networkState;
    private LiveData<GenericBoundaryCallback<Uebung>> boundaryCallback;

    private boolean isFiltered = false;
    private long filteId = 0;

    public UebungViewModel(Application application){
        super(application);

        uebungRepository = UebungRepository.getInstance(application);

        listing = new MutableLiveData<>(uebungRepository.getListable());

        listing.observeForever(uebungListing -> {
            boundaryCallback = Transformations.switchMap(listing, Listing::getBoundaryCallback);
            dataSource = Transformations.switchMap(listing, Listing::getDataSource);
            networkState = Transformations.switchMap(listing, Listing::getNetworkState);
        });
    }

    public void retry(){
        boundaryCallback.getValue().retryPetitions();
    }

    public void disableFilter(){
        if(!isFiltered)return;
        listing.postValue((uebungRepository.getListable()));
        isFiltered=false;
    }

    public void filterByKategorie(long id){
        if(isFiltered && id == this.filteId)return;
        listing.postValue(uebungRepository.getListableFiltered(id));
        isFiltered=true;
    }

    @Override
    protected void onCleared() {
        boundaryCallback.getValue().cleared();
    }

    public LiveData<PagedList<Uebung>> getUebungs(){
        return dataSource;
    }

    public LiveData<PagedList<Uebung>> update(){
        //kundendatenDTOs = kundenRepository.getAllKundenDaten();
        listing.getValue().getBoundaryCallback().getValue()
                .refreshPage();
        return dataSource;
    }

    public MutableLiveData<Uebung> getUebung(long id){
        if(dataSource.getValue().stream().anyMatch(uebung -> uebung.getUebungId()==id)){
            return new MutableLiveData<Uebung>(dataSource.getValue().stream().filter(uebung -> uebung.getUebungId() == id).findFirst().get());
        }else{
            return uebungRepository.getUebung(id);
        }
    }

    public MutableLiveData<Uebung> getUebungTrue(long id){
        return uebungRepository.getUebung(id);
    }

    public MutableLiveData<Uebung> saveUebung(Uebung uebung){
        return uebungRepository.saveUebung(uebung);
    }

    // TODO: Implement the ViewModel
}
