package com.example.mehme.physio22.Database.entities;

import androidx.room.Entity;

@Entity(primaryKeys = {"uebungId", "kategorieId"})
public class UebungKategorieCrossRef {
    public long uebungId;
    public long kategorieId;
}
