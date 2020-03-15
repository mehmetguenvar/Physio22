package com.example.mehme.physio22.Database.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UebungFreigabeWithKundenDaten {
    @Embedded
    public KundenDaten kundenDaten;

    @Relation(
            parentColumn = "kundenDatenId",
            entityColumn = "uebungFreigabeId"
    )
    public List<UebungFreigabe> uebungFreigabes;

}
