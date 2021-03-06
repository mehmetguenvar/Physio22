package com.example.mehme.physio22.Database.entities;


import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * A KundenDaten.
 */
@Entity(tableName = "kunden_daten")
public class KundenDaten implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private Long kundenDatenId;

    @ColumnInfo(name = "emailadresse")
    private String emailadresse;

    @ColumnInfo(name = "vorname")
    private String vorname;

    @ColumnInfo(name = "nachname")
    private String nachname;

    @ColumnInfo(name = "vsnummer")
    private Integer vsnummer;


    public Long getKundenDatenId() {
        return kundenDatenId;
    }

    public void setKundenDatenId(Long id) {
        this.kundenDatenId = id;
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
    public String toString() {
        return "KundenDaten{" +
            "id=" + getKundenDatenId() +
            ", emailadresse='" + getEmailadresse() + "'" +
            ", vorname='" + getVorname() + "'" +
            ", nachname='" + getNachname() + "'" +
            ", vsnummer=" + getVsnummer() +
            "}";
    }

    public boolean isSame(KundenDaten kobj){
        if(kobj == null)return false;
        try {

            if(getKundenDatenId() == kobj.getKundenDatenId()
                    && getEmailadresse() == kobj.getEmailadresse()
                    && getNachname() == kobj.getNachname()
                    && getVorname() == kobj.getVorname()
                    && getVsnummer() == kobj.getVsnummer()){
                return true;
            }else return false;

        }catch (Exception e){
            return false;
        }
    }
}
