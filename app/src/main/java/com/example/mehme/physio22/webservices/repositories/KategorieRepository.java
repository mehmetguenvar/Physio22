package com.example.mehme.physio22.webservices.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.mehme.physio22.dtos.KategorieDTO;
import com.example.mehme.physio22.webservices.IPhysioService;
import com.example.mehme.physio22.webservices.Rest;

import java.util.LinkedList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class KategorieRepository {

    private static IPhysioService iPhysioService = Rest.physioService;
    private static MutableLiveData<LinkedList<KategorieDTO>> kategorieDTOsCached = new MutableLiveData<>();

    static {
        kategorieDTOsCached.setValue(new LinkedList<>());
    }

    public static MutableLiveData<LinkedList<KategorieDTO>> getAllKategories(){
        iPhysioService.getAllKategories(Rest.token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LinkedList<KategorieDTO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LinkedList<KategorieDTO> kategorieDTOS) {
                        kategorieDTOsCached.postValue(kategorieDTOS);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return kategorieDTOsCached;
    }

    public static MutableLiveData<KategorieDTO> getKategorie(long id){
        MutableLiveData<KategorieDTO> mutableLiveData = new MutableLiveData<>();

        iPhysioService.getKategorie(Rest.token,id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KategorieDTO>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KategorieDTO kategorieDTO) {
                        mutableLiveData.postValue(kategorieDTO);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return mutableLiveData;
    }

    public static MutableLiveData<KategorieDTO> saveKategorie(KategorieDTO kategorieDTO){

        MutableLiveData<KategorieDTO> mutableLiveData = new MutableLiveData<>();

        if(kategorieDTO.getId()!=null){
            iPhysioService.saveKategorie(Rest.token,kategorieDTO).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<KategorieDTO>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(KategorieDTO kategorieDTO) {
                            mutableLiveData.postValue(kategorieDTO);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }else{
            iPhysioService.createKategorie(Rest.token,kategorieDTO).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<KategorieDTO>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(KategorieDTO kategorieDTO) {
                            mutableLiveData.postValue(kategorieDTO);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

        return mutableLiveData;
    }

}
