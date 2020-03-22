package com.example.mehme.physio22.Database.daos;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mehme.physio22.Database.entities.Kategorie;
import com.example.mehme.physio22.Database.entities.Uebung;
import com.example.mehme.physio22.Database.entities.UebungWithKategorie;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface UebungDao {

    @Query("select * from uebung")
    public DataSource.Factory<Integer, Uebung> getAllUebung();

    @Query("select * from uebung u " +
            "join UebungKategorieCrossRef uks on uks.uebung_id = u.uebung_id and uks.kategorie_id = :id")
    public DataSource.Factory<Integer, Uebung> getAllUebungByKategorieId(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUebung(Uebung uebung);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertUebungs(List<Uebung> uebung);

    @Update
    public void updateUebung(Uebung uebung);

    @Update
    public void updateUebungs(Uebung... uebungs);

    @Delete
    public void deleteUebung(Uebung uebung);

    @Delete
    public void deleteUebungs(Uebung... uebungs);

    @Query("Delete from uebung")
    public Completable deleteAllUebung();
}
