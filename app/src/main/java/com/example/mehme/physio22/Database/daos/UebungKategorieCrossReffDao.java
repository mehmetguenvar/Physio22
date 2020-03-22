package com.example.mehme.physio22.Database.daos;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.mehme.physio22.Database.entities.UebungKategorieCrossRef;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface UebungKategorieCrossReffDao {

    @Query("select * from UebungKategorieCrossRef")
    public DataSource.Factory<Integer, UebungKategorieCrossRef> getAllCrossReff();

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCrossReffs(List<UebungKategorieCrossRef> crossRefs );

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertCrossReff(UebungKategorieCrossRef crossRefs );


    @Query("delete from UebungKategorieCrossRef")
    public Completable deleteAllCrossReffs();

}
