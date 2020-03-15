package com.example.mehme.physio22.Database.entities;

import androidx.room.Entity;

@Entity(primaryKeys = {"uebungId","uebungFreigabeId"})
public class UebungFreigabeUebungCrossRef {
    public long uebungId;
    public long uebungFreigabeId;
}
