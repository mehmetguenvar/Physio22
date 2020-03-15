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

import java.util.List;

@Dao
public interface UebungDao {

    @Query("select * from uebung")
    public DataSource.Factory<Long, Uebung> getAllUebung();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUebung(Uebung uebung);

    @Update
    public void updateUebung(Uebung uebung);

    @Update
    public void updateUebungs(Uebung... uebungs);

    @Delete
    public void deleteUebung(Uebung uebung);

    @Delete
    public void deleteUebungs(Uebung... uebungs);
}
