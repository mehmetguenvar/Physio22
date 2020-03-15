package com.example.mehme.physio22.Database.entities;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class KategorieWithUebung {
    @Embedded
    public Kategorie kategorie;

    @Relation(
            parentColumn = "kategorieId",
            entityColumn = "uebungId",
            associateBy = @Junction(UebungKategorieCrossRef.class)
    )
    public List<Uebung> uebungs;
}
