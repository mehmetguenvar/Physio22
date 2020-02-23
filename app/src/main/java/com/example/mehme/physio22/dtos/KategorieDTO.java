package com.example.mehme.physio22.dtos;

import java.io.Serializable;
import java.util.Objects;


public class KategorieDTO implements Serializable {

    private Long id;

    private String bezeichnung;

    private Boolean isLeaf;

    private Long oberkategorieId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getOberkategorieId() {
        return oberkategorieId;
    }

    public void setOberkategorieId(Long kategorieId) {
        this.oberkategorieId = kategorieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        KategorieDTO kategorieDTO = (KategorieDTO) o;
        if (kategorieDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), kategorieDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KategorieDTO{" +
                "id=" + getId() +
                ", bezeichnung='" + getBezeichnung() + "'" +
                ", isLeaf='" + isIsLeaf() + "'" +
                ", oberkategorieId=" + getOberkategorieId() +
                "}";
    }
}
