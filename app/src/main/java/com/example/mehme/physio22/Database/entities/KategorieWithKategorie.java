package com.example.mehme.physio22.Database.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class KategorieWithKategorie {
    @Embedded
    public Kategorie kategorie;

    @Relation(
            parentColumn = "kategorie_id",
            entityColumn = "kategorie_id"
    )
    public List<Kategorie> unterKategorien;
}
