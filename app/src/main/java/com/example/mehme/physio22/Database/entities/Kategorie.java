package com.example.mehme.physio22.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * A Kategorie.
 */
@Entity(tableName = "kategorie")
public class Kategorie implements Serializable {

    private static final long serialVersionUID = 1L;


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Long kategorieId;

    @ColumnInfo(name = "bezeichnung")
    @NonNull
    private String bezeichnung = "";

    @ColumnInfo(name = "is_leaf")
    @NonNull
    private Boolean isLeaf = false;

    @NonNull
    public Long getKategorieId() {
        return kategorieId;
    }

    public void setKategorieId(@NonNull Long kategorieId) {
        this.kategorieId = kategorieId;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Boolean isIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }


    @Override
    public String toString() {
        return "Kategorie{" +
            "id=" + getKategorieId() +
            ", bezeichnung='" + getBezeichnung() + "'" +
            ", isLeaf='" + isIsLeaf() + "'" +
            "}";
    }
}
