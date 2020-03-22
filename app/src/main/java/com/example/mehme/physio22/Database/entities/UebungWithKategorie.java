package com.example.mehme.physio22.Database.entities;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class UebungWithKategorie {
    @Embedded
    public Uebung uebung;

    @Relation(
            parentColumn = "uebung_id",
            entityColumn = "kategorie_id",
            associateBy = @Junction(UebungKategorieCrossRef.class)
    )
    public List<Kategorie> kategories;

}

