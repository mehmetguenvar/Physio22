package com.example.mehme.physio22.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A UebungFreigabe.
 */
@Entity(tableName = "uebung_freigabe")
public class UebungFreigabe implements Serializable {

    @PrimaryKey 
    @NonNull
    @ColumnInfo(name = "id")
    private Long uebungFreigabeId;

    @ColumnInfo(name = "token")
    private String token;

    @ColumnInfo(name = "dauer")
    private LocalDate dauer;

    @NonNull
    public Long getUebungFreigabeId() {
        return uebungFreigabeId;
    }

    public void setUebungFreigabeId(@NonNull Long uebungFreigabeId) {
        this.uebungFreigabeId = uebungFreigabeId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDate getDauer() {
        return dauer;
    }

    public void setDauer(LocalDate dauer) {
        this.dauer = dauer;
    }

    @Override
    public String toString() {
        return "UebungFreigabe{" +
            "id=" + getUebungFreigabeId() +
            ", token='" + getToken() + "'" +
            ", dauer='" + getDauer() + "'" +
            "}";
    }
}
