package com.example.mehme.physio22.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * A UebungFreigabe.
 */
@Entity(tableName = "uebung_freigabe")
@TypeConverters({MyTypeConverters.class})
public class UebungFreigabe implements Serializable {

    @PrimaryKey 
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private Long uebungFreigabeId;

    @ColumnInfo(name = "token")
    private String token;

    @ColumnInfo(name = "dauer")
    private Date dauer;

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

    public Date getDauer() {
        return dauer;
    }

    public void setDauer(Date dauer) {
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
