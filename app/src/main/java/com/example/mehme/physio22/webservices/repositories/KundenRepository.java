package com.example.mehme.physio22.webservices.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.mehme.physio22.Database.PhysioRoomDatabase;
import com.example.mehme.physio22.Database.daos.KundenDatenDao;
import com.example.mehme.physio22.Database.entities.KundenDaten;
import com.example.mehme.physio22.webservices.IPhysioService;
import com.example.mehme.physio22.webservices.Rest;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class KundenRepository {

    private IPhysioService iPhysioService = Rest.physioService;
    private LiveData<PagedList<KundenDaten>> kundendatenDTOsCached;
    private static KundenRepository instance;
    private KundenDatenDao kundenDatenDao;
    public  final int SIZE_PAGE = 30;
    public  final int NETWORK_SIZE_PAGE = 60;


    public static KundenRepository getInstance(Application application){
        if(instance == null){
            instance = new KundenRepository(application);
        }
        return instance;
    }

    private KundenRepository(Application application) {
        PhysioRoomDatabase db = PhysioRoomDatabase.getDatabase(application);
        kundenDatenDao = db.kundenDatenDao();
        kundendatenDTOsCached = new LivePagedListBuilder<>(
                kundenDatenDao.getAllKundenDaten(), SIZE_PAGE).build();

    }

    public LiveData<PagedList<KundenDaten>> getAllKundenDaten(){

        iPhysioService.getAllKunden(Rest.token,null).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LinkedList<KundenDaten>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(LinkedList<KundenDaten> kundenDatenDTOS) {
                        PhysioRoomDatabase.databaseWriteExecutor.execute(() -> {
                            kundenDatenDao.insertKundenDatens(kundenDatenDTOS);
                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    
                });

        return kundendatenDTOsCached;

    }

    public  MutableLiveData<KundenDaten> saveKunde(KundenDaten kundenDatenDTO){
        MutableLiveData<KundenDaten> kunde = new MutableLiveData<>();

        if(kundenDatenDTO.getKundenDatenId()!=null)
        iPhysioService.saveKunde(Rest.token,kundenDatenDTO).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<KundenDaten>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(KundenDaten kundenDatenDTO) {
                        kunde.postValue(kundenDatenDTO);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    
                });
        else iPhysioService.createKunde(Rest.token,kundenDatenDTO).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<KundenDaten>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(KundenDaten kundenDatenDTO) {
                        kunde.postValue(kundenDatenDTO);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    
                });


        return kunde;
    }

    public MutableLiveData<KundenDaten> getKunde(long id){
        MutableLiveData<KundenDaten> kunde = new MutableLiveData<>();

        iPhysioService.getKunde(Rest.token,id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<KundenDaten>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(KundenDaten kundenDatenDTO) {
                        kunde.postValue(kundenDatenDTO);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    
                });

        return kunde;
    }



    public Completable saveKundeDatens(List<? extends KundenDaten> m){
        List<KundenDaten> kk = (List<KundenDaten>)m;
        return kundenDatenDao.insertKundenDatens(kk);
    }



    public Listing<KundenDaten> getListable(){
        return new Listing<KundenDaten>() {


            @NotNull
            @Override
            public LiveData<NetworkState> getNetworkState() {
                return Transformations.switchMap(getBoundaryCallback(),input -> input.getNetworkState());
            }

            GenericBoundaryCallback<KundenDaten> bc = new GenericBoundaryCallback<KundenDaten>(
                    ()->kundenDatenDao.deleteAllKundenDaten(),
                    integer -> {
                        Map<String,String> configs = new HashMap<>();
                        int p = integer/NETWORK_SIZE_PAGE;
                        //configs.put("offset",integer+"");
                        configs.put("page",p+"");
                        configs.put("size",NETWORK_SIZE_PAGE+"");
                        return iPhysioService.getAllKunden(Rest.token,configs);
                    },
                    kundenDatens -> saveKundeDatens(kundenDatens),NETWORK_SIZE_PAGE,0
            );

            @NotNull
            @Override
            public LiveData<GenericBoundaryCallback<KundenDaten>> getBoundaryCallback() {
                return new MutableLiveData<>(bc);
            }

            @NotNull
            @Override
            public LiveData<PagedList<KundenDaten>> getDataSource() {
                PagedList.Config c = new PagedList.Config.Builder().setPageSize(SIZE_PAGE).setInitialLoadSizeHint(30).setPrefetchDistance(10)
                        .setEnablePlaceholders(false).build();
                return new LivePagedListBuilder<>(
                        kundenDatenDao.getAllKundenDaten(), c).setBoundaryCallback(bc)

                        .build();
            }
        };
    }
}
