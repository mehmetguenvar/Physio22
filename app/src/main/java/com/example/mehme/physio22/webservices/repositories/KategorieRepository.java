package com.example.mehme.physio22.webservices.repositories;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mehme.physio22.dtos.KategorieDTO;
import com.example.mehme.physio22.webservices.IPhysioService;
import com.example.mehme.physio22.webservices.Rest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class KategorieRepository {

    private static IPhysioService iPhysioService = Rest.physioService;
    private static HashMap<Long, MutableLiveData<LinkedList<KategorieDTO>>> kategorieDTOsCached = new HashMap<>();
    private static MutableLiveData<LinkedList<KategorieDTO>> mainKategorieDTOsCached = new MutableLiveData<>();


    static {
        //kategorieDTOsCached.setValue(new LinkedList<>());
        mainKategorieDTOsCached.setValue(new LinkedList<>());
    }

    /*
    public static MutableLiveData<LinkedList<KategorieDTO>> getAllKategories(){

        iPhysioService.getAllKategories(Rest.token,null).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LinkedList<KategorieDTO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(LinkedList<KategorieDTO> kategorieDTOS) {
                        kategorieDTOsCached.postValue(kategorieDTOS);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    
                });

        return kategorieDTOsCached;
    }
*/
    public static MutableLiveData<LinkedList<KategorieDTO>> getKategoriesByOberkategorie(long id){
        RequestBody rb = RequestBody.create(MediaType.parse("form-data"),"oberkategorieid.specified=false");
        Map<String,String> filter = new HashMap<>();
        filter.put("oberkategorieId.equals",id+"");


        MutableLiveData<LinkedList<KategorieDTO>> katsbyid;
        if(kategorieDTOsCached.get(id) == null) {
            katsbyid = new MutableLiveData<>();
            kategorieDTOsCached.put(id,katsbyid);
        }
        iPhysioService.getAllKategories(Rest.token,filter).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LinkedList<KategorieDTO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(LinkedList<KategorieDTO> kategorieDTOS) {
                        kategorieDTOsCached.get(id).postValue(kategorieDTOS);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    
                });

        return kategorieDTOsCached.get(id);
    }

    public static MutableLiveData<LinkedList<KategorieDTO>> getMainKategories(){
        RequestBody rb = RequestBody.create(MediaType.parse("form-data"),"oberkategorieid.specified=false");
        Map<String,String> filter = new HashMap<>();
        filter.put("oberkategorieId.specified","false");
        iPhysioService.getAllKategories(Rest.token,filter).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LinkedList<KategorieDTO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(LinkedList<KategorieDTO> kategorieDTOS) {
                        mainKategorieDTOsCached.postValue(kategorieDTOS);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    
                });

        return mainKategorieDTOsCached;
    }

    public static MutableLiveData<KategorieDTO> getKategorie(long id){
        MutableLiveData<KategorieDTO> mutableLiveData = new MutableLiveData<>();

        iPhysioService.getKategorie(Rest.token,id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<KategorieDTO>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(KategorieDTO kategorieDTO) {
                        mutableLiveData.postValue(kategorieDTO);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    
                });

        return mutableLiveData;
    }

    public static MutableLiveData<KategorieDTO> saveKategorie(KategorieDTO kategorieDTO){

        MutableLiveData<KategorieDTO> mutableLiveData = new MutableLiveData<>();

        if(kategorieDTO.getId()!=null){
            iPhysioService.saveKategorie(Rest.token,kategorieDTO).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<KategorieDTO>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(KategorieDTO kategorieDTO) {
                            if(kategorieDTO.getOberkategorieId() != null){
                                kategorieDTOsCached.get(kategorieDTO.getOberkategorieId()).getValue().stream().forEach(kategorieDTO1 -> {
                                    if(kategorieDTO1.getId() == kategorieDTO.getId()){
                                        kategorieDTO1.setBezeichnung(kategorieDTO.getBezeichnung());
                                        kategorieDTO1.setIsLeaf(kategorieDTO.isIsLeaf());

                                    }

                                });
                                //kategorieDTOsCached.get(kategorieDTO.getOberkategorieId()).postValue(kategorieDTOsCached.get(kategorieDTO.getOberkategorieId()).getValue());
                            }else{
                                mainKategorieDTOsCached.getValue().stream().forEach(kategorieDTO1 -> {
                                    if(kategorieDTO1.getId() == kategorieDTO.getId()) {
                                        kategorieDTO1.setBezeichnung(kategorieDTO.getBezeichnung());
                                        kategorieDTO1.setIsLeaf(kategorieDTO.isIsLeaf());
                                    }
                                });
                            }
                            mutableLiveData.postValue(kategorieDTO);

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        
                    });
        }else{
            iPhysioService.createKategorie(Rest.token,kategorieDTO).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<KategorieDTO>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(KategorieDTO kategorieDTO) {
                            if(kategorieDTO.getOberkategorieId() != null){
                                kategorieDTOsCached.get(kategorieDTO.getOberkategorieId()).getValue().add(kategorieDTO);

                                //kategorieDTOsCached.get(kategorieDTO.getOberkategorieId()).postValue(kategorieDTOsCached.get(kategorieDTO.getOberkategorieId()).getValue());
                            }else{
                                mainKategorieDTOsCached.getValue().add(kategorieDTO);

                            }
                            mutableLiveData.postValue(kategorieDTO);

                        }

                        @Override
                        public void onError(Throwable e) {
                            mutableLiveData.postValue(null);
                        }

                        
                    });
        }

        return mutableLiveData;
    }

}
