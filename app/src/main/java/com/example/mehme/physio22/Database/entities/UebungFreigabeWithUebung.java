package com.example.mehme.physio22.Database.entities;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class UebungFreigabeWithUebung {
    @Embedded
    public UebungFreigabe uebungFreigabe;

    @Relation(
            parentColumn = "uebungFreigabeId",
            entityColumn = "uebungId",
            associateBy = @Junction(UebungFreigabeUebungCrossRef.class)
    )
    public List<Uebung> uebungs;
}
