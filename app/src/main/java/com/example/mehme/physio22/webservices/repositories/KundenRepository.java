package com.example.mehme.physio22.webservices.repositories;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mehme.physio22.dtos.KundenDatenDTO;
import com.example.mehme.physio22.dtos.UebungDTO;
import com.example.mehme.physio22.webservices.IPhysioService;
import com.example.mehme.physio22.webservices.Rest;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class KundenRepository {

    private static IPhysioService iPhysioService = Rest.physioService;
    private static MutableLiveData<LinkedList<KundenDatenDTO>> kundendatenDTOsCached = new MutableLiveData<>();

    static {
        kundendatenDTOsCached.setValue(new LinkedList<>());
    }

    public static LiveData<LinkedList<KundenDatenDTO>> getAllKundenDaten(){

        iPhysioService.getAllKunden(Rest.token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LinkedList<KundenDatenDTO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LinkedList<KundenDatenDTO> kundenDatenDTOS) {
                        kundendatenDTOsCached.postValue(kundenDatenDTOS);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return kundendatenDTOsCached;

    }

    public static MutableLiveData<KundenDatenDTO> saveKunde(KundenDatenDTO kundenDatenDTO){
        MutableLiveData<KundenDatenDTO> kunde = new MutableLiveData<>();

        if(kundenDatenDTO.getId()!=null)
        iPhysioService.saveKunde(Rest.token,kundenDatenDTO).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KundenDatenDTO>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KundenDatenDTO kundenDatenDTO) {
                        kunde.postValue(kundenDatenDTO);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        else iPhysioService.createKunde(Rest.token,kundenDatenDTO).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KundenDatenDTO>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KundenDatenDTO kundenDatenDTO) {
                        kunde.postValue(kundenDatenDTO);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return kunde;
    }

    public static MutableLiveData<KundenDatenDTO> getKunde(long id){
        MutableLiveData<KundenDatenDTO> kunde = new MutableLiveData<>();

        iPhysioService.getKunde(Rest.token,id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KundenDatenDTO>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KundenDatenDTO kundenDatenDTO) {
                        kunde.postValue(kundenDatenDTO);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return kunde;
    }
}
