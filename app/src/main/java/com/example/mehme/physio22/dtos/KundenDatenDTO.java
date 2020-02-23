package com.example.mehme.physio22.dtos;

import java.io.Serializable;
import java.util.Objects;


public class KundenDatenDTO implements Serializable {

    private Long id;

    private String emailadresse;

    private String vorname;

    private String nachname;

    private Integer vsnummer;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailadresse() {
        return emailadresse;
    }

    public void setEmailadresse(String emailadresse) {
        this.emailadresse = emailadresse;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Integer getVsnummer() {
        return vsnummer;
    }

    public void setVsnummer(Integer vsnummer) {
        this.vsnummer = vsnummer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        KundenDatenDTO kundenDatenDTO = (KundenDatenDTO) o;
        if (kundenDatenDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), kundenDatenDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KundenDatenDTO{" +
            "id=" + getId() +
            ", emailadresse='" + getEmailadresse() + "'" +
            ", vorname='" + getVorname() + "'" +
            ", nachname='" + getNachname() + "'" +
            ", vsnummer=" + getVsnummer() +
            "}";
    }
}
