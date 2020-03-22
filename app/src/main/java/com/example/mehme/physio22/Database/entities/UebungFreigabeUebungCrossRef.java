package com.example.mehme.physio22.Database.entities;

import androidx.room.Entity;

@Entity(primaryKeys = {"uebung_id","id"})
public class UebungFreigabeUebungCrossRef {
    public long uebung_id;
    public long id;
}
