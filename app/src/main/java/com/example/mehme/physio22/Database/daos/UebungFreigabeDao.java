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
import com.example.mehme.physio22.Database.entities.UebungFreigabe;
import com.example.mehme.physio22.Database.entities.UebungFreigabeWithUebung;

import java.util.List;

@Dao
public interface UebungFreigabeDao {

    @Query("select * from uebung_freigabe")
    public DataSource.Factory<Long, UebungFreigabe> getAllUebungFreigabe();

    @Query("select * from uebung_freigabe")
    public DataSource.Factory<Long,UebungFreigabeWithUebung> getAllUebungFreigabeWithUebungs();

    @Query("select * from uebung_freigabe where id = :id")
    public LiveData<UebungFreigabeWithUebung> getUebungFreigabeWithUebungs(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUebungFreigabe(UebungFreigabe uebungFreigabe );

    @Update
    public void updateUebungFreigabe(UebungFreigabe uebungFreigabe );

    @Update
    public void updateUebungFreigabe(UebungFreigabe... uebungFreigabes );

    @Delete
    public void deleteUebungFreigabe(UebungFreigabe uebungFreigabe  );

    @Delete
    public void deleteUebungFreigabe(UebungFreigabe... uebungFreigabes );
}
