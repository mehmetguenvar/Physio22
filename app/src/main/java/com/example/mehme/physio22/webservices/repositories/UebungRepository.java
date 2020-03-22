package com.example.mehme.physio22.webservices.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.mehme.physio22.Database.PhysioRoomDatabase;
import com.example.mehme.physio22.Database.daos.KategorieDao;
import com.example.mehme.physio22.Database.daos.KundenDatenDao;
import com.example.mehme.physio22.Database.daos.UebungDao;
import com.example.mehme.physio22.Database.daos.UebungKategorieCrossReffDao;
import com.example.mehme.physio22.Database.entities.Kategorie;
import com.example.mehme.physio22.Database.entities.KundenDaten;
import com.example.mehme.physio22.Database.entities.Uebung;
import com.example.mehme.physio22.Database.entities.UebungKategorieCrossRef;
import com.example.mehme.physio22.dtos.KategorieDTO;
import com.example.mehme.physio22.webservices.IPhysioService;
import com.example.mehme.physio22.webservices.Rest;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.SingleObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UebungRepository {

    private static UebungRepository instance;
    private static IPhysioService iPhysioService = Rest.physioService;
    private static HashMap<Long, MutableLiveData<LinkedHashMap<Long,Uebung>>> uebungDTOsCached = new HashMap<>();
    private static MutableLiveData<HashMap<Long,Uebung>> allUebungDto;

    private LiveData<PagedList<Uebung>> uebungCached;
    private UebungDao uebungDao;
    private UebungKategorieCrossReffDao crossReffDao;
    private KategorieDao kategorieDao;

    public final int SIZE_PAGE = 30;
    public final int NETWORK_SIZE_PAGE = 60;

    static {
        allUebungDto = new MutableLiveData<>(
                new HashMap<>()
        );
    }

    private Completable saveUebungs(List<? extends Uebung> m){
        List<Uebung> uebungs = (List<Uebung>)m;
        Completable a = uebungDao.insertUebungs(uebungs);
        for(Uebung u : uebungs){
            List<UebungKategorieCrossRef> kss = new LinkedList<>();
            for(Kategorie k : u.getKategories()){
                kategorieDao.insertKategorie(k);
                UebungKategorieCrossRef ufs = new UebungKategorieCrossRef();

                ufs.kategorie_id=k.getKategorieId();
                ufs.uebung_id =u.getUebungId();
                kss.add(ufs);

            }
            crossReffDao.insertCrossReffs(kss);//.doOnError(throwable -> throwable.printStackTrace());
        }



        return a;
    }

    public Listing<Uebung> getListable(){
        return new Listing<Uebung>() {


            @NotNull
            @Override
            public LiveData<NetworkState> getNetworkState() {
                return Transformations.switchMap(getBoundaryCallback(), input -> input.getNetworkState());
            }

            GenericBoundaryCallback<Uebung> bc = new GenericBoundaryCallback<Uebung>(
                    ()->uebungDao.deleteAllUebung(),
                    integer -> {
                        Map<String,String> configs = new HashMap<>();
                        int p = integer/NETWORK_SIZE_PAGE;
                        //configs.put("offset",integer+"");
                        configs.put("page",p+"");
                        configs.put("size",NETWORK_SIZE_PAGE+"");
                        return iPhysioService.getAllUebung(Rest.token,configs);
                    },u -> saveUebungs(u)
                    ,NETWORK_SIZE_PAGE,0
            );

            @NotNull
            @Override
            public LiveData<GenericBoundaryCallback<Uebung>> getBoundaryCallback() {
                return new MutableLiveData<>(bc);
            }

            @NotNull
            @Override
            public LiveData<PagedList<Uebung>> getDataSource() {
                PagedList.Config c = new PagedList.Config.Builder().setPageSize(SIZE_PAGE).setInitialLoadSizeHint(30).setPrefetchDistance(10)
                        .setEnablePlaceholders(false).build();
                return new LivePagedListBuilder<>(
                        uebungDao.getAllUebung(), c).setBoundaryCallback(bc)

                        .build();
            }
        };
    }

    public Listing<Uebung> getListableFiltered(long kategorieId){
        return new Listing<Uebung>() {


            @NotNull
            @Override
            public LiveData<NetworkState> getNetworkState() {
                return Transformations.switchMap(getBoundaryCallback(), input -> input.getNetworkState());
            }

            GenericBoundaryCallback<Uebung> bc = new GenericBoundaryCallback<Uebung>(
                    ()->uebungDao.deleteAllUebung(),
                    integer -> {
                        Map<String,String> configs = new HashMap<>();
                        int p = integer/NETWORK_SIZE_PAGE;
                        //configs.put("offset",integer+"");
                        configs.put("page",p+"");
                        configs.put("size",NETWORK_SIZE_PAGE+"");
                        configs.put("kategorieId.in",kategorieId+"");
                        return iPhysioService.getAllUebung(Rest.token,configs);
                    },u -> saveUebungs(u)
                    ,NETWORK_SIZE_PAGE,0
            );

            @NotNull
            @Override
            public LiveData<GenericBoundaryCallback<Uebung>> getBoundaryCallback() {
                return new MutableLiveData<>(bc);
            }

            @NotNull
            @Override
            public LiveData<PagedList<Uebung>> getDataSource() {
                PagedList.Config c = new PagedList.Config.Builder().setPageSize(SIZE_PAGE).setInitialLoadSizeHint(30).setPrefetchDistance(10)
                        .setEnablePlaceholders(false).build();
                return new LivePagedListBuilder<>(
                        uebungDao.getAllUebungByKategorieId(kategorieId), c).setBoundaryCallback(bc)

                        .build();
            }
        };
    }


    public static UebungRepository getInstance(Application application){
        if(instance == null){
            instance = new UebungRepository(application);
        }
        return instance;
    }

    private UebungRepository(Application application){
        PhysioRoomDatabase db = PhysioRoomDatabase.getDatabase(application);
        uebungDao = db.uebungDao();
        crossReffDao = db.uebungKategorieCrossReffDao();
        kategorieDao = db.kategorieDao();
        uebungCached = new LivePagedListBuilder<>(
                uebungDao.getAllUebung(), SIZE_PAGE).build();
    }

    public static MutableLiveData<LinkedHashMap<Long,Uebung>> getUebungsByKategorie(long id){
        Map<String,String> filter = new HashMap<>();
        filter.put("kategorieId.in",id+"");


        MutableLiveData<LinkedHashMap<Long,Uebung>> uebungsByKatId;
        if(uebungDTOsCached.get(id) == null) {
            uebungsByKatId = new MutableLiveData<>();
            uebungDTOsCached.put(id,uebungsByKatId);
        }
        iPhysioService.getAllUebung(Rest.token,filter).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LinkedList<Uebung>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(LinkedList<Uebung> uebungDTOS) {
                        LinkedHashMap<Long,Uebung> katu = new LinkedHashMap<>();
                        uebungDTOS.forEach(uebungDTO -> {
                            katu.put(uebungDTO.getUebungId(),uebungDTO);

                            if(allUebungDto.getValue().containsKey(uebungDTO.getUebungId())) {
                                allUebungDto.getValue().replace(uebungDTO.getUebungId(), uebungDTO);
                            }

                            else allUebungDto.getValue().put(uebungDTO.getUebungId(),uebungDTO);
                        });


                        uebungDTOsCached.get(id).postValue(katu);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                    
                });

        return uebungDTOsCached.get(id);
    }

    public static MutableLiveData<Uebung> getUebung(long id){
        MutableLiveData<Uebung> mutableLiveData = new MutableLiveData<>();

        iPhysioService.getUebung(Rest.token,id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Uebung>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Uebung uebungDTO) {
                        replaceAll(uebungDTO);

                        mutableLiveData.postValue(uebungDTO);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                    
                });

        return mutableLiveData;
    }

    public static void replaceAll (Uebung uebungDTO){

        allUebungDto.getValue().put(uebungDTO.getUebungId(),uebungDTO);

        uebungDTO.getKategories().forEach(kategorieDTO ->
        {
            uebungDTOsCached.forEach((aLong, linkedHashMapMutableLiveData) -> {
                //if(uebungDTO.getKategories().contains()) {
                    // Enthalten
                //}

            });


        });

        uebungDTOsCached.forEach((aLong, linkedHashMapMutableLiveData) -> {
            if(linkedHashMapMutableLiveData.getValue().containsKey(uebungDTO.getUebungId()))
                linkedHashMapMutableLiveData.getValue().replace(uebungDTO.getUebungId(),uebungDTO);
        });
    }

    public static MutableLiveData<Uebung> saveUebung(Uebung uebungDTO){

        MutableLiveData<Uebung> mutableLiveData = new MutableLiveData<>();

        if(uebungDTO.getUebungId()!=null){
            iPhysioService.saveUebung(Rest.token,uebungDTO).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Uebung>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(Uebung uebungDTO) {
                            replaceAll(uebungDTO);
                            mutableLiveData.postValue(uebungDTO);
                        }

                        @Override
                        public void onError(Throwable e) {
                            mutableLiveData.postValue(null);
                        }


                    });
        }else{
            iPhysioService.createUebung(Rest.token,uebungDTO).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Uebung>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(Uebung uebungDTO) {
                            allUebungDto.getValue().put(uebungDTO.getUebungId(),uebungDTO);
                            uebungDTO.getKategories().forEach(kategorieDTO -> {
                                //if(uebungDTOsCached.containsKey(kategorieDTO.getUebungId()))
                            });
                            mutableLiveData.postValue(uebungDTO);

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
