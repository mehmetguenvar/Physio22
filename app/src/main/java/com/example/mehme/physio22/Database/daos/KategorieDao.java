package com.example.mehme.physio22.Database.daos;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.mehme.physio22.Database.entities.Kategorie;
import com.example.mehme.physio22.Database.entities.KategorieWithKategorie;
import com.example.mehme.physio22.Database.entities.KategorieWithUebung;

import java.util.List;

@Dao
public interface KategorieDao {

    @Query("select * from kategorie")
    public DataSource.Factory<Integer,Kategorie> getAllKategorie();

    @Transaction
    @Query("select * from kategorie")
    public DataSource.Factory<Integer,KategorieWithKategorie> getAllKategorieWithSubKategories();

    @Transaction
    @Query("select * from kategorie k where k.kategorie_id = :id")
    public LiveData<KategorieWithKategorie> getKategorieWithSubKategories(long id);

    @Transaction
    @Query("select * from kategorie")
    public DataSource.Factory<Integer,KategorieWithUebung> getAllKategorieWithUebungs();

    @Transaction
    @Query("select * from kategorie k where k.kategorie_id = :id")
    public LiveData<KategorieWithUebung> getKategorieWithUebungs(long id);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertKategorie(Kategorie kategorie);

    @Update
    public void updateKategorie(Kategorie kategorie);

    @Update
    public void updateKategorie(Kategorie... kategories);

    @Delete
    public void deleteKategorie(Kategorie kategorie);

    @Delete
    public void deleteKategorie(Kategorie... kategories);
}
