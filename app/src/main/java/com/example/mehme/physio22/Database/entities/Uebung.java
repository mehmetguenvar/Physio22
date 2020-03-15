package com.example.mehme.physio22.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


/**
 * A Uebung.
 */
@Entity(tableName = "uebung")
public class Uebung implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Long uebungId;

    @ColumnInfo(name = "file_path")
    private String filePath;

    @NonNull
    @ColumnInfo(name = "bezeichnung")
    private String bezeichnung;

    @ColumnInfo(name = "bild", typeAffinity = ColumnInfo.BLOB)
    private byte[] bild;

    @ColumnInfo(name = "bild_content_type")
    private String bildContentType;

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
}
