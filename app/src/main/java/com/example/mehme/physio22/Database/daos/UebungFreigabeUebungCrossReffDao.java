package com.example.mehme.physio22.Database.daos;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mehme.physio22.Database.entities.KundenDaten;
import com.example.mehme.physio22.Database.entities.UebungFreigabeUebungCrossRef;
import com.example.mehme.physio22.Database.entities.UebungKategorieCrossRef;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface UebungFreigabeUebungCrossReffDao {

    @Query("select * from UebungFreigabeUebungCrossRef")
    public DataSource.Factory<Integer, UebungFreigabeUebungCrossRef> getAllCrossReff();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertCrossReffs(List<UebungFreigabeUebungCrossRef> crossRefs );

    @Query("delete from uebungfreigabeuebungcrossref")
    public Completable deleteAllCrossReffs();

}