package com.example.mehme.physio22.dtos;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

public class UebungFreigabeDTO implements Serializable {

    private Long id;

    private String token;

    private LocalDate dauer;


    private Set<UebungDTO> uebungs = new HashSet<>();

    private Long kundeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<UebungDTO> getUebungs() {
        return uebungs;
    }

    public void setUebungs(Set<UebungDTO> uebungs) {
        this.uebungs = uebungs;
    }

    public Long getKundeId() {
        return kundeId;
    }

    public void setKundeId(Long kundenDatenId) {
        this.kundeId = kundenDatenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UebungFreigabeDTO uebungFreigabeDTO = (UebungFreigabeDTO) o;
        if (uebungFreigabeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), uebungFreigabeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UebungFreigabeDTO{" +
            "id=" + getId() +
            ", token='" + getToken() + "'" +
            ", dauer='" + getDauer() + "'" +
            ", kundeId=" + getKundeId() +
            "}";
    }
}
