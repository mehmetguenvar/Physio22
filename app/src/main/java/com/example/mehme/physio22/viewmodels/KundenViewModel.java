package com.example.mehme.physio22.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.example.mehme.physio22.Database.entities.KundenDaten;
import com.example.mehme.physio22.webservices.repositories.GenericBoundaryCallback;
import com.example.mehme.physio22.webservices.repositories.KundenRepository;
import com.example.mehme.physio22.webservices.repositories.Listing;
import com.example.mehme.physio22.webservices.repositories.NetworkState;

import java.util.LinkedList;
import java.util.Objects;

public class KundenViewModel extends AndroidViewModel {

    //private LiveData<PagedList<KundenDaten>> kundendatenDTOs;
    private KundenRepository kundenRepository;

    private LiveData<Listing<KundenDaten>> listing;

    private LiveData<GenericBoundaryCallback<KundenDaten>> boundaryCallback;
    public LiveData<PagedList<KundenDaten>> dataSource;
    public LiveData<NetworkState> networkState ;

    public KundenViewModel(Application application){
        super(application);
        kundenRepository = KundenRepository.getInstance(application);
        //kundendatenDTOs = kundenRepository.getAllKundenDaten();

        listing  = new MutableLiveData<>(kundenRepository.getListable());
        boundaryCallback  = Transformations.switchMap(listing, Listing<KundenDaten>::getBoundaryCallback);
        dataSource  = Transformations.switchMap(listing, Listing<KundenDaten>::getDataSource);
        networkState= Transformations.switchMap(listing, Listing<KundenDaten>::getNetworkState);
    }

    public void retry(){
       boundaryCallback.getValue().retryPetitions();
    }

    @Override
    protected void onCleared() {
        Objects.requireNonNull(boundaryCallback.getValue()).cleared();
    }

    public LiveData<PagedList<KundenDaten>> getKundeDaten(){
        return dataSource;
    }

    public LiveData<PagedList<KundenDaten>> update(){
        //kundendatenDTOs = kundenRepository.getAllKundenDaten();
        listing.getValue().getBoundaryCallback().getValue()
                .refreshPage();
        return dataSource;
    }

    public MutableLiveData<KundenDaten> getKunde(long id){
        if(dataSource.getValue().stream().anyMatch(kundenDatenDTO -> kundenDatenDTO.getKundenDatenId()==id)){
            return new MutableLiveData<KundenDaten>(dataSource.getValue().stream().filter(kundenDatenDTO -> kundenDatenDTO.getKundenDatenId() == id).findFirst().get());
        }else{
            return kundenRepository.getKunde(id);
        }
    }

    public MutableLiveData<KundenDaten> saveKunde(KundenDaten kundenDatenDTO){
        return kundenRepository.saveKunde(kundenDatenDTO);
    }

}
