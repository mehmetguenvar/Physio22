package com.example.mehme.physio22.Database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(tableName = "UebungKategorieCrossRef",primaryKeys = {"uebung_id", "kategorie_id"}, indices = {@Index("kategorie_id")})
public class UebungKategorieCrossRef {

    @ColumnInfo(name = "uebung_id")
    public long uebung_id;

    @ColumnInfo(name = "kategorie_id")
    public long kategorie_id;

    public long getUebung_id() {
        return uebung_id;
    }

    public void setUebung_id(long uebung_id) {
        this.uebung_id = uebung_id;
    }

    public long getKategorie_id() {
        return kategorie_id;
    }

    public void setKategorie_id(long kategorie_id) {
        this.kategorie_id = kategorie_id;
    }
}
