package com.example.mehme.physio22.dtos;

import com.example.mehme.physio22.dtos.KategorieDTO;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


public class UebungDTO implements Serializable {

    private Long id;

    private String filePath;

    @NotNull
    private String bezeichnung;


    private byte[] bild;

    private String bildContentType;

    private Set<KategorieDTO> kategories = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
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

    public Set<KategorieDTO> getKategories() {
        return kategories;
    }

    public void setKategories(Set<KategorieDTO> kategories) {
        this.kategories = kategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UebungDTO uebungDTO = (UebungDTO) o;
        if (uebungDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), uebungDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UebungDTO{" +
            "id=" + getId() +
            ", filePath='" + getFilePath() + "'" +
            ", bezeichnung='" + getBezeichnung() + "'" +
            ", bild='" + getBild() + "'" +
            "}";
    }
}
