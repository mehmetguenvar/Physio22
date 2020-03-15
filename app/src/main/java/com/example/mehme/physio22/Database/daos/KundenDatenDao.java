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
import com.example.mehme.physio22.Database.entities.KundenDaten;

import java.util.List;

@Dao
public interface KundenDatenDao {

    @Query("select * from kunden_daten")
    public DataSource.Factory<Long, KundenDaten> getAllKundenDaten();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertKundenDaten(KundenDaten kundenDaten );

    @Update
    public void updateKundenDaten(KundenDaten kundenDaten );

    @Update
    public void updateKundenDaten(KundenDaten... kundenDatens );

    @Delete
    public void deleteKundenDaten(KundenDaten kundenDaten);

    @Delete
    public void deleteKundenDaten(KundenDaten... kundenDatens);
}
