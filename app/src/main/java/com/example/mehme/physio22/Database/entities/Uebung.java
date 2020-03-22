package com.example.mehme.physio22.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.mehme.physio22.dtos.KategorieDTO;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * A Uebung.
 */
@Entity(tableName = "uebung")
public class Uebung implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uebung_id")
    @SerializedName("id")
    private Long uebungId;

    @ColumnInfo(name = "file_path")
    private String filePath;

    @NonNull
    @ColumnInfo(name = "bezeichnung")
    private String bezeichnung;

    @ColumnInfo(name = "bild", typeAffinity = ColumnInfo.BLOB)
    @JsonAdapter(JsonAdapterByteArray.class)
    private byte[] bild = new byte[0];

    @ColumnInfo(name = "bild_content_type")
    private String bildContentType;

    @Ignore
    private Set<Kategorie> kategories;

    public Set<Kategorie> getKategories() {
        return kategories;
    }

    public void setKategories(Set<Kategorie> kategories) {
        this.kategories = kategories;
    }

    @NonNull
    public Long getUebungId() {
        return uebungId;
    }

    public void setUebungId(@NonNull Long uebungId) {
        this.uebungId = uebungId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @NonNull
    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(@NonNull String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public byte[] getBild() {
        return bild;
    }

    public void setBild(byte[] bild) {
        this.bild = bild;
    }

    public String getBildContentType() {
        return bildContentType;
    }

    public void setBildContentType(String bildContentType) {
        this.bildContentType = bildContentType;
    }

    @Override
    public String toString() {
        return "Uebung{" +
            "id=" + getUebungId() +
            ", filePath='" + getFilePath() + "'" +
            ", bezeichnung='" + getBezeichnung() + "'" +
            ", bild='" + getBild() + "'" +
            ", bildContentType='" + getBildContentType() + "'" +
            "}";
    }

    public boolean isSame(Uebung newItem) {
        if(newItem != null && getUebungId() == newItem.getUebungId() && getBezeichnung().equals(newItem.getBezeichnung()) )
            if((newItem.getBild() == null) == (getBild()==null) && getBild() != null && getBild().equals(newItem.getBild())) return true;
        return false;
    }
}
